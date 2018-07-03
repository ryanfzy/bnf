package com.ryanf.bnf;

import com.ryanf.bnf.types.TokenType;

public class Lex {
	public static char Assign = '=';
	public static char Star = '*';
	public static char LeftBrace = '(';
	public static char RightBrace = ')';
	public static char Question = '?';
	public static char HorBar = '-';
	public static char VerBar = '|';
	public static char LeftSquareBrace = '[';
	public static char RightSquareBrace = ']';
	public static char Hash = '#';
	public static char Hat = '^';
	public static char Quote = '\'';
	public static char DoubleQuote = '"';
	public static char Comma = ',';
	public static char SemiColon = ';';
	public static char Plus = '+';
	public static char Escape = '\\';
	
	public static String EmptyNode = "e";
	
	public static boolean matchQuote(char ch) {
		return ch == Lex.Quote || ch == Lex.DoubleQuote;
	}

	public static boolean matchSpecial(char ch) {
		if (ch == Lex.Assign
			|| ch == Lex.Star
			|| ch == Lex.LeftBrace
			|| ch == Lex.RightBrace
			|| ch == Lex.Question
			|| ch == Lex.VerBar
			|| ch == Lex.LeftSquareBrace
			|| ch == Lex.RightSquareBrace
			|| ch == Lex.Hash
			|| ch == Lex.Hat
			|| ch == Lex.SemiColon
			|| ch == Lex.Comma
			|| ch == Lex.Plus
			|| ch == Lex.Quote
			|| ch == Lex.DoubleQuote)
			return true;
		return false;
	}
	
	public static boolean matchSpecialInNumberContext(char ch) {
		return matchSpecial(ch) || ch == Lex.HorBar;
	}
	
	public static boolean matchSeparator(char ch) {
		return ch == Lex.Comma;
	}
	
	public static boolean matchIgnore(char ch) {
		return Character.isWhitespace(ch);
	}
	
	public static boolean matchDigit(char ch) {
		return Character.isDigit(ch);
	}
	
	public static boolean matchNumberStartChar(char ch) {
		return ch == Lex.Hash;
	}
	
	public static TokenType getTokenType(String tokenName) {
		if (tokenName.length() > 1 && (tokenName.startsWith("'") || tokenName.startsWith("\"")))
			return TokenType.STRING;
		else if (tokenName.length() > 1 && tokenName.startsWith("#"))
			return TokenType.NUMBER;
		else if (tokenName.equals("="))
			return TokenType.ASSIGN;
		else if (tokenName.equals("*"))
			return TokenType.STAR;
		else if (tokenName.equals("+"))
			return TokenType.PLUS;
		else if (tokenName.equals("("))
			return TokenType.LEFTBRACE;
		else if (tokenName.equals(")"))
			return TokenType.RIGHTBRACE;
		else if (tokenName.equals("?"))
			return TokenType.QUESTION;
		else if (tokenName.equals("-"))
			return TokenType.HBAR;
		else if (tokenName.equals("|"))
			return TokenType.VBAR;
		else if (tokenName.equals("["))
			return TokenType.LEFTSQUAREBRACE;
		else if (tokenName.equals("]"))
			return TokenType.RIGHTSQUAREBRACE;
		else if (tokenName.equals("#"))
			return TokenType.HASH;
		else if (tokenName.equals("^"))
			return TokenType.HAT;
		else if (tokenName.equals(";"))
			return TokenType.SEMICOLON;
		else if (tokenName.equals(","))
			return TokenType.COMMA;
		else if (tokenName.equals("'"))
			return TokenType.QUOTE;
		else if (tokenName.equals("\""))
			return TokenType.DOUBLEQUOTE;
		else
			return TokenType.IDENTIFIER;
	}
}
