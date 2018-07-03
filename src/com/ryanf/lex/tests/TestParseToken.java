package com.ryanf.lex.tests;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import com.ryanf.lex.MultiOneOrMoreTokenValue;
import com.ryanf.lex.Parser;
import com.ryanf.lex.SingleOneTokenValue;
import com.ryanf.lex.Token;
import com.ryanf.lex.types.ValueType;

public class TestParseToken {
	
	/*
	private Vector<Long> ToValues(String str) {
		Vector<Long> values = new Vector<Long>();
		for (char ch : str.toCharArray())
			values.add((long)ch);
		return values;
	}*/

	@Test
	public void testStringValue() {
		String str = "XML_DECL_START = <?xml";
		Token token = Parser.parseString(str);
		assertEquals("XML_DECL_START", token.Name());
		assertEquals(5, token.Values().size());
		assertTrue(token.Values().get(0) instanceof SingleOneTokenValue);
		assertEquals((long)'<', ((SingleOneTokenValue)token.Values().get(0)).Value());
		assertTrue(token.Values().get(1) instanceof SingleOneTokenValue);
		assertEquals((long)'?', ((SingleOneTokenValue)token.Values().get(1)).Value());
		assertTrue(token.Values().get(2) instanceof SingleOneTokenValue);
		assertEquals((long)'x', ((SingleOneTokenValue)token.Values().get(2)).Value());
		assertTrue(token.Values().get(3) instanceof SingleOneTokenValue);
		assertEquals((long)'m', ((SingleOneTokenValue)token.Values().get(3)).Value());
		assertTrue(token.Values().get(4) instanceof SingleOneTokenValue);
		assertEquals((long)'l', ((SingleOneTokenValue)token.Values().get(4)).Value());
	}
	
	@Test
	public void testNumberValue() {
		String str = "SPACE = #x20 #x9 #xD #xA";
		Token token = Parser.parseString(str);
		assertEquals("SPACE", token.Name());
		assertEquals(4, token.Values().size());
		assertTrue(token.Values().get(0) instanceof SingleOneTokenValue);
		assertEquals(32, ((SingleOneTokenValue)token.Values().get(0)).Value());
		assertTrue(token.Values().get(1) instanceof SingleOneTokenValue);
		assertEquals(9, ((SingleOneTokenValue)token.Values().get(1)).Value());
		assertTrue(token.Values().get(2) instanceof SingleOneTokenValue);
		assertEquals(13, ((SingleOneTokenValue)token.Values().get(2)).Value());
		assertTrue(token.Values().get(3) instanceof SingleOneTokenValue);
		assertEquals(10, ((SingleOneTokenValue)token.Values().get(3)).Value());
	}

	@Test
	public void testAnyOneOrMoreValue() {
		String str = "SPACE = [#x20 #x9 #xD #xA]+";
		Token token = Parser.parseString(str);
		assertEquals("SPACE", token.Name());
		assertEquals(1, token.Values().size());
		assertTrue(token.Values().get(0) instanceof MultiOneOrMoreTokenValue);
	}
}
