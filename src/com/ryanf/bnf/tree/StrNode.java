package com.ryanf.bnf.tree;

import java.util.Vector;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.types.AstNodeType;

public class StrNode extends AstNode {
	IToken token;
	
	public StrNode(IToken token) {
		this.token = token;
	}
	
	@Override
	public AstNodeType getType() {
		return AstNodeType.STRING;
	}

	@Override
	public String toString() {
		return token.getName();
	}
	
	@Override
	public void addChild(IAstNode child) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Vector<String> firsts() {
		Vector<String> firsts = new Vector<String>();
		firsts.add(token.getName());
		return firsts;
	}
}
