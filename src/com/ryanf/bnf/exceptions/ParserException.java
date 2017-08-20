package com.ryanf.bnf.exceptions;

import com.ryanf.bnf.interfaces.IToken;

public class ParserException extends Exception {
	private static final long serialVersionUID = 6667226610420105390L;

	String message;
	
	public ParserException() {
		message = "Unknown Exception";
	}
	
	public ParserException(IToken token) {
		message = String.format("\nLine %d, Column %d\n", token.getPos().getRow(), token.getPos().getColumn());
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	protected void setMessage(String msg) {
		message += msg;
	}
}
