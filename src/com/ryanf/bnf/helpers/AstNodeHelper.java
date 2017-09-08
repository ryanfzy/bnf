package com.ryanf.bnf.helpers;

import java.util.Vector;

import com.ryanf.bnf.Lex;
import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IAstTree;
import com.ryanf.bnf.types.AstNodeType;

public class AstNodeHelper {
	public static Vector<String> getFirsts(IAstTree tree, IAstNode node) throws Exception {
		Vector<String> firsts = new Vector<String>();
		if (tree != null && node != null) {
			if (node.getType() == AstNodeType.EMPTY)
				add(firsts, Lex.EmptyNode);
			else if (node.getType() == AstNodeType.ASIGN_STAT)
				add(firsts, getFirsts(tree, node.getChild(1)));
			else if (node.getType() == AstNodeType.ALTERNODELIST) {
				for (int i = 0; i < node.getChildrenCount(); i++)
					add(firsts, getFirsts(tree, node.getChild(i)));
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
}
