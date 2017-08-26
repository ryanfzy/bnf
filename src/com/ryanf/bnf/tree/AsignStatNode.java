package com.ryanf.bnf.tree;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.types.AstNodeType;

public class AsignStatNode extends AstNode {
	public AsignStatNode() {
		super();
	}
	
	public IAstNode getLhs() {
		return getChild(0);
	}
	
	public IAstNode getRhs() {
		return getChild(1);
	}

	@Override
	public AstNodeType getType() {
		return AstNodeType.ASIGN_STAT;
	}
}
