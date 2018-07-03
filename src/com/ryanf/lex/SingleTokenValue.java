package com.ryanf.lex;

import com.ryanf.lex.types.ValueType;

public abstract class SingleTokenValue extends TokenValue {
	protected long value;
	
	public SingleTokenValue(long value) {
		this.value = value;
	}
	
	public long Value() {
		return value;
	}
}
