package com.ryanf.bnf.types;

public class TokenPos {
	int column;
	int line;
	
	public TokenPos(int column, int line) {
		this.column = column;
		this.line = line;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getLine() {
		return line;
	}
}
