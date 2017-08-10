package com.ryanf.bnf.builders;

import com.ryanf.bnf.Tokens;
import com.ryanf.bnf.interfaces.ISource;
import com.ryanf.bnf.interfaces.ITokens;

public class TokensBuilder {
	public static ITokens createTokens(ISource source) {
		return new Tokens(source);
	}
}
