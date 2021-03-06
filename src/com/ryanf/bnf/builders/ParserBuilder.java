package com.ryanf.bnf.builders;

import com.ryanf.bnf.Parser;
import com.ryanf.bnf.interfaces.IParser;
import com.ryanf.bnf.interfaces.ISource;
import com.ryanf.bnf.interfaces.ITokens;

public class ParserBuilder {
	public static Parser createParser(String fileName) {
		ISource source = SourceBuilder.createSource(fileName);
		ITokens tokens = TokensBuilder.createTokens(source);
		return new Parser(tokens);
	}
	
	public static IParser createParserFromString(String str) {
		ISource source = SourceBuilder.createStringSource(str);
		ITokens tokens = TokensBuilder.createTokens(source);
		return new Parser(tokens);
	}
}
