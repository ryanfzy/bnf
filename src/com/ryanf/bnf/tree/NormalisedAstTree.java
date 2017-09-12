package com.ryanf.bnf.tree;

import java.util.Vector;

import com.ryanf.bnf.Lex;
import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.INormalisedAstTree;
import com.ryanf.bnf.types.AstNodeType;

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
				AsignStatNode statNode = (AsignStatNode)node;
				addFirsts(firsts, getFirsts(statNode.getRhs(), includeEmptyNode));
			}
			else if (node.getType() == AstNodeType.ALTERNODELIST) {
				for (int i = 0; i < node.getChildrenCount(); i++)
					addFirsts(firsts, getFirsts(node.getChild(i), includeEmptyNode));
			}
			else if (node.getType() == AstNodeType.CHARLIST)
				addFirst(firsts, node.toString());
			else if (node.getType() == AstNodeType.CHAR || 
					 node.getType() == AstNodeType.CHAR_RANGE || 
					 node.getType() == AstNodeType.NUMBER || 
					 node.getType() == AstNodeType.STRING ||
					 node.getType() == AstNodeType.SUB_STAT)
				addFirst(firsts, node.toString());
			else if (node.getType() == AstNodeType.IDENT) {
				Vector<IAstNode> identNodes = getStatNodes(node.getName());
				for (IAstNode identNode : identNodes)
					addFirsts(firsts, getFirsts(identNode.getChild(1), includeEmptyNode));
			}
			else if (node.getType() == AstNodeType.NODELIST)
				addFirsts(firsts, getFirsts(node.getChild(0), includeEmptyNode));
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

	public Vector<String> getAllFirsts(IAstNode node) throws Exception {
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
					AsignStatNode statNode = getStatNode(i);
					if (statNode.getRhs().contains(node) && !statNode.getLhs().equals(node)) {
						if (statNode.getRhs().getType() == AstNodeType.NODELIST) {
							int childrenCount = statNode.getRhs().getChildrenCount();
							for (int j = 0; j < childrenCount; j++) {
								if (statNode.getRhs().getChild(j).equals(node)) {
									if (j < childrenCount-1)
										addFollows(follows, getFirsts(statNode.getRhs().getChild(j+1), false));
									if ((j == childrenCount-1 || (j == childrenCount-2 && containsEmptyNode(statNode.getRhs().getChild(j+1).getName()))))
										addFollows(follows, getFollows(statNode.getLhs()));
									break;
								}
							}
						}
						else if (statNode.getRhs().getType() == AstNodeType.IDENT)
							addFollows(follows, getFollows(statNode.getLhs()));
					}
				}
			}
		}
		return follows;
	}

}
