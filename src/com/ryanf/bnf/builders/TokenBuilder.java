package com.ryanf.bnf.builders;

import com.ryanf.bnf.Token;
import com.ryanf.bnf.TokenType;
import com.ryanf.bnf.interfaces.IToken;

public class TokenBuilder {
	public static IToken createToken(String name, TokenType type, int row, int col) {
		return new Token(name, type, row, col);
	}
}
