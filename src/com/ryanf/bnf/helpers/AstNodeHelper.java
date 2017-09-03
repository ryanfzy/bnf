package com.ryanf.bnf.helpers;

import java.util.Vector;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IAstTree;
import com.ryanf.bnf.types.AstNodeType;

public class AstNodeHelper {
	public static Vector<String> GetFirsts(IAstTree tree, IAstNode node) {
		Vector<String> firsts = new Vector<String>();
		if (tree != null && node != null) {
			if (node.getType() == AstNodeType.ASIGN_STAT)
				firsts.addAll(GetFirsts(tree, node.getChild(1)));
			else if (node.getType() == AstNodeType.ALTERNODELIST || node.getType() == AstNodeType.CHARLIST) {
				for (int i = 0; i < node.getChildrenCount(); i++)
					firsts.addAll(GetFirsts(tree, node.getChild(i)));
			}
			else if (node.getType() == AstNodeType.CHAR || 
					 node.getType() == AstNodeType.CHAR_RANGE || 
					 node.getType() == AstNodeType.NUMBER || 
					 node.getType() == AstNodeType.STRING ||
					 node.getType() == AstNodeType.SUB_STAT)
				firsts.add(node.toString());
			else if (node.getType() == AstNodeType.IDENT) {
				IAstNode identNode = tree.getNode(node.getName());
				if (node != null)
					firsts.addAll(GetFirsts(tree, identNode));
			}
			else if (node.getType() == AstNodeType.NODELIST)
				firsts.addAll(GetFirsts(tree, node.getChild(0)));
		}
		return firsts;
	}
}
