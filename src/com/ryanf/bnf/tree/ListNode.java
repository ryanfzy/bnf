package com.ryanf.bnf.tree;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.types.AstNodeType;

public class ListNode extends AstNode {
	public ListNode() {
		super();
	}
	
	private ListNode(ListNode other) {
		super(other);
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

	@Override
	public boolean contains(IAstNode node) {
		for (int i = 0; i < getChildrenCount(); i++) {
			if (getChild(i).equals(node))
				return true;
		}
		for (int i = 0; i < getChildrenCount(); i++) {
			if (getChild(i).contains(node))
				return true;
		}
		return false;
	}

	@Override
	public IAstNode clone() {
		return new ListNode(this);
	}
}
