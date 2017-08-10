package com.ryanf.bnf;

import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.interfaces.ITokens;

public class Parser {
	ITokens tokens;
	
	public Parser(ITokens tokens) {
		this.tokens = tokens;
	}
	
	public void parse() {
		while (tokens.hasMore()) {
			tokens.next();
			IToken token = tokens.getToken();
			System.out.printf("%s\t%s\n", token.getName(), token.getType().toString());
		}
	}
}
