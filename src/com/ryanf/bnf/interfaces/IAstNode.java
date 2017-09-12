package com.ryanf.bnf.interfaces;

import com.ryanf.bnf.types.AstNodeType;
import com.ryanf.bnf.types.QuantifierType;

public interface IAstNode {
	 void addChild(IAstNode node);
	 IAstNode getChild(int index);
	 int getChildrenCount();
	 AstNodeType getType();
	 QuantifierType getQuantifier();
	 void setQuantifier(QuantifierType type);
	 String getName();
	 void setName(String name);
	 void setChild(int pos, IAstNode child);
	 boolean contains(IAstNode node);
	 Iterable<IAstNode> getChildren();
	 IAstNode clone();
}
