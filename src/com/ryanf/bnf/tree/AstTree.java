package com.ryanf.bnf.tree;

import java.util.Vector;

import com.ryanf.bnf.Lex;
//import com.ryanf.bnf.builders.ParseTreeBuilder;
//import com.ryanf.bnf.interfaces.IAstNode;
//import com.ryanf.bnf.interfaces.IAstTree;
import com.ryanf.bnf.types.AstNodeType;

public class AstTree /*implements IAstTree*/ {
	//IAstNode rootNode;
	AstNode root;
	
	public AstTree(/*IAstNode*/AstNode root) {
		this.root = root;
	}
	
	/*
	public IAstNode getStatListNode() {
		return getRoot();
	}
	
	protected int getStatNodeCount() {
		return rootNode.getChildrenCount();
	}
	
	protected AsignStatNode getStatNode(int childIndex) {
		if (childIndex < getStatNodeCount())
			return (AsignStatNode)rootNode.getChild(childIndex);
		return null;
	}
	
	private Iterable<IAstNode> getStatNodes() {
		return rootNode.getChildren();
	}

	public Vector<IAstNode> getStatNodes(String identName) {
		Vector<IAstNode> nodes = new Vector<IAstNode>();
		for (IAstNode statNode : getStatNodes()) {
			if (statNode.getChild(0).getName().equals(identName))
				nodes.add(statNode);
		}
		return nodes;
	}
	
	public IAstNode getStatNode(String name, int nodeIndex) {
		return getStatNode(nodeIndex);
	}
	
	public boolean contains(String identName, IAstNode node) {
		for (IAstNode rhs : getStatNodes(identName))
			if (rhs.contains(node))
				return true;
		return false;
	}
	
	public boolean containsEmptyNode(String identName) {
		return contains(identName, ParseTreeBuilder.createEmptyNode());
	}*/

	public AstNode getRoot() {
		return root;
	}
}
