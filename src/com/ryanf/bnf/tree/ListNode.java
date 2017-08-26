package com.ryanf.bnf.tree;

import com.ryanf.bnf.types.AstNodeType;

public class ListNode extends AstNode {
	public ListNode() {
		super();
	}

	@Override
	public AstNodeType getType() {
		return AstNodeType.NODELIST;
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
				builder.append(String.format(",%s", getChild(i).toString()));
		}
		builder.append(")");
		return builder.toString() + getQuantifierInStr();
	}
}
