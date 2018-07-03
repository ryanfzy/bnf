package com.ryanf.bnf.tree;

//import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.types.AstNodeType;

public class ListNode extends AstNode {
	public ListNode() {
		super();
	}
	
	/*
	private ListNode(ListNode other) {
		super(other);
	}*/

	@Override
	public AstNodeType getType() {
		return AstNodeType.NODELIST;
	}
	
	public int count() {
		return getChildrenCount();
	}
	
	public AstNode get(int index) {
		return getChild(index);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		//for (IAstNode child : getChildren()) {
		for (AstNode child : getChildren()) {
			if (builder.length() == 0) {
				builder.append("(");
				builder.append(child.toString());
			}
			else
				builder.append(String.format(",%s", child.toString()));
		}
		builder.append(")");
		return builder.toString() + getQuantifierInStr();
	}

	/*
	@Override
	public boolean contains(IAstNode node) {
		for (IAstNode child : getChildren()) {
			if (child.equals(node))
				return true;
		}
		for (IAstNode child : getChildren()) {
			if (child.contains(node))
				return true;
		}
		return false;
	}

	@Override
	public IAstNode clone() {
		return new ListNode(this);
	}*/
}
