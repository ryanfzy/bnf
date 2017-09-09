package com.ryanf.bnf.tree;


import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.types.AstNodeType;

public class AsignStatNode extends AstNode {
	public AsignStatNode() {
		super();
	}
	
	public AsignStatNode(IAstNode lhs, IAstNode rhs) {
		super();
		addChild(lhs);
		addChild(rhs);
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
	
	@Override
	public String toString() {
		return String.format("%s = %s", getLhs().toString(), getRhs().toString());
	}

	@Override
	public boolean contains(IAstNode node) {
		return getRhs().contains(node);
	}
}
