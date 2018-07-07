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
		assertEquals(ValueType.AnyOneOrMore, item.Type());
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
		assertEquals(ValueType.Single, item.Type());
		item = parser.Next();
		assertEquals(".", item.Value());
		assertEquals(ValueType.Single, item.Type());
		item = parser.Next();
		assertEquals("0-9", item.Value());
		assertEquals(ValueType.AnyOneOrMore, item.Type());
		item = parser.Next();
		assertEquals(null, item);
	}

	@Test
	public void testRanges() {
		String input = "a-zA-Z0-9";
		TokenItemParser parser = new TokenItemParser(input);
		TokenItem item = parser.Next();
		assertEquals("a-z", item.Value());
		item = parser.Next();
		assertEquals("A-Z", item.Value());
		item = parser.Next();
		assertEquals("0-9", item.Value());
	}
}
