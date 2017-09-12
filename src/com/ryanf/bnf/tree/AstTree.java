package com.ryanf.bnf.tree;

import java.util.Vector;

import com.ryanf.bnf.Lex;
import com.ryanf.bnf.builders.ParseTreeBuilder;
import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IAstTree;
import com.ryanf.bnf.types.AstNodeType;

public class AstTree implements IAstTree {
	IAstNode statListNode;
	
	public AstTree(IAstNode statListNode) {
		this.statListNode = statListNode;
	}
	
	public IAstNode getStatListNode() {
		return statListNode;
	}
	
	protected int getStatNodeCount() {
		return statListNode.getChildrenCount();
	}
	
	protected AsignStatNode getStatNode(int childIndex) {
		if (childIndex < getStatNodeCount())
			return (AsignStatNode)statListNode.getChild(childIndex);
		return null;
	}
	
	private Iterable<IAstNode> getStatNodes() {
		return statListNode.getChildren();
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
	}
}
