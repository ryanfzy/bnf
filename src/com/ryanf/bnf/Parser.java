package com.ryanf.bnf;

import java.util.Stack;

//import com.ryanf.bnf.builders.ParseTreeBuilder;
import com.ryanf.bnf.exceptions.ParserException;
import com.ryanf.bnf.exceptions.QuantifierNotMatchException;
import com.ryanf.bnf.exceptions.TokenTypeNotMatchException;
//import com.ryanf.bnf.interfaces.IAstNode;
//import com.ryanf.bnf.interfaces.IAstTree;
import com.ryanf.bnf.interfaces.IParser;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.interfaces.ITokens;
import com.ryanf.bnf.tree.AlterListNode;
import com.ryanf.bnf.tree.AsignStatNode;
import com.ryanf.bnf.tree.AstNode;
import com.ryanf.bnf.tree.AstTree;
import com.ryanf.bnf.tree.CharListNode;
import com.ryanf.bnf.tree.CharNode;
import com.ryanf.bnf.tree.CharRangeNode;
import com.ryanf.bnf.tree.IdentNode;
import com.ryanf.bnf.tree.ListNode;
import com.ryanf.bnf.tree.NumberNode;
import com.ryanf.bnf.tree.StatListNode;
import com.ryanf.bnf.tree.StrNode;
import com.ryanf.bnf.tree.SubStatNode;
import com.ryanf.bnf.types.QuantifierType;
import com.ryanf.bnf.types.TokenType;

public class Parser implements IParser {
	ITokens tokens;
	//IAstNode statListNode;
	AstNode statListNode;
	//Stack<IAstNode> astNodes;
	Stack<AstNode> astNodes;
	
	public Parser(ITokens tokens) {
		this.tokens = tokens;
	}
	
	private void init() {
		//astNodes = new Stack<IAstNode>();
		//statListNode = ParseTreeBuilder.createStatListNode();
		astNodes = new Stack<AstNode>(); 
		statListNode = new StatListNode();
	}
	
	public /*IAstTree*/AstTree parse() throws ParserException {
		init();
		tokens.next();
		while (tokens.hasMore()) {
			parseRule();
			statListNode.addChild(popAstNode());
		}
		return new AstTree(statListNode);
	}
	
	private void parseRule() throws ParserException {
		if (lookAheadType(1) == TokenType.ASSIGN) {
			pushAstNode(/*ParseTreeBuilder.createAsignStatNode()*/new AsignStatNode());
			matchLhs();
			match(TokenType.ASSIGN);
			matchRhs();
			addAstNode(popAstNode());
			match(TokenType.SEMICOLON);
		}
		else
			throw new TokenTypeNotMatchException(tokens.getToken(), TokenType.ASSIGN, getTokenType());
	}
	
	private void matchLhs() throws ParserException {
		addAstNode(/*ParseTreeBuilder.createIdentNode(getTokenName())*/new IdentNode(getTokenName()));
		match(TokenType.IDENTIFIER);
	}
	
	private void matchRhs() throws ParserException {
		if (getTokenType() == TokenType.COMMA) {
			match(TokenType.COMMA);
			matchRhsElem();
			addAstNode(popAstNode());
			if (getTokenType() != TokenType.SEMICOLON)
				matchRhs();
		}
		else if (getTokenType() == TokenType.VBAR) {
			match(TokenType.VBAR);
			matchRhsElem();
			addAstNode(popAstNode());
			if (getTokenType() != TokenType.SEMICOLON)
				matchRhs();
		}
		else if (getTokenType() == TokenType.HBAR) {  // match subtract expression
			match(TokenType.HBAR);
			matchRhsElem();
			addAstNode(popAstNode());
		}
		else {
			matchRhsElem();
			if (getTokenType() == TokenType.COMMA) {
				/*IAstNode*/AstNode node = popAstNode();
				pushAstNode(/*ParseTreeBuilder.createListNode()*/new ListNode());
				addAstNode(node);
				matchRhs();
			}
			else if (getTokenType() == TokenType.VBAR) {
				/*IAstNode*/AstNode node = popAstNode();
				pushAstNode(/*ParseTreeBuilder.createAlterListNode()*/new AlterListNode());
				addAstNode(node);
				matchRhs();
			}
			else if (getTokenType() == TokenType.HBAR) {
				/*IAstNode*/AstNode left = popAstNode();
				pushAstNode(/*ParseTreeBuilder.createSubStatNode()*/new SubStatNode());
				addAstNode(left);
				matchRhs();
			}
		}
	}
	
