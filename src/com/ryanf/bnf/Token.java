package com.ryanf.bnf;

import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.types.TokenPos;
import com.ryanf.bnf.types.TokenType;

public class Token implements IToken {
	String name;
	TokenType type;
	TokenPos pos;
	
	public Token(String name, TokenType type, int column, int line) {
		this.name = name;
		this.type = type;
		pos = new TokenPos(column, line);
	}
	
	public TokenType getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public TokenPos getPos() {
		return pos;
	}
	
	@Override
	public String toString() {
		return String.format("%s[%s](%d,%d)", name, type.toString(), pos.getLine(), pos.getColumn());
	}
}
