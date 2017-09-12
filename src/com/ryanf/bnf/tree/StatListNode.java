package com.ryanf.bnf.tree;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.types.AstNodeType;

public class StatListNode extends AstNode {
	public StatListNode() {
		super();
	}
	
	private StatListNode(StatListNode other) {
		super(other);
	}
	
	@Override
	public AstNodeType getType() {
		return AstNodeType.STATLIST;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < getChildrenCount(); i++) {
			builder.append(String.format("%s\n", getChild(i).toString()));
		}
		return builder.toString();
	}

	@Override
	public boolean contains(IAstNode node) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IAstNode clone() {
		return new StatListNode(this);
	}
}
