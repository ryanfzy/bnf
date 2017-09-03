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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < getChildrenCount(); i++) {
			if (builder.length() == 0) {
				builder.append("(");
				builder.append(getChild(i).toString());
			}
			else
				builder.append(String.format("|%s", getChild(i).toString()));
		}
		builder.append(")");
		return builder.toString() + getQuantifierInStr();
	}
}
