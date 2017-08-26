package com.ryanf.bnf.tree;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.types.AstNodeType;

public class CharNode extends AstNode {
	IToken token;
	
	public CharNode(IToken token) {
		this.token = token;
	}

	@Override
	public AstNodeType getType() {
		return AstNodeType.CHAR;
	}

	@Override
	public String toString() {
		return token.getName();
	}
	
	@Override
	public void addChild(IAstNode child) {
		throw new UnsupportedOperationException();
	}
}
