package com.ryanf.bnf.interfaces;

import com.ryanf.bnf.exceptions.OutOfCharException;

public interface ISource {
	public char getChar();
	public int getColumn();
	public int getRow();
	
	public void next() throws OutOfCharException;
	public void previous();
	
	public boolean hasMore();
	
	public char lookAhead(int pos) throws OutOfCharException;
}
