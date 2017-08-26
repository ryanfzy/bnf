package com.ryanf.bnf.tree;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.types.AstNodeType;

public class SubStatNode extends AstNode {
	public SubStatNode() {
		super();
	}
	
	public IAstNode left() {
		return getChild(0);
	}
	
	public IAstNode right() {
		return getChild(1);
	}

	@Override
	public AstNodeType getType() {
		return AstNodeType.SUB_STAT;
	}
}
