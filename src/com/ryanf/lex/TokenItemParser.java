package com.ryanf.lex;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ryanf.lex.types.ValueType;

public class TokenItemParser {
	private String input;
	private int pos;
	
	private static String ANYSET_PATTERN = "^\\[.*?\\]";
	private static String ANYSET_ONE_OR_MORE_PATTERN = "^\\[.*?\\]\\+";
	private static String MULTIANYSET_ZOER_OR_MORE_PATTERN = "^\\(.*?\\)\\*";
	private static String RANGE_PATTERN = "^.-.";
	private static String OR_CHARACTER = "|";
	
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
		ValueType itemType = ValueType.SingleCharacter;
		Pattern p = Pattern.compile(ANYSET_ONE_OR_MORE_PATTERN);
		Matcher m = p.matcher(inputValue);
		if (m.find()) {
			itemValue = inputValue.substring(0, m.end());
			itemValue = itemValue.substring(1, itemValue.length()-2);
			pos += m.end();
			itemType = ValueType.AnySetOneOrMore;
			return new TokenItem(itemValue, itemType);
		}
		p = Pattern.compile(ANYSET_PATTERN);
		m = p.matcher(inputValue);
		if (m.find()) {
			itemValue = inputValue.substring(0, m.end());
			itemValue = itemValue.substring(1, itemValue.length()-1);
			pos += m.end();
			itemType = ValueType.AnySet;
			return new TokenItem(itemValue, itemType);
		}
		p = Pattern.compile(MULTIANYSET_ZOER_OR_MORE_PATTERN);
		m = p.matcher(inputValue);
		if (m.find()) {
			itemValue = inputValue.substring(0, m.end());
			itemValue = itemValue.substring(1, itemValue.length()-2);
			pos += m.end();
			itemType = ValueType.MultiAnySetZeroOrMore;
			return new TokenItem(itemValue, itemType);
		}
		p = Pattern.compile(RANGE_PATTERN);
		m = p.matcher(inputValue);
		if (m.find()) {
			itemValue = inputValue.substring(0, m.end());
			pos += m.end();
			itemType = ValueType.CharacterRange;
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
		if (itemValue.trim().isEmpty() || itemValue.equals(OR_CHARACTER))
			return Next();
		else
			return new TokenItem(itemValue, itemType);
	}
}
