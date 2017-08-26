package com.ryanf.bnf.tree;

import com.ryanf.bnf.types.AstNodeType;

public class CharListNode extends AstNode {
	public CharListNode() {
		super();
	}

	@Override
	public AstNodeType getType() {
		return AstNodeType.CHARLIST;
	}
}
