package com.ryanf.bnf.tree;

import java.util.Vector;

import com.ryanf.bnf.Lex;
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
		quantifier = QuantifierType.ONE;
	}
	
	private void init() {
		children = new Vector<IAstNode>();
	}
	
	public void addChild(IAstNode node) {
		children.add(node);
	}
	
	public int getChildrenCount() {
		return children.size();
	}
	
	public IAstNode getChild(int index) {
		if (index > -1 && index < children.size())
			return children.get(index);
		return null;
	}
	
	public void setChild(int pos, IAstNode child) {
		if (pos > -1 && pos < children.size())
			children.set(pos, child);
	}
	
	public abstract AstNodeType getType();
	
	public String getName() {
		return "";
	}
	
	public void setName(String name) {
	}
	
	public QuantifierType getQuantifier() {
		return quantifier;
	}
	
	public void setQuantifier(QuantifierType type) {
		quantifier = type;
	}
	
	protected String getQuantifierInStr() {
		switch (quantifier) {
		case ZERO_OR_ONE:
			return Character.toString(Lex.Question);
		case ZERO_OR_MORE:
			return Character.toString(Lex.Star);
		case ONE_OR_MORE:
			return Character.toString(Lex.Plus);
		default:
			break;
		}
		return "";
	}
}
