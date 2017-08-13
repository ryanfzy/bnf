package com.ryanf.bnf.interfaces;

public interface ITokens {
	public IToken getToken();
	public void next();
	public void previous();
	public boolean hasMore();
	public int getPos();
	public void setPos(int pos);
}
