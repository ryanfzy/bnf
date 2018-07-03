package com.ryanf.bnf.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ryanf.bnf.StringSource;
import com.ryanf.bnf.Tokens;
import com.ryanf.bnf.builders.TokensBuilder;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.interfaces.ITokens;
import com.ryanf.bnf.types.TokenType;

public class TestTokens {

	@Test
	public void testTokens() {
		String str = "ident=,;*()?\\\'\\\"-|[]\\#^\"a string\"+#123 #x123abc?ident2\"another \\\"string\\\"\"";
		ITokens tokens = TokensBuilder.createTokens(new StringSource(str));
		TokenType[] expectedTypes = {
			TokenType.IDENTIFIER,
			TokenType.ASSIGN,
			TokenType.COMMA,
			TokenType.SEMICOLON,
			TokenType.STAR,
			TokenType.LEFTBRACE,
			TokenType.RIGHTBRACE,
			TokenType.QUESTION,
			TokenType.QUOTE,
			TokenType.DOUBLEQUOTE,
			TokenType.HBAR,
			TokenType.VBAR,
			TokenType.LEFTSQUAREBRACE,
			TokenType.RIGHTSQUAREBRACE,
			TokenType.HASH,
			TokenType.HAT,
			TokenType.STRING,
			TokenType.PLUS,
			TokenType.NUMBER,
			TokenType.NUMBER,
			TokenType.QUESTION,
			TokenType.IDENTIFIER,
			TokenType.STRING,
		};
		String[] expectedValue = {
			"ident",
			"=",
			",",
			";",
			"*",
			"(",
			")",
			"?",
			"'",
			"\"",
			"-",
			"|",
			"[",
			"]",
			"#",
			"^",
			"a string",
			"+",
			"123",
			"x123abc",
			"?",
			"ident2",
			"another \"string\"",
		};
		int typeIndex = 0;
		while (tokens.hasMore()) {
			tokens.next();
			IToken token = tokens.getToken();
			assertEquals(expectedTypes[typeIndex], token.getType());
			assertEquals(expectedValue[typeIndex], token.getName());
			typeIndex++;
		}
	}

}
