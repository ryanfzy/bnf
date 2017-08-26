package com.ryanf.bnf.tree;

import java.util.Vector;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.types.AstNodeType;
import com.ryanf.bnf.types.QuantifierType;

public abstract class AstNode implements IAstNode {
	Vector<IAstNode> children;
	AstNodeType type;
	QuantifierType quantifier;
	
	public AstNode() {
		init();
		type = AstNodeType.NOTYPE;
	}
	
	private void init() {
		children = new Vector<IAstNode>();
	}
	
	public void addChild(IAstNode node) {
		children.add(node);
	}
	
	public IAstNode getChild(int index) {
		if (index > -1 && index < children.size())
			return children.get(index);
		return null;
	}
	
	public abstract AstNodeType getType();
	
	public void setQuantifier(QuantifierType type) {
		quantifier = type;
	}
}
