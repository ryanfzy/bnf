package com.ryanf.bnf.tree;

//import com.ryanf.bnf.interfaces.IAstNode;
//import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.types.AstNodeType;

public class NumberNode extends AstNode {
	String token;
	
	public NumberNode(String token) {
		this.token = token;
	}
	
	/*
	private NumberNode(NumberNode other) {
		super(other);
		this.token = other.token;
	}*/

	@Override
	public AstNodeType getType() {
		return AstNodeType.NUMBER;
	}
	
	@Override
	public String toString() {
		return token;
	}
	
	public String getNumber() {
		return token;
	}
	
	/*
	@Override
	public void addChild(IAstNode child) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(IAstNode node) {
		return equals(node);
	}

	@Override
	public IAstNode clone() {
		return new NumberNode(this);
	}*/
}
