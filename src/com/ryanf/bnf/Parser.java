package com.ryanf.bnf;

import java.util.Stack;

import com.ryanf.bnf.exceptions.IdentifierNotMatchException;
import com.ryanf.bnf.exceptions.MultifierNotMatchException;
import com.ryanf.bnf.exceptions.ParserException;
import com.ryanf.bnf.exceptions.QuantifierNotMatchException;
import com.ryanf.bnf.exceptions.RhsNotMatchException;
import com.ryanf.bnf.exceptions.TokenTypeNotMatchException;
import com.ryanf.bnf.interfaces.ITokens;

public class Parser {
	ITokens tokens;
	//Stack<Integer> tpStack;
	
	public Parser(ITokens tokens) {
		this.tokens = tokens;
		//init();
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
		matchIdentifier();
	}
	
	private void matchRhs() throws ParserException {
		matchRhsElem();
		if (getTokenType() == TokenType.COMMA)
		{
			match(TokenType.COMMA);
			matchRhs();
		}
	}
	
	private void matchRhsElem() throws ParserException {
		if (getTokenType() == TokenType.STRING)
			matchTerminal();
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
			if (getTokenType() == TokenType.VBAR) {
				match(TokenType.VBAR);
				matchRhs();
			}
			match(TokenType.RIGHTBRACE);
		}
	}
	
	private void matchCharList() throws ParserException {
		match(TokenType.IDENTIFIER);
		if (tokens.lookAhead(1).getType() != TokenType.RIGHTSQUAREBRACE)
			matchCharList();
	}
	
	private void matchIdentifier() throws ParserException {
		match(TokenType.IDENTIFIER);
	}
	
	private void matchIdentifierWithMultifier() throws ParserException {
		matchIdentifier();
		if (isQuantifier())
			matchQuantifier();
	}
	
	private void matchTerminal() throws ParserException {
		match(TokenType.STRING);
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
	
	/*
	private boolean follow(TokenType type) {
		return tokens.lookAhead(1).getType() == type;
	}
	
	private void pushState() {
		tpStack.push(tokens.getPos());
	}
	
	private void popState() {
		tokens.setPos(tpStack.pop());
	}
	
	private void init() {
		tpStack = new Stack<Integer>();
	}*/
}
