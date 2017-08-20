package com.ryanf.bnf;

import java.util.Vector;

import com.ryanf.bnf.builders.TokenBuilder;
import com.ryanf.bnf.exceptions.OutOfCharException;
import com.ryanf.bnf.interfaces.ISource;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.interfaces.ITokens;
import com.ryanf.bnf.types.TokenContext;

public class Tokens implements ITokens {
	ISource source;
	Vector<IToken> tokens;
	int pos;
	TokenBuilder tokenBuilder;
	
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
	
	public IToken lookAhead(int pos) {
		int originalPos = getPos();
		while (pos > 0) {
			pos--;
			next();
		}
		IToken token = getToken();
		setPos(originalPos);
		return token;
	}
	
	private IToken getNext() {
		int tokenCol = -1;
		int tokenRow = -1;;
		while (source.hasMore()) {
			try {
				source.next();
				if (tokenCol == -1 && tokenRow == -1) {
					tokenCol = source.getColumn() + 1;
					tokenRow = source.getRow() + 1;
				}
				char nextChar = ' ';
				try {
					nextChar = source.lookAhead(1);
				}
				catch (Exception e) {}
				if (tokenBuilder.feed(source.getChar(), nextChar))
					break;
			} catch (Exception e) {
			}
		}
		return tokenBuilder.createToken(tokenCol, tokenRow);
	}
	
	private void init() {
		tokenBuilder = new TokenBuilder();
		tokens = new Vector<IToken>();
		pos = -1;
	}
}
