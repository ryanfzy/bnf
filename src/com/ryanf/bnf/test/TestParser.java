package com.ryanf.bnf.test;

import static org.junit.Assert.*;

import javax.swing.text.html.parser.Parser;

import org.junit.Test;

import com.ryanf.bnf.builders.ParserBuilder;
import com.ryanf.bnf.exceptions.ParserException;
//import com.ryanf.bnf.interfaces.IAstNode;
//import com.ryanf.bnf.interfaces.IAstTree;
import com.ryanf.bnf.interfaces.IParser;
import com.ryanf.bnf.tree.AlterListNode;
import com.ryanf.bnf.tree.AsignStatNode;
import com.ryanf.bnf.tree.AstNode;
import com.ryanf.bnf.tree.AstTree;
import com.ryanf.bnf.tree.CharListNode;
import com.ryanf.bnf.tree.CharNode;
import com.ryanf.bnf.tree.CharRangeNode;
import com.ryanf.bnf.tree.ListNode;
import com.ryanf.bnf.tree.NumberNode;
import com.ryanf.bnf.tree.StatListNode;
import com.ryanf.bnf.tree.SubStatNode;
import com.ryanf.bnf.types.AstNodeType;
import com.ryanf.bnf.types.QuantifierType;
import com.ryanf.bnf.types.TokenType;

public class TestParser {

	@Test
	public void testSimpleAsignStat() {
		String str = "grammar = rule;";
		IParser parser = ParserBuilder.createParserFromString(str);
		AstTree tree = null;
		AstNode root = null;
		try {
			tree = parser.parse();
			root = tree.getRoot();
		} catch (ParserException e) {
			assertTrue(false);
		}
		assertEquals(AstNodeType.STATLIST, root.getType());
		StatListNode stats = root.As();
		assertEquals(1, stats.count());
		assertEquals(AstNodeType.ASIGN_STAT, stats.get(0).getType());
		AsignStatNode asign = stats.get(0).As();
		assertEquals("grammar", asign.getLhs().getName());
		assertEquals("rule", asign.getRhs().getName());
	}

	@Test
	public void testListAsignStat() {
		try {
			String str = "document = prolog, element, misc*;";
			IParser parser = ParserBuilder.createParserFromString(str);
			AstTree tree = parser.parse();
			StatListNode stats = tree.getRoot().As();
			assertEquals(1, stats.count());
			AsignStatNode asign = stats.get(0).As();
			assertEquals("document", asign.getLhs().getName());
			ListNode list = asign.getRhs().As();
			assertEquals(3, list.count());
			assertEquals("prolog", list.get(0).getName());
			assertEquals("element", list.get(1).getName());
			assertEquals("misc", list.get(2).getName());
			assertEquals(QuantifierType.ZERO_OR_MORE, list.get(2).getQuantifier());
		} catch (ParserException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testSubListAsignStat() {
		try {
			String str = "prolog = xml-decl, misc*, (doctype-decl, misc*)?;";
			IParser parser = ParserBuilder.createParserFromString(str);
			AstTree tree = parser.parse();
			StatListNode stats = tree.getRoot().As();
			assertEquals(1, stats.count());
			AsignStatNode asign = stats.get(0).As();
			assertEquals("prolog", asign.getLhs().getName());
			ListNode list = asign.getRhs().As();
			assertEquals(3, list.count());
			assertEquals("xml-decl", list.get(0).getName());
			assertEquals("misc", list.get(1).getName());
			assertEquals(QuantifierType.ZERO_OR_MORE, list.get(1).getQuantifier());
			ListNode list2 = list.get(2).As();
			assertEquals(2, list2.count());
			assertEquals(QuantifierType.ZERO_OR_ONE, list2.getQuantifier());
			assertEquals("doctype-decl", list2.get(0).getName());
			assertEquals("misc", list2.get(1).getName());
			assertEquals(QuantifierType.ZERO_OR_MORE, list2.get(1).getQuantifier());
		} catch (ParserException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testCharListAsignStat() {
		try {
			String str = "version-num = '1.', [0-9]+;";
			IParser parser = ParserBuilder.createParserFromString(str);
			AstTree tree = parser.parse();
			StatListNode stats = tree.getRoot().As();
			assertEquals(1, stats.count());
			AsignStatNode asign = stats.get(0).As();
			assertEquals("version-num", asign.getLhs().getName());
			ListNode list = asign.getRhs().As();
			assertEquals(2, list.count());
			assertEquals("1.", list.get(0).getName());
			CharListNode charList = list.get(1).As();
			assertEquals(QuantifierType.ONE_OR_MORE, charList.getQuantifier());
			assertEquals(1, charList.count());
			CharRangeNode rangeNode = charList.get(0).As();
			CharNode fromNode = rangeNode.from().As();
			CharNode toNode = rangeNode.to().As();
			assertEquals("0", fromNode.getChar());
			assertEquals("9", toNode.getChar());
		} catch (ParserException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testAlterListAsignStat() {
		try {
			String str = "misc = comment | pi | S;";
			IParser parser = ParserBuilder.createParserFromString(str);
			AstTree tree = parser.parse();
			StatListNode stats = tree.getRoot().As();
			AsignStatNode asign = stats.get(0).As();
			assertEquals("misc", asign.getLhs().getName());
			AlterListNode alterList = asign.getRhs().As();
			assertEquals(3, alterList.count());
			assertEquals("comment", alterList.get(0).getName());
			assertEquals("pi", alterList.get(1).getName());
			assertEquals("S", alterList.get(2).getName());
		} catch (ParserException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testNumberAlterListAsignStat() {
		try {
			String str = "S = (#x20 | #x9 | #xD | #xA)+;";
			IParser parser = ParserBuilder.createParserFromString(str);
			AstTree tree = parser.parse();
			StatListNode stats = tree.getRoot().As();
			AsignStatNode asign = stats.get(0).As();
			assertEquals("S", asign.getLhs().getName());
			AlterListNode alterList = asign.getRhs().As();
			assertEquals(4, alterList.count());
			assertEquals(QuantifierType.ONE_OR_MORE, alterList.getQuantifier());
			NumberNode number = alterList.get(0).As();
			assertEquals("x20", number.getNumber());
			number = alterList.get(1).As();
			assertEquals("x9", number.getNumber());
			number = alterList.get(2).As();
			assertEquals("xD", number.getNumber());
			number = alterList.get(3).As();
			assertEquals("xA", number.getNumber());
		} catch (ParserException e) {
			assertTrue(false);
		}
	}
}
