package com.ryanf.bnf.tree;

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

}
