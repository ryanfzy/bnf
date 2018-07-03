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
	boolean escapeNextChar;
	
	public TokenBuilder() {
		init();
	}
	
	public boolean feed(char curChar, char nextChar) {
		if (foundToken)
			resetToken();
		
		if (isInStringContext()) {
			if (matchEscapeCharacter(curChar))
				escapeNextChar = true;
			else {
				addToToken(curChar);
				if (curChar == quote) {
					if (escapeNextChar)
						escapeNextChar = false;
					else {
						popTokenContext();
						foundToken = true;
					}
				}
			}
		}
		else if (isInCharacterSetContext()) {
			addToToken(curChar);
			if (Lex.matchNumberStartChar(curChar))
				pushNumberContext();
			else {
				foundToken = true;
				if (matchCloseCharacterSet(curChar))
					popTokenContext();
			}
		}
		else if (isInNumberContext()) {
			addToToken(curChar);
			if (Lex.matchSpecialInNumberContext(nextChar) || Lex.matchIgnore(nextChar)) {
				foundToken = true;
				popTokenContext();
			}
		}
		else if (matchEscapeCharacter(curChar)) {
			escapeNextChar = true;
		}
		else if (matchOpenCharacterSet(curChar)) {
			pushCharacterSetContext();
			addToToken(curChar);
			foundToken = true;
		}
		else if (!escapeNextChar && Lex.matchNumberStartChar(curChar)) {
			addToToken(curChar);
			pushNumberContext();
		}
		else if (!escapeNextChar && Lex.matchQuote(curChar)) {
			pushStringContext(curChar);
			addToToken(curChar);
		}
		else if (Lex.matchSpecial(curChar)) {
			if (escapeNextChar)
				escapeNextChar = false;
			addToToken(curChar);
			foundToken = true;
		}
		else if (!Lex.matchIgnore(curChar)) {
			addToToken(curChar);
			if (Lex.matchSpecial(nextChar) || Lex.matchIgnore(nextChar))
				foundToken = true;
		}
		
		return foundToken && hasToken();
	}
	
	public IToken createToken(int col, int row) {
		return new Token(getTokenName(), getTokenType(), col, row);
	}
	
	private void init() {
		strBuilder = new StringBuilder();
		foundToken = false;
		contexts = new Stack<TokenContext>();
		contexts.push(TokenContext.NOCONTEXT);
		escapeNextChar = false;
		
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
	
	private boolean matchCloseCharacterSet(char ch) {
		return ch == Lex.RightSquareBrace;
	}
	
	private boolean matchOpenCharacterSet(char ch) {
		return ch == Lex.LeftSquareBrace;
	}
	
	private boolean matchEscapeCharacter(char ch) {
		return ch == Lex.Escape;
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
		quote = Character.MIN_VALUE;
	}
	
	private void addToToken(char ch) {
		strBuilder.append(ch);
	}
	
	private boolean hasToken() {
		return strBuilder.toString().trim().length() >  0;
	}
	
	private String getTokenName() {
		if (getTokenType() == TokenType.STRING) {
			return strBuilder.substring(1, strBuilder.length() - 1);
		}
		else if (getTokenType() == TokenType.NUMBER) {
			return strBuilder.substring(1);
		}
		return strBuilder.toString();
	}
	
	private TokenType getTokenType() {
		return Lex.getTokenType(strBuilder.toString());
	}
}
