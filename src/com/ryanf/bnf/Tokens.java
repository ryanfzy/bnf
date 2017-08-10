package com.ryanf.bnf;

import java.util.Vector;

import com.ryanf.bnf.builders.TokenBuilder;
import com.ryanf.bnf.interfaces.ISource;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.interfaces.ITokens;

public class Tokens implements ITokens {
	ISource source;
	StringBuilder builder;
	Vector<IToken> tokens;
	int pos;
	
	public Tokens(ISource source) {
		this.source = source;
		init();
	}
	
	public void next() {
		pos++;
		if (pos > tokens.size() - 1)
			tokens.add(getNext());
	}
	
	public void previous() {
		pos--;
	}
	
	public IToken getToken() {
		return tokens.elementAt(pos);
	}
	
	public boolean hasMore() {
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
			else if (!matchIgnore()) {
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
		return TokenBuilder.createToken(getTokenName(), getTokenType());
	}
	
	private void init() {
		builder = new StringBuilder();
		tokens = new Vector<IToken>();
		pos = -1;
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
	
	private String getTokenName() {
		return builder.toString();
	}
	
	private boolean matchSpecial() {
		return Lex.matchSpecial(source.getChar());
	}
	
	private boolean matchQuote() {
		return Lex.matchQuote(source.getChar());
	}
	
	private boolean matchIgnore() {
		return Lex.matchIgnore(source.getChar());
	}
	
	private TokenType getTokenType() {
		return Lex.getTokenType(getTokenName());
	}
}
