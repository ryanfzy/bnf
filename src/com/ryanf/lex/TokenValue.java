package com.ryanf.lex;

import java.util.Vector;

import com.ryanf.lex.types.ValueType;

public abstract class TokenValue {
	//private Vector<Long> values;
	//protected ValueType type;
	
	public TokenValue() {
		//this.type = type;
	}
	
	public abstract int addToTableFirst(LexTable table, int lastIndex);
	public abstract int addToTable(LexTable table, int lastIndex);
	public abstract void addToTableLast(LexTable table, int lastIndex);
	public abstract void addToTableFirstLast(LexTable table);
	
	/*
	public TokenValue(long value, ValueType type) {
		values = new Vector<Long>();
		values.add(value);
		this.type = type;
	}
	
	public TokenValue(Vector<Long> values, ValueType type) {
		this.values = values;
		this.type = type;
	}
	
	public ValueType Type() {
		return type;
	}
	
	public Vector<Long> Values() {
		return values;
	}*/
}
