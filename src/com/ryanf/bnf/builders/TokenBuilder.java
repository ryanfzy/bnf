package com.ryanf.bnf.builders;

import com.ryanf.bnf.Lex;
import com.ryanf.bnf.Token;
import com.ryanf.bnf.TokenType;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.types.TokenContext;

public class TokenBuilder {
	StringBuilder strBuilder;
	TokenContext context;
	char quote;
	boolean foundToken;
	
	public TokenBuilder() {
		init();
	}
	
	public boolean feed(char curChar, char nextChar) {
		if (foundToken)
			resetToken();
		
		if (isInStringContext()) {
			addToToken(curChar);
			if (curChar == quote) {
				resetTokenContext();
				foundToken = true;
			}
		} 
		else if (isInCharacterSetContext()) {
			addToToken(curChar);
			foundToken = true;
			if (matchCloseCharacterSet(curChar))
				resetTokenContext();
		}
		else if (matchOpenCharacterSet(curChar)) {
			setInCharacterSetContext();
			addToToken(curChar);
			foundToken = true;
		}
		else if (matchSpecial(curChar)) {
			addToToken(curChar);
			foundToken = true;
		}
		else if (matchQuote(curChar)) {
			setInStringContext(curChar);
			addToToken(curChar);
		}
		else if (!matchIgnore(curChar)) {
			addToToken(curChar);
			if (matchSpecial(nextChar) || matchIgnore(nextChar))
				foundToken = true;
		}
		
		if (foundToken && hasToken())
			return true;
		return false;
	}
	
	public IToken createToken(int col, int row) {
		return new Token(getTokenName(), getTokenType(), col, row);
	}
	
	private void init() {
		strBuilder = new StringBuilder();
		foundToken = false;
	}
	
	private boolean isInStringContext() {
		return context == TokenContext.STRING;
	}
	
	private boolean isInCharacterSetContext() {
		return context == TokenContext.CHARACTERSET;
	}
	
	private boolean matchCloseCharacterSet(char feedChar) {
		return feedChar == Lex.RightSquareBrace;
	}
	
	private boolean matchOpenCharacterSet(char feedChar) {
		return feedChar == Lex.LeftSquareBrace;
	}
	
	private void resetTokenContext() {
		context = TokenContext.NOCONTEXT;
		quote = Character.MIN_VALUE;
	}
	
	private void setInStringContext(char quoteChar) {
		context = TokenContext.STRING;
		quote = quoteChar;
	}
	
	private void setInCharacterSetContext() {
		context = TokenContext.CHARACTERSET;
	}
	
	/*
	private IToken getNext() {
		clearToken();
		while (source.hasMore()) {
			try {
				source.next();
			} catch (Exception e) {
				return null;
			}
			if (matchQuote()) {
				getString();
				break;
			}
			else if (!matchIgnore()) {
				if (!matchSpecial()) {
					addToToken();
					if (!matchSpecialOneAhead())
						continue;
				}
				else {
					addToToken();
					
				}
			}
			if (hasToken())
				break;
		}
		return TokenBuilder.createToken(getTokenName(), getTokenType(), tokenCol+1, tokenRow+1);
	}*/
	
	private void resetToken() {
		strBuilder = new StringBuilder();
		foundToken = false;
	}
	
	private void addToToken(char feedChar) {
		strBuilder.append(feedChar);
	}
	
	private boolean hasToken() {
		return strBuilder.toString().trim().length() >  0;
	}
	
	private String getTokenName() {
		return strBuilder.toString();
	}
	
	private TokenType getTokenType() {
		return Lex.getTokenType(getTokenName());
	}
	
	private boolean matchSpecial(char feedChar) {
		return Lex.matchSpecial(feedChar);
	}
	
	private boolean matchQuote(char feedChar) {
		return Lex.matchQuote(feedChar);
	}
	
	private boolean matchIgnore(char feedChar) {
		return Lex.matchIgnore(feedChar);
	}
}
