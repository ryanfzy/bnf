package com.ryanf.lex;

public class ValueItem {
	private TokenValue parent;
	private long value;
	
	public ValueItem(TokenValue parent, long value) {
		this.parent = parent;
		this.value = value;
	}
	
	public long Value() {
		return value;
	}
	
	public TokenValue Parent() {
		return parent;
	}
}
