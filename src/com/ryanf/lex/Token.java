package com.ryanf.lex;

import java.util.Vector;
import java.util.regex.Pattern;

import com.ryanf.lex.types.ValueType;

public class Token {
	private String name;
	//private Vector<Long> values;
	//private ValueType valueType;
	private Vector<TokenValue> values;
	
	public Token(String name, String value) {
		this.name = name;
		//values = new Vector<Long>();
		values = new Vector<TokenValue>();
		//valueType = ValueType.Single;
		
		parseValue(value);
	}
	
	public String Name() {
		return name;
	}
	
	public Vector<TokenValue> Values(){
		return values;
	}
	
	/*
	public Vector<Long> Values() {
		return values;
	}
	
	public ValueType ValueType() {
		return valueType;
	}*/
	
	// TODO: change this function
	private void parseValue(String value) {
		ValueType valueType = ValueType.Single;
		if (isAnyOneOrMoreValue(value)) {
			valueType = ValueType.AnyOneOrMore;
			value = value.substring(1, value.length()-2);
		}
		Vector<Long> tokenValues = parseValues(value);
		if (valueType == ValueType.AnyOneOrMore)
			values.add(new MultiOneOrMoreTokenValue(tokenValues));
			//values.add(new TokenValue(tokenValues, valueType));
		else {
			for (long tokenValue : tokenValues)
				values.add(new SingleOneTokenValue(tokenValue));
				//values.add(new TokenValue(tokenValue, valueType));
		}
	}
	
	private Vector<Long> parseValues(String value){
		Vector<Long> tokenValues = new Vector<Long>();
		String[] parts = value.split("\\s+");
		for (String part : parts) {
			if (part.startsWith("#x")) {
				tokenValues.add(Long.parseLong(part.replace("#x", ""), 16));
			}
			else {
				for (char ch : value.toCharArray())
					tokenValues.add((long)ch);
			}
		}
		return tokenValues;
	}
	
	private boolean isAnyOneOrMoreValue(String value) {
		String pat = "\\[.*\\]\\+";
		return Pattern.matches(pat, value);
	}
	
	public void addToTable(LexTable table) {
		table.addTokenName(Name());
		if (values.size() == 1)
			values.get(0).addToTableFirstLast(table);
		else {
			int lastValue = LexTable.ERROR_NUMBER;
			for (int i = 0; i < values.size(); i++) {
				TokenValue value = values.get(i);
				if (i == 0)
					lastValue = value.addToTableFirst(table, lastValue);
				if (i == values.size()-1)
					value.addToTableLast(table, lastValue);
				if (i > 0 && i < values.size()-1)
					lastValue = value.addToTable(table, lastValue);
			}
		}
	}
}
