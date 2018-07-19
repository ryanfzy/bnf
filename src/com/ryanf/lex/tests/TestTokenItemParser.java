package com.ryanf.lex.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ryanf.lex.TokenItem;
import com.ryanf.lex.TokenItemParser;
import com.ryanf.lex.types.ValueType;

public class TestTokenItemParser {
	
	@Test
	public void testNumberAnyOrMore() {
		String input = "[#x20 #x9 #xD #xA]+";
		TokenItemParser parser = new TokenItemParser(input);
		TokenItem item = parser.Next();
		assertEquals("#x20 #x9 #xD #xA", item.Value());
		assertEquals(ValueType.AnySetOneOrMore, item.Type());
		item = parser.Next();
		assertEquals(null, item);
	}
	
	@Test
	public void testNumbers() {
		String input = "#x20 #x9 #xD #xA";
		TokenItemParser parser = new TokenItemParser(input);
		TokenItem item = parser.Next();
		assertEquals("#x20", item.Value());
		item = parser.Next();
		assertEquals("#x9", item.Value());
		item = parser.Next();
		assertEquals("#xD", item.Value());
		item = parser.Next();
		assertEquals("#xA", item.Value());
		item = parser.Next();
		assertEquals(null, item);
	}
	
	@Test
	public void testCombined() {
		String input = "1.[0-9]+";
		TokenItemParser parser = new TokenItemParser(input);
		TokenItem item = parser.Next();
		assertEquals("1", item.Value());
		assertEquals(ValueType.SingleCharacter, item.Type());
		item = parser.Next();
		assertEquals(".", item.Value());
		assertEquals(ValueType.SingleCharacter, item.Type());
		item = parser.Next();
		assertEquals("0-9", item.Value());
		assertEquals(ValueType.AnySetOneOrMore, item.Type());
		item = parser.Next();
		assertEquals(null, item);
	}

	@Test
	public void testRanges() {
		String input = "a-zA-Z0-9";
		TokenItemParser parser = new TokenItemParser(input);
		TokenItem item = parser.Next();
		assertEquals("a-z", item.Value());
		assertEquals(item.Type(), ValueType.CharacterRange);
		item = parser.Next();
		assertEquals("A-Z", item.Value());
		assertEquals(item.Type(), ValueType.CharacterRange);
		item = parser.Next();
		assertEquals("0-9", item.Value());
		assertEquals(item.Type(), ValueType.CharacterRange);
	}
	
	@Test
	public void testAnyList() {
		String input = "[A-Za-z] ([A-Za-z0-9._] | -)*";
		TokenItemParser parser = new TokenItemParser(input);
		TokenItem item = parser.Next();
		assertEquals("A-Za-z", item.Value());
		assertEquals(ValueType.AnySet, item.Type());
		item = parser.Next();
		assertEquals("[A-Za-z0-9._] | -", item.Value());
		assertEquals(ValueType.MultiAnySetZeroOrMore, item.Type());
		parser = new TokenItemParser(item.Value());
		item = parser.Next();
		assertEquals("A-Za-z0-9._", item.Value());
		assertEquals(ValueType.AnySet, item.Type());
		item = parser.Next();
		assertEquals("-", item.Value());
		assertEquals(ValueType.SingleCharacter, item.Type());
	}
}
