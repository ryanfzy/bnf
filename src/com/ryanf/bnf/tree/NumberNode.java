package com.ryanf.bnf.tree;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.types.AstNodeType;

public class NumberNode extends AstNode {
	IToken token;
	
	public NumberNode(IToken token) {
		this.token = token;
	}

	@Override
	public AstNodeType getType() {
		return AstNodeType.NUMBER;
	}
	
	@Override
	public String toString() {
		return token.getName();
	}
	
	@Override
	public void addChild(IAstNode child) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(IAstNode node) {
		return equals(node);
	}
}
