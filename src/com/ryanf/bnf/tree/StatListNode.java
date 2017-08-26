package com.ryanf.bnf.tree;

import com.ryanf.bnf.types.AstNodeType;

public class StatListNode extends AstNode {

	@Override
	public AstNodeType getType() {
		return AstNodeType.STATLIST;
	}

}
