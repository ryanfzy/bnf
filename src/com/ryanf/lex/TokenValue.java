package com.ryanf.lex;

import java.util.Vector;

import com.ryanf.lex.types.ValueType;

public abstract class TokenValue {
	
	public TokenValue() {
	}
	
	public abstract int addToTableFirst(LexTable table, int lastIndex);
	public abstract int addToTable(LexTable table, int lastIndex);
	public abstract void addToTableLast(LexTable table, int lastIndex);
	public abstract void addToTableFirstLast(LexTable table);
}
