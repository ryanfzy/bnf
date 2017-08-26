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
}
