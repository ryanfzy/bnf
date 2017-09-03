package com.ryanf.bnf.builders;

import java.util.Vector;

import com.ryanf.bnf.ParseTable;
import com.ryanf.bnf.helpers.AstNodeHelper;
import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IAstTree;
import com.ryanf.bnf.interfaces.IParseTable;
import com.ryanf.bnf.types.AstNodeType;

public class ParseTableBuilder {
	public static IParseTable createParseTable(IAstTree tree) {
		if (tree.getRoot().getType() == AstNodeType.STATLIST) {
			Vector<String> addedRows = new Vector<String>();
			Vector<String> addedCols = new Vector<String>();
		
			IAstNode statListNode = tree.getRoot();
			ParseTable table = new ParseTable();
		
			for (int i = 0; i < statListNode.getChildrenCount(); i++) {
				IAstNode lhs = statListNode.getChild(i).getChild(0);
				if (!addedRows.contains(lhs.toString())) {
					table.addRow(lhs.toString());
					addedRows.add(lhs.toString());
				}
			}
			
			for (int i = 0; i < statListNode.getChildrenCount(); i++) {
				IAstNode statNode = statListNode.getChild(i);
				IAstNode firstNode = statNode.getChild(1);
				
				if (firstNode.getType() == AstNodeType.NODELIST || firstNode.getType() == AstNodeType.ALTERNODELIST)
					firstNode = firstNode.getChild(0);
				
				for (String first : AstNodeHelper.GetFirsts(tree, statNode)) {
					if (!addedCols.contains(first)) {
						table.addColumn(first);
						addedCols.add(first);
					}
					if (firstNode.getType() == AstNodeType.IDENT)
						table.setEntry(statNode.getChild(0).toString(), first, firstNode.toString());
					else
						table.setEntry(statNode.getChild(0).toString(), first, first);
				}			
			}
			
			return table;
		}
		return null;
	}
}
