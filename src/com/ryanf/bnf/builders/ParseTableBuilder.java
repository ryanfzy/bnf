package com.ryanf.bnf.builders;

import java.util.Vector;

import com.ryanf.bnf.ParseTable;
import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IParseTable;
import com.ryanf.bnf.types.AstNodeType;

public class ParseTableBuilder {
	public static IParseTable createParseTable(IAstNode statListNode) {
		if (statListNode.getType() == AstNodeType.STATLIST) {
			Vector<String> addedCols = new Vector<String>();
		
			ParseTable table = new ParseTable();
		
			for (int i = 0; i < statListNode.getChildrenCount(); i++) {
				IAstNode statNode = statListNode.getChild(i);
				table.addRow(statNode.getChild(0).toString());
			}
			
			for (int i = 0; i < statListNode.getChildrenCount(); i++) {
				IAstNode statNode = statListNode.getChild(i);
				IAstNode firstNode = statNode.getChild(1);;
				if (firstNode.getType() == AstNodeType.NODELIST || firstNode.getType() == AstNodeType.ALTERNODELIST)
					firstNode = firstNode.getChild(0);
				if (firstNode.getType() == AstNodeType.IDENT) {
					for (String first : firstNode.firsts()) {
						if (!addedCols.contains(first)) {
							table.addColumn(first);
							addedCols.add(first);
						}
						table.setEntry(statNode.getChild(0).toString(), first, firstNode.toString());
					}
				}
			}
			
			return table;
		}
		return null;
	}
}
