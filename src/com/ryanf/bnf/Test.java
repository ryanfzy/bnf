package com.ryanf.bnf;

import com.ryanf.bnf.builders.ParserBuilder;
import com.ryanf.bnf.builders.SourceBuilder;
import com.ryanf.bnf.builders.TokensBuilder;
import com.ryanf.bnf.exceptions.ParserException;
import com.ryanf.bnf.interfaces.ISource;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.interfaces.ITokens;

public class Test {

	public static void main(String[] args) {
		String filePath = "D:\\eclipse-workspace\\bnf\\xml.txt";
	
		/*
		ITokens tokens = TokensBuilder.createTokens(SourceBuilder.createSource(filePath));
		while (tokens.hasMore()) {
			tokens.next();
			IToken token = tokens.getToken();
			System.out.println(token.toString());
		}*/
		
		Parser parser = ParserBuilder.createParser(filePath);
		try {
			parser.parse();
		} catch (ParserException e) {
			System.out.println(e.toString());
		}
	}
}