	private void matchRhsElem() throws ParserException {
		if (getTokenType() == TokenType.NUMBER) {
			pushAstNode(/*ParseTreeBuilder.createNumberNode(getToken())*/new NumberNode(getTokenName()));
			match(TokenType.NUMBER);
		}
		else if (getTokenType() == TokenType.STRING) {
			pushAstNode(/*ParseTreeBuilder.createStrNode(getToken())*/new StrNode(getTokenName()));
			match(TokenType.STRING);
		}
		else if (getTokenType() == TokenType.LEFTBRACE) {
			match(TokenType.LEFTBRACE);
			matchRhs();
			match(TokenType.RIGHTBRACE);
			if (isQuantifier())
				matchQuantifier();
			pushAstNode(popAstNode());
		}
		else if (getTokenType() == TokenType.LEFTSQUAREBRACE) {
			pushAstNode(/*ParseTreeBuilder.createCharListNode()*/new CharListNode());
			match(TokenType.LEFTSQUAREBRACE);
			matchCharList();
			match(TokenType.RIGHTSQUAREBRACE);
			if (isQuantifier())
				matchQuantifier();
		}
		else if (getTokenType() == TokenType.IDENTIFIER) {
			pushAstNode(/*ParseTreeBuilder.createIdentNode(getTokenName())*/new IdentNode(getTokenName()));
			match(TokenType.IDENTIFIER);
			if (isQuantifier())
				matchQuantifier();
		}
	}
	
	private void matchQuantifier() throws ParserException {
		if (getTokenType() == TokenType.QUESTION) {
			setAstNodeQuantifier(QuantifierType.ZERO_OR_ONE);
			match(TokenType.QUESTION);
		}
		else if (getTokenType() == TokenType.PLUS) {
			setAstNodeQuantifier(QuantifierType.ONE_OR_MORE);
			match(TokenType.PLUS);
		}
		else if (getTokenType() == TokenType.STAR) {
			setAstNodeQuantifier(QuantifierType.ZERO_OR_MORE);
			match(TokenType.STAR);
		}
		else
			throw new QuantifierNotMatchException();
	}
	
	private void matchCharList() throws ParserException {
		if (getTokenType() != TokenType.RIGHTSQUAREBRACE) {
			//IAstNode node = *ParseTreeBuilder.createCharNode(getToken());
			AstNode node = new CharNode(getTokenName());
			match(getTokenType());
			if (getTokenType() == TokenType.HBAR) {
				match(TokenType.HBAR);
				addAstNode(/*ParseTreeBuilder.createCharRangeNode(node, ParseTreeBuilder.createCharNode(getToken()))*/new CharRangeNode(node, new CharNode(getTokenName())));
				match(getTokenType());
			}
			else {
				addAstNode(node);
			}
		}
		if (getTokenType() != TokenType.RIGHTSQUAREBRACE)
			matchCharList();
	}
	
	private void match(TokenType type) throws ParserException {
		if (getTokenType() == type)
			consume();
		else
			throw new TokenTypeNotMatchException(getToken(), type, getTokenType());
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
		return tokens.peek(pos).getType();
	}
	
	private void pushAstNode(/*IAstNode*/AstNode node) {
		astNodes.push(node);
	}
	
	private /*IAstNode*/AstNode popAstNode() {
		return astNodes.pop();
	}
	
	private void addAstNode(/*IAstNode*/AstNode node) {
		astNodes.peek().addChild(node);
	}
	
	private /*IAstNode*/AstNode getAstNode() {
		return astNodes.peek();
	}
	
	private void setAstNodeQuantifier(QuantifierType type) {
		getAstNode().setQuantifier(type);
	}
	
	private String getTokenName() {
		return getToken().getName();
	}
	
	private IToken getToken() {
		return tokens.getToken();
	}
}
