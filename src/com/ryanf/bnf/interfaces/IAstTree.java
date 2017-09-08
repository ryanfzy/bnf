package com.ryanf.bnf.interfaces;

import java.util.Vector;

public interface IAstTree {
	IAstNode getRoot();
	Vector<IAstNode> getNodes(String name);
}
