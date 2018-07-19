package com.ryanf.lex.tests;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import com.ryanf.lex.MultiOneOrMoreTokenValue;
import com.ryanf.lex.Parser;
import com.ryanf.lex.SingleTokenValue;
import com.ryanf.lex.Token;
import com.ryanf.lex.types.ValueType;

public class TestParseToken {

	@Test
	public void testStringValue() {
		String str = "XML_DECL_START = <?xml";
		Token token = Parser.parseString(str);
		assertEquals("XML_DECL_START", token.Name());
		assertEquals(5, token.Values().size());
		assertTrue(token.Values().get(0) instanceof SingleTokenValue);
		assertEquals((long)'<', ((SingleTokenValue)token.Values().get(0)).Value());
		assertTrue(token.Values().get(1) instanceof SingleTokenValue);
		assertEquals((long)'?', ((SingleTokenValue)token.Values().get(1)).Value());
		assertTrue(token.Values().get(2) instanceof SingleTokenValue);
		assertEquals((long)'x', ((SingleTokenValue)token.Values().get(2)).Value());
		assertTrue(token.Values().get(3) instanceof SingleTokenValue);
		assertEquals((long)'m', ((SingleTokenValue)token.Values().get(3)).Value());
		assertTrue(token.Values().get(4) instanceof SingleTokenValue);
		assertEquals((long)'l', ((SingleTokenValue)token.Values().get(4)).Value());
	}
	
	@Test
	public void testNumberValue() {
		String str = "SPACE = #x20 #x9 #xD #xA";
		Token token = Parser.parseString(str);
		assertEquals("SPACE", token.Name());
		assertEquals(4, token.Values().size());
		assertTrue(token.Values().get(0) instanceof SingleTokenValue);
		assertEquals(32, ((SingleTokenValue)token.Values().get(0)).Value());
		assertTrue(token.Values().get(1) instanceof SingleTokenValue);
		assertEquals(9, ((SingleTokenValue)token.Values().get(1)).Value());
		assertTrue(token.Values().get(2) instanceof SingleTokenValue);
		assertEquals(13, ((SingleTokenValue)token.Values().get(2)).Value());
		assertTrue(token.Values().get(3) instanceof SingleTokenValue);
		assertEquals(10, ((SingleTokenValue)token.Values().get(3)).Value());
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
