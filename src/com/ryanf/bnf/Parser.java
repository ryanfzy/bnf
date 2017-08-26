package com.ryanf.bnf;

import java.util.Stack;

import com.ryanf.bnf.builders.ParseTreeBuilder;
import com.ryanf.bnf.exceptions.ParserException;
import com.ryanf.bnf.exceptions.QuantifierNotMatchException;
import com.ryanf.bnf.exceptions.TokenTypeNotMatchException;
import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.interfaces.ITokens;
import com.ryanf.bnf.types.AstNodeType;
import com.ryanf.bnf.types.QuantifierType;
import com.ryanf.bnf.types.TokenType;

public class Parser {
	ITokens tokens;
	IAstNode statListNode;
	Stack<IAstNode> astNodes;
	
	public Parser(ITokens tokens) {
		this.tokens = tokens;
		init();
	}
	
	private void init() {
		astNodes = new Stack<IAstNode>();
	}
	
	public void parse() throws ParserException {
		//addStatListNode();
		tokens.next();
		while (tokens.hasMore()) {
			parseRule();
			//addAstNode(popAstNode());
		}
	}
	
	private void parseRule() throws ParserException {
		if (lookAheadType(1) == TokenType.ASSIGN) {
			//pushAstNode(ParseTreeBuilder.createAsignStatNode());
			matchLhs();
			match(TokenType.ASSIGN);
			matchRhs();
			match(TokenType.SEMICOLON);
		}
		else
			throw new TokenTypeNotMatchException(tokens.getToken(), TokenType.ASSIGN, getTokenType());
	}
	
	private void matchLhs() throws ParserException {
		//addAstNode(ParseTreeBuilder.createIdentNode(tokens.getToken()));
		match(TokenType.IDENTIFIER);
	}
	
	private void matchRhs() throws ParserException {
		if (getTokenType() == TokenType.COMMA) {
			match(TokenType.COMMA);
			matchRhs();
		}
		else if (getTokenType() == TokenType.VBAR) {
			match(TokenType.VBAR);
			matchRhs();
		}
		else if (getTokenType() == TokenType.HBAR) {  // match subtract expression
			//pushAstNode(ParseTreeBuilder.createSubStatNode());
			match(TokenType.HBAR);
			matchRhsElem();
			//addAstNode(popAstNode());
		}
		else {
			matchRhsElem();
			if (getTokenType() == TokenType.COMMA || getTokenType() == TokenType.VBAR || getTokenType() == TokenType.HBAR)
				matchRhs();
		}
		
		//if (lookAheadType() == TokenType.SEMICOLON)
			//return astNodes.pop();
		//return null;
	}
	
	private void matchRhsElem() throws ParserException {
		if (getTokenType() == TokenType.NUMBER) {
			//addAstNode(ParseTreeBuilder.createNumberNode(tokens.getToken()));
			match(TokenType.NUMBER);
		}
		else if (getTokenType() == TokenType.STRING) {
			//addAstNode(ParseTreeBuilder.createStrNode(tokens.getToken()));
			match(TokenType.STRING);
		}
		else if (getTokenType() == TokenType.LEFTBRACE) {
			match(TokenType.LEFTBRACE);
			matchRhs();
			match(TokenType.RIGHTBRACE);
			if (isQuantifier())
				matchQuantifier();
			//addAstNode(popAstNode());
		}
		else if (getTokenType() == TokenType.LEFTSQUAREBRACE) {
			match(TokenType.LEFTSQUAREBRACE);
			matchCharList();
			match(TokenType.RIGHTSQUAREBRACE);
			if (isQuantifier())
				matchQuantifier();
			//addAstNode(popAstNode());
		}
		else if (getTokenType() == TokenType.IDENTIFIER) {
			//pushAstNode(ParseTreeBuilder.createIdentNode(tokens.getToken()));
			match(TokenType.IDENTIFIER);
			if (isQuantifier())
				matchQuantifier();
			//addAstNode(popAstNode());
		}
		else
			throw new TokenTypeNotMatchException(tokens.getToken(), TokenType.NOTYPE, getTokenType());
	}
	
	private void matchQuantifier() throws ParserException {
		if (getTokenType() == TokenType.QUESTION) {
			//setAstNodeQuantifier(QuantifierType.ZERO_OR_ONE);
			match(TokenType.QUESTION);
		}
		else if (getTokenType() == TokenType.PLUS) {
			//setAstNodeQuantifier(QuantifierType.ONE_OR_MORE);
			match(TokenType.PLUS);
		}
		else if (getTokenType() == TokenType.STAR) {
			//setAstNodeQuantifier(QuantifierType.ZERO_OR_MORE);
			match(TokenType.STAR);
		}
		else
			throw new QuantifierNotMatchException();
	}
	
	private void matchCharList() throws ParserException {
		if (getTokenType() != TokenType.RIGHTSQUAREBRACE) {
			//addCharListNodeIfRequired();
			//addAstNode(ParseTreeBuilder.createCharNode(tokens.getToken()));
			match(getTokenType());
		}
		if (getTokenType() != TokenType.RIGHTSQUAREBRACE)
			matchCharList();
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
		return isQuantifier(getTokenType());
	}
	
	private boolean isQuantifier(TokenType type) {
		return type == TokenType.QUESTION || type == TokenType.PLUS || type == TokenType.STAR;
	}
	
	private TokenType lookAheadType(int pos) {
		//return tokens.getToken().getType();
		return tokens.lookAhead(pos).getType();
	}
	
	private void pushAstNode(IAstNode node) {
		astNodes.push(node);
	}
	
	private IAstNode popAstNode() {
		return astNodes.pop();
	}
	
	private void addAstNode(IAstNode node) {
		astNodes.peek().addChild(node);
	}
	
	private IAstNode getAstNode() {
		return astNodes.peek();
	}
	
	private void setAstNodeQuantifier(QuantifierType type) {
		getAstNode().setQuantifier(type);
	}
	
	private void addStatListNode() {
		statListNode = ParseTreeBuilder.createStatListNode();
	}
	
	private void addListNodeIfRequired() {
		if (astNodes.peek().getType() != AstNodeType.NODELIST)
			pushAstNode(ParseTreeBuilder.createListNode());
	}
	
	private void addCharListNodeIfRequired() {
		if (astNodes.peek().getType() != AstNodeType.CHARLIST)
			pushAstNode(ParseTreeBuilder.createCharListNode());
	}
	
	private void addAlterListNodeIfRequired() {
		if (astNodes.peek().getType() != AstNodeType.ALTERNODELIST)
			pushAstNode(ParseTreeBuilder.createAlterListNode());
	}
}
