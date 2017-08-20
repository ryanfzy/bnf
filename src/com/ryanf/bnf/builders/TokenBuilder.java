package com.ryanf.bnf.builders;

import java.util.Stack;

import com.ryanf.bnf.Lex;
import com.ryanf.bnf.Token;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.types.TokenContext;
import com.ryanf.bnf.types.TokenType;

public class TokenBuilder {
	StringBuilder strBuilder;
	Stack<TokenContext> contexts;
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
				popTokenContext();
				foundToken = true;
			}
		}
		else if (isInCharacterSetContext()) {
			addToToken(curChar);
			if (matchNumberStartChar(curChar))
				pushNumberContext();
			else {
				foundToken = true;
				if (matchCloseCharacterSet(curChar))
					popTokenContext();
			}
		}
		else if (isInNumberContext()) {
			addToToken(curChar);
			if (matchSpecial(nextChar) || matchIgnore(nextChar)) {
				foundToken = true;
				popTokenContext();
			}
		}
		else if (matchOpenCharacterSet(curChar)) {
			pushCharacterSetContext();
			addToToken(curChar);
			foundToken = true;
		}
		else if (matchNumberStartChar(curChar)) {
			addToToken(curChar);
			pushNumberContext();
		}
		else if (matchSpecial(curChar)) {
			addToToken(curChar);
			foundToken = true;
		}
		else if (matchQuote(curChar)) {
			pushStringContext(curChar);
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
		contexts = new Stack<TokenContext>();
		contexts.push(TokenContext.NOCONTEXT);
		
	}
	
	private boolean isInStringContext() {
		return contexts.peek() == TokenContext.STRING;
	}
	
	private boolean isInCharacterSetContext() {
		return contexts.peek() == TokenContext.CHARACTERSET;
	}
	
	private boolean isInNumberContext() {
		return contexts.peek() == TokenContext.NUMBER;
	}
	
	private boolean matchCloseCharacterSet(char feedChar) {
		return feedChar == Lex.RightSquareBrace;
	}
	
	private boolean matchOpenCharacterSet(char feedChar) {
		return feedChar == Lex.LeftSquareBrace;
	}
	
	private void popTokenContext() {
		contexts.pop();
		quote = Character.MIN_VALUE;
	}
	
	private void pushStringContext(char quoteChar) {
		contexts.add(TokenContext.STRING);
		quote = quoteChar;
	}
	
	private void pushCharacterSetContext() {
		contexts.add(TokenContext.CHARACTERSET);
	}
	
	private void pushNumberContext() {
		contexts.add(TokenContext.NUMBER);
	}
	
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
	
	private boolean matchNumberStartChar(char ch) {
		return Lex.matchNumberStartChar(ch);
	}
}
