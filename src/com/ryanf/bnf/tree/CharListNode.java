package com.ryanf.bnf.tree;

import java.util.Vector;

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
		builder.append("[");
		for (int i = 0; i < getChildrenCount(); i++)
			builder.append(getChild(i).toString());
		builder.append("]");
		return builder.toString() + getQuantifierInStr();
	}
	
	@Override
	public Vector<String> firsts(){
		Vector<String> firsts = new Vector<String>();
		for (int i = 0; i < getChildrenCount(); i++)
			firsts.addAll(getChild(i).firsts());
		return firsts;
	}
}
