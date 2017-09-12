package com.ryanf.bnf.interfaces;

import com.ryanf.bnf.exceptions.OutOfCharException;

public interface ISource {
	public char getChar();
	public int getColumn();
	public int getLine();
	public boolean hasMore();
	public char peek(int pos) throws OutOfCharException;
	
	public void next() throws OutOfCharException;
	public void previous();
}
