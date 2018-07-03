package com.ryanf.bnf.tree;

//import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.types.AstNodeType;

public class CharRangeNode extends AstNode {
	
	public CharRangeNode(/*IAstNode*/AstNode from, /*IAstNode*/AstNode to) {
		super();
		addChild(from);
		addChild(to);
	}
	
	/*
	private CharRangeNode(CharRangeNode other) {
		super(other);
	}*/
	
	public /*IAstNode*/AstNode from() {
		return getChild(0);
	}
	
	public /*IAstNode*/AstNode to() {
		return getChild(1);
	}

	@Override
	public AstNodeType getType() {
		return AstNodeType.CHAR_RANGE;
	}
	
	@Override
	public String toString() {
		return String.format("%s-%s", from().toString(), to().toString());
	}

	/*
	@Override
	public boolean contains(IAstNode node) {
		return equals(node);
	}

	@Override
	public IAstNode clone() {
		return new CharRangeNode(this);
	}*/
}
