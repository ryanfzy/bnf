package com.ryanf.bnf.helpers;

import java.util.Vector;

import com.ryanf.bnf.Lex;
import com.ryanf.bnf.builders.ParseTreeBuilder;
import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IAstTree;
import com.ryanf.bnf.types.AstNodeType;

public class AstNodeHelper {
	public static Vector<String> getFirsts(IAstTree tree, IAstNode node) throws Exception {
		return getFirsts(tree, node, true);
	}
	
	private static Vector<String> getFirsts(IAstTree tree, IAstNode node, boolean includeEmptyNode) throws Exception {
		Vector<String> firsts = new Vector<String>();
		if (tree != null && node != null) {
			if (node.getType() == AstNodeType.EMPTY) {
				if (includeEmptyNode)
					add(firsts, Lex.EmptyNode);
			}
			else if (node.getType() == AstNodeType.ASIGN_STAT)
				add(firsts, getFirsts(tree, node.getChild(1), includeEmptyNode));
			else if (node.getType() == AstNodeType.ALTERNODELIST) {
				for (int i = 0; i < node.getChildrenCount(); i++)
					add(firsts, getFirsts(tree, node.getChild(i), includeEmptyNode));
			}
			else if (node.getType() == AstNodeType.CHARLIST)
				add(firsts, node.toString());
			else if (node.getType() == AstNodeType.CHAR || 
					 node.getType() == AstNodeType.CHAR_RANGE || 
					 node.getType() == AstNodeType.NUMBER || 
					 node.getType() == AstNodeType.STRING ||
					 node.getType() == AstNodeType.SUB_STAT)
				add(firsts, node.toString());
			else if (node.getType() == AstNodeType.IDENT) {
				Vector<IAstNode> identNodes = tree.getNodes(node.getName());
				for (IAstNode identNode : identNodes)
					add(firsts, getFirsts(tree, identNode, includeEmptyNode));
			}
			else if (node.getType() == AstNodeType.NODELIST)
				add(firsts, getFirsts(tree, node.getChild(0), includeEmptyNode));
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
	
	private static void addFollows(Vector<String> follow1, Vector<String>follow2) {
		for (String follow : follow2) {
			if (!follow1.contains(follow))
				follow1.add(follow);
		}
	}
	
	// only works for normalised tree
	public static Vector<String> getFollows(IAstTree tree, IAstNode node) throws Exception {
		Vector<String> follows = new Vector<String>();
		if (tree != null && node != null) {
			if (node.getType() == AstNodeType.ASIGN_STAT)
				addFollows(follows, getFollows(tree, node.getChild(0)));
			else {
				IAstNode statListNode = tree.getRoot();
				for (int i = 0; i < statListNode.getChildrenCount(); i++) {
					IAstNode lhs = statListNode.getChild(i).getChild(0);
					IAstNode rhs = statListNode.getChild(i).getChild(1);
					if (rhs.contains(node) && !lhs.equals(node)) {
						if (rhs.getType() == AstNodeType.NODELIST) {
							int childrenCount = rhs.getChildrenCount();
							for (int j = 0; j < childrenCount; j++) {
								if (rhs.getChild(j).equals(node)) {
									if (j < childrenCount-1)
										addFollows(follows, getFirsts(tree, rhs.getChild(j+1), false));
									if ((j == childrenCount-1 || (j == childrenCount-2 && tree.containsEmptyNode(rhs.getChild(j+1).getName()))))
										addFollows(follows, getFollows(tree, lhs));
									break;
								}
							}
						}
						else if (rhs.getType() == AstNodeType.IDENT)
							addFollows(follows, getFollows(tree, lhs));
					}
				}
			}
		}
		return follows;
	}
}
