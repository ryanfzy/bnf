package com.ryanf.bnf.tree;

import java.util.Vector;

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

	public Vector<IAstNode> getNodes(String name) {
		Vector<IAstNode> nodes = new Vector<IAstNode>();
		for (int i = 0; i < statListNode.getChildrenCount(); i++) {
			IAstNode identNode = statListNode.getChild(i).getChild(0);
			if (identNode.toString().equals(name))
				nodes.add(statListNode.getChild(i).getChild(1));
		}
		return nodes;
	}
}
