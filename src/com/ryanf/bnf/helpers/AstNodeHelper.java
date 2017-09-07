package com.ryanf.bnf.helpers;

import java.util.Vector;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IAstTree;
import com.ryanf.bnf.types.AstNodeType;

public class AstNodeHelper {
	public static Vector<String> getFirsts(IAstTree tree, IAstNode node) throws Exception {
		Vector<String> firsts = new Vector<String>();
		if (tree != null && node != null) {
			if (node.getType() == AstNodeType.ASIGN_STAT)
				add(firsts, getFirsts(tree, node.getChild(1)));
			else if (node.getType() == AstNodeType.ALTERNODELIST || node.getType() == AstNodeType.CHARLIST) {
				for (int i = 0; i < node.getChildrenCount(); i++)
					add(firsts, getFirsts(tree, node.getChild(i)));
			}
			else if (node.getType() == AstNodeType.CHAR || 
					 node.getType() == AstNodeType.CHAR_RANGE || 
					 node.getType() == AstNodeType.NUMBER || 
					 node.getType() == AstNodeType.STRING ||
					 node.getType() == AstNodeType.SUB_STAT)
				add(firsts, node.toString());
			else if (node.getType() == AstNodeType.IDENT) {
				IAstNode identNode = tree.getNode(node.getName());
				if (node != null)
					add(firsts, getFirsts(tree, identNode));
			}
			else if (node.getType() == AstNodeType.NODELIST)
				add(firsts, getFirsts(tree, node.getChild(0)));
		}
		return firsts;
	}
	
	private static void add(Vector<String> firsts, String first) throws Exception {
		if (firsts.contains(first))
			throw new Exception(String.format("Found duplicate firsts: %s.", first));
		else
			firsts.add(first);
	}
	
	private static void add(Vector<String> firsts1, Vector<String> firsts2) throws Exception {
		for (String first : firsts2)
			add(firsts1, first);
	}
	
	/*
	public static Vector<String> getFollows(IAstTree tree, IAstNode node) {
		Vector<String> follows = new Vector<String>();
		if (node.getType() == AstNodeType.IDENT) {
			IAstNode statListNode = tree.getRoot();
			for (int i = 0; i < statListNode.getChildrenCount(); i++) {
				IAstNode statNode = statListNode.getChild(i);
				IAstNode lhs = statNode.getChild(0);
				if (!lhs.getName().equals((node.getName()))) {
					IAstNode rhs = statNode.getChild(1);
					if (rhs.getType() == AstNodeType.NODELIST) {
						for (int j = 0; j < rhs.getChildrenCount(); j++) {
							IAstNode child = rhs.getChild(j);
							
						}
					}
				}
			}
		}
		return follows;
	}
	
	private static Vector<String> getFollows(IAstNode nodeList, IAstNode node) {
		Vector<String> follows = new Vector<String>();
		if (nodeList.getType() == AstNodeType.NODELIST) {
			for (int i = 0; i < nodeList.getChildrenCount(); i++) {
				IAstNode child = nodeList.getChild(i);
				if (child.getName().equals(node.getName())) {
					if (i < nodeList.getChildrenCount() - 1) {
						IAstNode follow = nodeList.getChild(i + 1);
					}
				}
			}
		}
		return follows;
	}*/
}
