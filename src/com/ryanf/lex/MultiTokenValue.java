package com.ryanf.lex;

import java.util.Vector;

import com.ryanf.lex.types.ValueType;

public abstract class MultiTokenValue extends TokenValue {
	protected Vector<Long> values;
	
	public MultiTokenValue(Vector<Long> values) {
		this.values = values;
	}
	
	public Vector<Long> Values() {
		return this.values;
	}
}
