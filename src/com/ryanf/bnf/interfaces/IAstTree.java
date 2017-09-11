package com.ryanf.bnf.interfaces;

import java.util.Vector;

public interface IAstTree {
	IAstNode getRoot();
	Vector<IAstNode> getAsignNodes(String name);
	IAstNode getAsignNode(String name, int nodeIndex);
	Vector<String> getAllFirsts(IAstNode node) throws Exception;
	Vector<String> getFirsts(IAstNode node) throws Exception;
	Vector<String> getFollows(IAstNode node) throws Exception;
	
	boolean contains(String identName, IAstNode node);
	boolean containsEmptyNode(String identName);
}
