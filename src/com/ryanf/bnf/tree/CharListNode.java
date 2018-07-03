package com.ryanf.bnf.tree;

//import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.types.AstNodeType;

public class CharListNode extends AstNode {
	public CharListNode() {
		super();
	}
	
	/*
	private CharListNode(CharListNode other) {
		super(other);
	}*/

	@Override
	public AstNodeType getType() {
		return AstNodeType.CHARLIST;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		//for (IAstNode child : getChildren())
		for (AstNode child : getChildren())
			builder.append(child.toString());
		builder.append("]");
		return builder.toString() + getQuantifierInStr();
	}
	
	public int count() {
		return getChildrenCount();
	}
	
	public AstNode get(int index) {
		return getChild(index);
	}
	
	/*
	@Override
	public boolean contains(IAstNode node) {
		for (IAstNode child : getChildren()) {
			if (child.equals(node))
				return true;
		}
		return false;
	}

	@Override
	public IAstNode clone() {
		return new CharListNode(this);
	}*/
}
