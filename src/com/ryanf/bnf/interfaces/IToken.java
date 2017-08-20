package com.ryanf.bnf.interfaces;

import com.ryanf.bnf.types.TokenPos;
import com.ryanf.bnf.types.TokenType;

public interface IToken {
	public String getName();
	public TokenType getType();
	public TokenPos getPos();
}
