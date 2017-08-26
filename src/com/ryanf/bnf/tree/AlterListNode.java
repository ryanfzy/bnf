package com.ryanf.bnf.tree;

import com.ryanf.bnf.types.AstNodeType;

public class AlterListNode extends AstNode {
	public AlterListNode() {
		super();
	}
	
	@Override
	public AstNodeType getType() {
		return AstNodeType.ALTERNODELIST;
	}

}
