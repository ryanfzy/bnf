package com.ryanf.bnf.exceptions;

import com.ryanf.bnf.TokenType;
import com.ryanf.bnf.interfaces.IToken;

public class TokenTypeNotMatchException extends ParserException {
	private static final long serialVersionUID = 1135090799239775994L;
	
	public TokenTypeNotMatchException(IToken token, TokenType expectedType, TokenType actualType) {
		super(token);
		setMessage(String.format("Expect type: %s; Actual type: %s", expectedType.toString(), actualType.toString()));
	}
}
