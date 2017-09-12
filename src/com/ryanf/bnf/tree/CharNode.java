package com.ryanf.bnf.tree;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.types.AstNodeType;

public class CharNode extends AstNode {
	String ch;
	
	public CharNode(String ch) {
		this.ch = ch;
	}
	
	private CharNode(CharNode other) {
		super(other);
		this.ch = other.ch;
	}

	@Override
	public AstNodeType getType() {
		return AstNodeType.CHAR;
	}

	@Override
	public String toString() {
		return ch;
	}
	
	@Override
	public void addChild(IAstNode child) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(IAstNode node) {
		return equals(node);
	}

	@Override
	public IAstNode clone() {
		return new CharNode(this);
	}
}
