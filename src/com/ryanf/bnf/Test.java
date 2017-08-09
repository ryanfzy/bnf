package com.ryanf.bnf;

import com.ryanf.bnf.builders.TokensBuilder;
import com.ryanf.bnf.interfaces.ISource;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.interfaces.ITokens;

public class Test {

	public static void main(String[] args) {
		String filePath = "D:\\eclipse-workspace\\bnf\\xml.txt";
		ISource source = new Source(filePath);

		ITokens tokens = TokensBuilder.getTokens(source);
		while (tokens.hasNext()) {
			IToken token = tokens.getNext();
			System.out.printf("%s\t%s\n", token.getName(), token.getType().toString());
		}
	}
}
