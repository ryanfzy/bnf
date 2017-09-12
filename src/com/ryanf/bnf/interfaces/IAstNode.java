package com.ryanf.bnf.interfaces;

import com.ryanf.bnf.types.AstNodeType;
import com.ryanf.bnf.types.QuantifierType;

public interface IAstNode {
	 IAstNode getChild(int index);
	 int getChildrenCount();
	 AstNodeType getType();
	 QuantifierType getQuantifier();
	 String getName();
	 Iterable<IAstNode> getChildren();
	 boolean contains(IAstNode node);
	 IAstNode clone();
	 
	 void setName(String name);
	 void setChild(int pos, IAstNode child);
	 void setQuantifier(QuantifierType type);
	 void addChild(IAstNode node);
}
