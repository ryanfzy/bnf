package com.ryanf.bnf.tree;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.types.AstNodeType;

public class IdentNode extends AstNode {
	String name;
	
	public IdentNode(String name) {
		super();
		this.name = name;
	}
	
	public IdentNode(IdentNode node) {
		this(node.name);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public AstNodeType getType() {
		return AstNodeType.IDENT;
	}
	
	@Override
	public String toString() {
		return name + getQuantifierInStr();
	}
	
	@Override
	public void addChild(IAstNode child) {
		throw new UnsupportedOperationException(name);
	}

	@Override
	public boolean contains(IAstNode node) {
		return equals(node);
	}
}
