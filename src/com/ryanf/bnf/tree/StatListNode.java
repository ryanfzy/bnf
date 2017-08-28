package com.ryanf.bnf.tree;

import java.util.Vector;

import com.ryanf.bnf.types.AstNodeType;

public class StatListNode extends AstNode {

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
	public Vector<String> firsts() {
		throw new UnsupportedOperationException();
	}
}
