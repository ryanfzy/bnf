package com.ryanf.bnf.interfaces;

import java.util.Vector;

public interface IAstTree {
	IAstNode getStatListNode();
	Vector<IAstNode> getStatNodes(String identName);
	IAstNode getStatNode(String name, int nodeIndex);
}
