package com.ryanf.lex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Vector;

public class Parser {
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
		String[] parts = str.split("=");
		if (parts.length == 2) {
			return new Token(parts[0].trim(), parts[1].trim());
		}
		return null;
	}
}
