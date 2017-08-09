package com.ryanf.bnf;

import com.ryanf.bnf.builders.TokenBuilder;
import com.ryanf.bnf.interfaces.ISource;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.interfaces.ITokens;

public class Tokens implements ITokens {
	ISource source;
	StringBuilder builder;
	
	public Tokens(ISource source) {
		this.source = source;
		init();
	}
	
	public boolean hasNext() {
		return source.hasMore();
	}
	
	public IToken getNext() {
		clearToken();
		boolean foundQuote = false;
		while (source.hasMore()) {
			source.moveForward();
			foundQuote ^= matchQuote();
			if (foundQuote) {
				addToToken();
				continue;
			}
			else if (!matchSeparator()) {
				if (matchSpecial()) {
					if (hasToken())
						source.moveBackward();
					else
						addToToken();
				}
				else {
					addToToken();
					continue;
				}
			}
			if (hasToken())
				break;
		}
		return TokenBuilder.createToken(getToken(), getTokenType());
	}
	
	private void init() {
		builder = new StringBuilder();
	}
	
	private void clearToken() {
		builder = new StringBuilder();
	}
	
	private void addToToken() {
		builder.append(source.getChar());
	}
	
	private boolean hasToken() {
		return builder.toString().trim().length() >  0;
	}
	
	private String getToken() {
		return builder.toString();
	}
	
	private boolean matchSpecial() {
		return Lex.matchSpecial(source.getChar());
	}
	
	private boolean matchQuote() {
		return Lex.matchQuote(source.getChar());
	}
	
	private boolean matchSeparator() {
		return Lex.matchSeparator(source.getChar());
	}
	
	private TokenType getTokenType() {
		return Lex.getTokenType(getToken());
	}
}
