package com.ryanf.bnf.interfaces;

public interface ISource {
	public char getChar();
	public void moveForward();
	public void moveBackward();
	public boolean hasMore();
}
