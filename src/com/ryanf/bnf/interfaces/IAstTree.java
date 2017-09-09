package com.ryanf.bnf.interfaces;

import java.util.Vector;

public interface IAstTree {
	IAstNode getRoot();
	Vector<IAstNode> getNodes(String name);
	boolean contains(String identName, IAstNode node);
	boolean containsEmptyNode(String identName);
}
