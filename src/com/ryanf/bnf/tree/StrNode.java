package com.ryanf.bnf.tree;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.types.AstNodeType;

public class StrNode extends AstNode {
	String token;
	
	public StrNode(String token) {
		this.token = token;
	}
	
	private StrNode(StrNode other) {
		super(other);
		this.token = other.token;
	}
	
	@Override
	public AstNodeType getType() {
		return AstNodeType.STRING;
	}

	@Override
	public String toString() {
		return token;
	}
	
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
		return new StrNode(this);
	}
}
