package com.ryanf.bnf.interfaces;

import com.ryanf.bnf.TokenType;
import com.ryanf.bnf.types.TokenPos;

public interface IToken {
	public String getName();
	public TokenType getType();
	public TokenPos getPos();
}
