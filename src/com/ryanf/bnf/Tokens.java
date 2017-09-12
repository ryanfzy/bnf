package com.ryanf.bnf;

import java.util.Vector;

import com.ryanf.bnf.builders.TokenBuilder;
import com.ryanf.bnf.interfaces.ISource;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.interfaces.ITokens;

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
	
	public void seek(int pos) {
		this.pos = pos;
	}
	
	public boolean hasMore() {
		return source.hasMore();
	}
	
	public IToken peek(int pos) {
		int originalPos = getPos();
		for (int i = pos; i > 0; i--)
			next();
		IToken token = getToken();
		seek(originalPos);
		return token;
	}
	
	private IToken getNext() {
		int tokenCol = source.getColumn() + 2;
		int tokenLine = source.getLine() + 2;;
		while (source.hasMore()) {
			char nextChar = Character.MIN_VALUE;
			try {
				source.next();
				nextChar = source.peek(1);
			} 
			catch (Exception e) {
			}
			if (tokenBuilder.feed(source.getChar(), nextChar))
				break;
		}
		return tokenBuilder.createToken(tokenCol, tokenLine);
	}
	
	private void init() {
		tokenBuilder = new TokenBuilder();
		tokens = new Vector<IToken>();
		pos = -1;
	}
}
