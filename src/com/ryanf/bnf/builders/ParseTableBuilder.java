package com.ryanf.bnf.builders;

import com.ryanf.bnf.ParseTable;
//import com.ryanf.bnf.interfaces.IAstNode;
//import com.ryanf.bnf.interfaces.IAstTree;
//import com.ryanf.bnf.interfaces.INormalisedAstTree;
import com.ryanf.bnf.interfaces.IParseTable;
import com.ryanf.bnf.tree.AstTree;
//import com.ryanf.bnf.types.AstNodeType;

public class ParseTableBuilder {
	public static IParseTable createParseTable(AstTree tree) throws Exception {
		return new ParseTable();
		/*
		if (tree.getRoot().getType() == AstNodeType.STATLIST) {
			
			INormalisedAstTree normalisedTree = ParseTreeBuilder.createNormalisedAstTree(tree);
			IParseTable table = new ParseTable();	
			IAstNode statListNode = tree.getRoot();

			// add rows
			for (IAstNode statNode : statListNode.getChildren()){
				String rowName = statNode.getChild(0).toString();
				if (!table.hasRow(rowName))
					table.addRow(rowName);
			}
			
			// add columns
			for (IAstNode statNode : statListNode.getChildren()) {
				IAstNode firstNode = statNode.getChild(1);
				if (firstNode.getType() == AstNodeType.NODELIST)
					firstNode = firstNode.getChild(0);
				for (String first : normalisedTree.getFirsts(firstNode)) {
					if (!table.hasColumn(first))
						table.addColumn(first);
				}
			}
			
			return table;
		}
		return null;*/
	}
}
