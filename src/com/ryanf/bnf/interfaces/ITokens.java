package com.ryanf.bnf.interfaces;

public interface ITokens {
	public IToken getToken();
	public boolean hasMore();
	public int getPos();
	public IToken peek(int pos);
	
	public void next();
	public void previous();
	public void seek(int pos);
}
