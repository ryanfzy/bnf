package com.ryanf.bnf;

import com.ryanf.bnf.builders.ParserBuilder;
import com.ryanf.bnf.builders.TokensBuilder;
import com.ryanf.bnf.interfaces.ISource;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.interfaces.ITokens;

public class Test {

	public static void main(String[] args) {
		String filePath = "D:\\eclipse-workspace\\bnf\\xml.txt";
	
		Parser parser = ParserBuilder.createParser(filePath);
		parser.parse();
	}
}
