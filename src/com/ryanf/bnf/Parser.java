package com.ryanf.bnf;

import com.ryanf.bnf.exceptions.ParserException;
import com.ryanf.bnf.exceptions.QuantifierNotMatchException;
import com.ryanf.bnf.exceptions.TokenTypeNotMatchException;
import com.ryanf.bnf.interfaces.ITokens;
import com.ryanf.bnf.types.TokenType;

public class Parser {
	ITokens tokens;
	
	public Parser(ITokens tokens) {
		this.tokens = tokens;
	}
	
	public void parse() throws ParserException {
		tokens.next();
		while (tokens.hasMore()) {
			parseRule();
		}
	}
	
	private void parseRule() throws ParserException {
		matchLhs();
		match(TokenType.ASSIGN);
		matchRhs();
		match(TokenType.SEMICOLON);
	}
	
	private void matchLhs() throws ParserException {
		match(TokenType.IDENTIFIER);
	}
	
	private void matchRhs() throws ParserException {
		matchRhsElem();
		if (getTokenType() == TokenType.COMMA) {
			match(TokenType.COMMA);
			matchRhs();
		}
		else if (getTokenType() == TokenType.VBAR) {
			match(TokenType.VBAR);
			matchRhs();
		}
		else if (getTokenType() == TokenType.HBAR) {  // match "-", which is minus in this case
			match(TokenType.HBAR);
			matchRhs();
		}
	}
	
	private void matchRhsElem() throws ParserException {
		if (getTokenType() == TokenType.NUMBER)
			match(TokenType.NUMBER);
		else if (getTokenType() == TokenType.STRING)
			match(TokenType.STRING);
		else if (getTokenType() == TokenType.LEFTBRACE || getTokenType() == TokenType.LEFTSQUAREBRACE)
			matchRegex();
		else
			matchIdentifierWithMultifier();
	}
	
	private void matchRegex() throws ParserException {
		matchRegExpr();
		if (isQuantifier())
			matchQuantifier();
	}
	
	private void matchQuantifier() throws ParserException {
		if (getTokenType() == TokenType.QUESTION)
			match(TokenType.QUESTION);
		else if (getTokenType() == TokenType.PLUS)
			match(TokenType.PLUS);
		else if (getTokenType() == TokenType.STAR)
			match(TokenType.STAR);
		else
			throw new QuantifierNotMatchException();
	}
	
	private void matchRegExpr() throws ParserException {
		if (getTokenType() == TokenType.LEFTSQUAREBRACE) {
			match(TokenType.LEFTSQUAREBRACE);
			matchCharList();
			match(TokenType.RIGHTSQUAREBRACE);
		}
		else if (getTokenType() == TokenType.LEFTBRACE) {
			match(TokenType.LEFTBRACE);
			matchRhs();
			match(TokenType.RIGHTBRACE);
		}
	}
	
	private void matchCharList() throws ParserException {
		if (getTokenType() != TokenType.RIGHTSQUAREBRACE)
			match(getTokenType());
		if (getTokenType() != TokenType.RIGHTSQUAREBRACE)
			matchCharList();
	}
	
	private void matchIdentifierWithMultifier() throws ParserException {
		match(TokenType.IDENTIFIER);
		if (isQuantifier())
			matchQuantifier();
	}
	
	private void match(TokenType type) throws ParserException {
		if (getTokenType() == type)
			consume();
		else
			throw new TokenTypeNotMatchException(tokens.getToken(), type, getTokenType());
	}
	
	private void consume() {
		tokens.next();
	}
	
	private TokenType getTokenType() {
		return tokens.getToken().getType();
	}
	
	private boolean isQuantifier() {
		return getTokenType() == TokenType.QUESTION || getTokenType() == TokenType.PLUS || getTokenType() == TokenType.STAR;
	}
}
