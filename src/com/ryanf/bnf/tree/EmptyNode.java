package com.ryanf.bnf.tree;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.types.AstNodeType;

public class EmptyNode extends AstNode {
	@Override
	public AstNodeType getType() {
		return AstNodeType.EMPTY;
	}

	@Override
	public String toString() {
		return "e";
	}
	
	@Override
	public void addChild(IAstNode child) {
		throw new UnsupportedOperationException();
	}
}
