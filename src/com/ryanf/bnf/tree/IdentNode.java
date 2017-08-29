package com.ryanf.bnf.tree;

import java.util.Vector;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IAstNodeGetter;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.types.AstNodeType;

public class IdentNode extends AstNode {
	IToken token;
	IAstNodeGetter getter;
	
	public IdentNode(IAstNodeGetter getter, IToken token) {
		super();
		this.getter = getter;
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
		IAstNode node = getter.get(token.getName());
		if (node != null)
			return node.firsts();
		return new Vector<String>();
	}
}
