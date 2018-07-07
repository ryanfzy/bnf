package com.ryanf.lex;

import com.ryanf.lex.types.ValueType;

public class TokenItem {
	private ValueType type;
	private String value;
	
	public TokenItem(String value, ValueType type) {
		this.value = value;
		this.type = type;
	}
	
	public String Value() {
		return value;
	}
	
	public ValueType Type() {
		return type;
	}
}
