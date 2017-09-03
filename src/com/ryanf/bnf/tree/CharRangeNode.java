package com.ryanf.bnf.tree;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.types.AstNodeType;

public class CharRangeNode extends AstNode {
	
	public CharRangeNode(IAstNode from, IAstNode to) {
		super();
		addChild(from);
		addChild(to);
	}

	@Override
	public AstNodeType getType() {
		return AstNodeType.CHAR_RANGE;
	}
	
	@Override
	public String toString() {
		return String.format("%s-%s", getChild(0).toString(), getChild(1).toString());
	}
}
