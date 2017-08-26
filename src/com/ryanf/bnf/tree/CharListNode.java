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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < getChildrenCount(); i++)
			builder.append(getChild(i).toString());
		return builder.toString();
	}
}
