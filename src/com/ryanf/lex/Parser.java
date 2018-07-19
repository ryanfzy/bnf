package com.ryanf.lex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
	private static String TOKEN_LINE_PATTERN = "^(.+?)=(.+?)$";
	public static Lex parseFile(String fileName) {
		Vector<Token> tokens = new Vector<Token>();
		try {
			List<String> allLines = Files.readAllLines(Paths.get(fileName));
			for(String line : allLines) {
				Token token = parseString(line);
				if (token != null)
					tokens.add(token);
			}
		}
		catch (IOException ex) {
		}
		return new Lex(tokens);
	}
	
	public static Token parseString(String str) {
		Pattern p = Pattern.compile(TOKEN_LINE_PATTERN);
		Matcher m = p.matcher(str);
		if (m.find() && m.groupCount() >= 2)
			return new Token(m.group(1).trim(), m.group(2).trim());
		return null;
	}
}
