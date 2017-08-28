package com.ryanf.bnf.tree;

import java.util.Vector;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.types.AstNodeType;

public class IdentNode extends AstNode {
	private IToken token;
	
	public IdentNode(IToken token) {
		super();
		this.token = token;
	}

	@Override
	public AstNodeType getType() {
		return AstNodeType.IDENT;
	}
	
	@Override
	public String toString() {
		return token.getName() + getQuantifierInStr();
	}
	
	@Override
	public void addChild(IAstNode child) {
		throw new UnsupportedOperationException(token.getName());
	}
	
	@Override
	public Vector<String> firsts(){
		Vector<String> firsts = new Vector<String>();
		firsts.add(token.getName());
		return firsts;
	}
}
