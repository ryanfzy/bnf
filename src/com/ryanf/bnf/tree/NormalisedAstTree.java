package com.ryanf.bnf.tree;

import java.util.Vector;

import com.ryanf.bnf.Lex;
//import com.ryanf.bnf.interfaces.IAstNode;
//import com.ryanf.bnf.interfaces.INormalisedAstTree;
import com.ryanf.bnf.types.AstNodeType;
/*
public class NormalisedAstTree extends AstTree implements INormalisedAstTree {
	public NormalisedAstTree(IAstNode statListNode) {
		super(statListNode);
	}

	private Vector<String> getFirsts(IAstNode node, boolean includeEmptyNode) throws Exception {
		Vector<String> firsts = new Vector<String>();
		if (node != null) {
			if (node.getType() == AstNodeType.EMPTY) {
				if (includeEmptyNode)
					addFirst(firsts, Lex.EmptyNode);
			}
			else if (node.getType() == AstNodeType.ASIGN_STAT) {
				addFirsts(firsts, getFirsts(node.getChild(1), includeEmptyNode));
			}
			else if (node.getType() == AstNodeType.IDENT) {
				Vector<IAstNode> identNodes = getStatNodes(node.getName());
				for (IAstNode identNode : identNodes)
					addFirsts(firsts, getFirsts(identNode.getChild(1), includeEmptyNode));
			}
			else if (node.getType() == AstNodeType.NODELIST)
				addFirsts(firsts, getFirsts(node.getChild(0), includeEmptyNode));
			else
				addFirst(firsts, node.toString());
		}
		return firsts;
	}
	
	private void addFirst(Vector<String> firsts, String first) throws Exception {
		if (firsts.contains(first))
			throw new Exception(String.format("Found duplicate firsts: %s.", first));
		firsts.add(first);
	}
	
	private void addFirsts(Vector<String> firsts1, Vector<String> firsts2) throws Exception {
		for (String first : firsts2)
			addFirst(firsts1, first);
	}

	public Vector<String> getFirsts(IAstNode node) throws Exception {
		return getFirsts(node, true);
	}
	
	private void addFollows(Vector<String> follow1, Vector<String>follow2) {
		for (String follow : follow2) {
			if (!follow1.contains(follow))
				follow1.add(follow);
		}
	}


	public Vector<String> getFollows(IAstNode node) throws Exception {
		Vector<String> follows = new Vector<String>();
		if (node != null) {
			if (node.getType() == AstNodeType.ASIGN_STAT)
				addFollows(follows, getFollows(node.getChild(0)));
			else {
				for (int i = 0; i < getStatNodeCount(); i++) {
					IAstNode statNode = getStatNode(i);
					IAstNode lhs = statNode.getChild(0);
					IAstNode rhs = statNode.getChild(1);
					if (rhs.contains(node) && !lhs.equals(node)) {
						if (rhs.getType() == AstNodeType.NODELIST) {
							int childrenCount = rhs.getChildrenCount();
							for (int j = 0; j < childrenCount; j++) {
								if (rhs.getChild(j).equals(node)) {
									// if not the last child, add follows of the next child
									if (j < childrenCount-1)
										addFollows(follows, getFirsts(rhs.getChild(j+1), false));
									// if is the last child, or if it is the 2nd last child and the last child contains an empty node
									// add follows of the lhs
									if ((j == childrenCount-1 || (j == childrenCount-2 && containsEmptyNode(rhs.getChild(j+1).getName()))))
										addFollows(follows, getFollows(lhs));
									break;
								}
							}
						}
						else if (rhs.getType() == AstNodeType.IDENT)
							addFollows(follows, getFollows(lhs));
					}
				}
			}
		}
		return follows;
	}

}*/
