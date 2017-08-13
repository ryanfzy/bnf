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
	int tokenRow;
	int tokenCol;
	
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
	
	public int getPos() {
		return pos;
	}
	
	public void setPos(int pos) {
		this.pos = pos;
	}
	
	public boolean hasMore() {
		return source.hasMore();
	}
	
	private IToken getNext() {
		clearToken();
		boolean foundQuote = false;
		char quoteChar = ' ';
		while (source.hasMore()) {
			try {
				source.next();
			} catch (Exception e) {
				return null;
			}
			foundQuote ^= matchQuote(quoteChar);
			if (foundQuote) {
				addToToken();
				continue;
			}
			else if (!matchIgnore()) {
				if (!matchSpecial()) {
					addToToken();
					if (!matchSpecialOneAhead())
						continue;
				}
				else
					addToToken();
			}
			if (hasToken())
				break;
		}
		return TokenBuilder.createToken(getTokenName(), getTokenType(), tokenRow, tokenCol);
	}
	
	private void init() {
		builder = new StringBuilder();
		tokens = new Vector<IToken>();
		pos = -1;
	}
	
	private void clearToken() {
		builder = new StringBuilder();
		tokenRow = -1;
		tokenCol = -1;
	}
	
	private void addToToken() {
		builder.append(source.getChar());
		if (tokenRow == -1 && tokenCol == -1) {
			tokenRow = source.getRow();
			tokenCol = source.getColumn();
		}
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
	
	private boolean matchSpecialOneAhead() {
		try {
			return Lex.matchSpecial(source.lookAhead(1));
		} catch (Exception e) {
			return false;
		}
	}
	
	private boolean matchQuote(char quoteChar) {
		return Lex.matchQuote(source.getChar()) && source.getChar() == quoteChar;
	}
	
	private boolean matchIgnore() {
		return Lex.matchIgnore(source.getChar());
	}
	
	private TokenType getTokenType() {
		return Lex.getTokenType(getTokenName());
	}
}
