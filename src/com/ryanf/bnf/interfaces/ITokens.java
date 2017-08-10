package com.ryanf.bnf.interfaces;

public interface ITokens {
	public IToken getToken();
	public void next();
	public void previous();
	public boolean hasMore();
}
