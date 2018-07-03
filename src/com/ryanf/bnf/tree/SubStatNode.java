package com.ryanf.bnf.tree;

//import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.types.AstNodeType;

public class SubStatNode extends AstNode {
	public SubStatNode() {
		super();
	}
	
	public SubStatNode(/*IAstNode*/AstNode left, /*IAstNode*/AstNode right) {
		super();
		addChild(left);
		addChild(right);
	}
	
	/*
	private SubStatNode(SubStatNode other) {
		super(other);
	}*/
	
	public /*IAstNode*/AstNode left() {
		return getChild(0);
	}
	
	public /*IAstNode*/AstNode right() {
		return getChild(1);
	}

	@Override
	public AstNodeType getType() {
		return AstNodeType.SUB_STAT;
	}
	
	@Override
	public String toString() {
		return String.format("(%s - %s)", left().toString(), right().toString());
	}

	/*
	@Override
	public boolean contains(IAstNode node) {
		return equals(node);
	}

	@Override
	public IAstNode clone() {
		return new SubStatNode(this);
	}*/
}
