package com.ryanf.bnf;

import com.ryanf.bnf.interfaces.IToken;

public class Token implements IToken {
	String name;
	TokenType type;
	
	public Token(String name, TokenType type) {
		this.name = name;
		this.type = type;
	}
	
	public TokenType getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
}
