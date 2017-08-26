package com.ryanf.bnf.interfaces;

import com.ryanf.bnf.types.AstNodeType;
import com.ryanf.bnf.types.QuantifierType;

public interface IAstNode {
	 void addChild(IAstNode node);
	 IAstNode getChild(int index);
	 int getChildrenCount();
	 AstNodeType getType();
	 void setQuantifier(QuantifierType type);
}