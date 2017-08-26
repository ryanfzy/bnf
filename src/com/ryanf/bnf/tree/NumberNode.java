package com.ryanf.bnf.tree;

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
}
