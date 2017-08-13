package com.ryanf.bnf.types;

public class TokenPos {
	int column;
	int row;
	
	public TokenPos(int column, int row) {
		this.column = column;
		this.row = row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getRow() {
		return row;
	}
}
