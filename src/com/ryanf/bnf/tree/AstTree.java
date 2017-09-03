package com.ryanf.bnf.tree;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IAstTree;

public class AstTree implements IAstTree {
	IAstNode statListNode;
	
	public AstTree(IAstNode statListNode) {
		this.statListNode = statListNode;
	}
	
	public IAstNode getRoot() {
		return statListNode;
	}

	public IAstNode getNode(String name) {
		for (int i = 0; i < statListNode.getChildrenCount(); i++) {
			IAstNode identNode = statListNode.getChild(i).getChild(0);
			if (identNode.toString().equals(name))
				return statListNode.getChild(i).getChild(1);
		}
		return null;
	}
}
