package com.ryanf.lex;

import java.util.Vector;
import java.util.regex.Pattern;

import com.ryanf.lex.types.ValueType;

public class Token {
	private String name;
	private Vector<TokenValue> values;
	
	public Token(String name, String value) {
		this.name = name;
		values = new Vector<TokenValue>();
		
		parseValue(value);
	}
	
	public String Name() {
		return name;
	}
	
	public Vector<TokenValue> Values(){
		return values;
	}
	
	private void parseValue(String value) {
		TokenItemParser parser = new TokenItemParser(value);
		TokenItem item = parser.Next();
		while (item != null) {
			if (item.Type() == ValueType.AnyOneOrMore)
				values.add(new MultiOneOrMoreTokenValue(getValues(item.Value())));
			else
				values.add(new SingleOneTokenValue(getValue(item.Value())));
			item = parser.Next();
		}
	}
	
	private Vector<Long> getValues(String value) {
		Vector<Long> values = new Vector<Long>();
		TokenItemParser parser = new TokenItemParser(value);
		TokenItem item = parser.Next();
		while (item != null) {
			String[] parts = item.Value().split("-");
			if (parts.length == 2) {
				long endValue = getValue(parts[1]) + 1;
				for (long startValue = getValue(parts[0]); startValue < endValue; startValue++)
					values.add(startValue);
			}
			else
				values.add(getValue(item.Value()));
			item = parser.Next();
		}
		return values;
	}
	
	private Long getValue(String value) {
		if (value.startsWith("#x"))
			return Long.parseLong(value.replace("#x", ""), 16);
		return (long)value.toCharArray()[0];
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
