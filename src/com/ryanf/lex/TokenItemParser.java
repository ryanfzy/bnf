package com.ryanf.lex;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ryanf.lex.types.ValueType;

public class TokenItemParser {
	private String input;
	private int pos;
	
	private static String ANY_OR_MORE_PATTERN = "^\\[.*\\]\\+";
	private static String RANGE_PATTERN = "^.-.";
	
	public TokenItemParser(String input) {
		this.input = input;
		this.pos = 0;
	}
	
	public TokenItem Next() {
		if (pos >= input.length())
			return null;
		return getNext(input.substring(pos));
	}
	
	private TokenItem getNext(String inputValue) {
		String itemValue = "";
		ValueType itemType = ValueType.Single;
		Pattern p = Pattern.compile(ANY_OR_MORE_PATTERN);
		Matcher m = p.matcher(inputValue);
		if (m.find()) {
			itemValue = inputValue.substring(0, m.end());
			itemValue = itemValue.substring(1, itemValue.length()-2);
			pos += m.end();
			itemType = ValueType.AnyOneOrMore;
			return new TokenItem(itemValue, itemType);
		}
		p = Pattern.compile(RANGE_PATTERN);
		m = p.matcher(inputValue);
		if (m.find()) {
			itemValue = inputValue.substring(0, m.end());
			pos += m.end();
			return new TokenItem(itemValue, itemType);
		}
		if (inputValue.startsWith("#x")) {
			itemValue = inputValue.split("\\s+")[0];
			pos += (itemValue.length() + 1);
		}
		else {
			itemValue = input.substring(pos, pos+1);
			pos += 1;
		}
		return new TokenItem(itemValue, itemType);
	}
}
