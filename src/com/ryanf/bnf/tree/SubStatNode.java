package com.ryanf.bnf.tree;

import java.util.Vector;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.types.AstNodeType;

public class SubStatNode extends AstNode {
	public SubStatNode() {
		super();
	}
	
	public SubStatNode(IAstNode left, IAstNode right) {
		super();
		addChild(left);
		addChild(right);
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
	
	@Override
	public String toString() {
		return String.format("(%s - %s)", left().toString(), right().toString());
	}
	
	@Override
	public Vector<String> firsts() {
		Vector<String> firsts = left().firsts();
		firsts.removeAll(right().firsts());
		return firsts;
	}
}
