package com.ryanf.lex;

import java.util.Vector;

public class Lex {
	Vector<Token> tokens;
	
	public Lex(Vector<Token> tokens) {
		this.tokens = tokens;
	}
	
	public LexTable getTable() {
		LexTable table = new LexTable();
		for (Token token : tokens) {
			table.addRow(token);
		}
		return table;
	}
}
