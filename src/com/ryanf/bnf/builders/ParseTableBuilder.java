package com.ryanf.bnf.builders;

import java.util.Vector;

import com.ryanf.bnf.ParseTable;
import com.ryanf.bnf.helpers.AstNodeHelper;
import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IAstTree;
import com.ryanf.bnf.interfaces.IParseTable;
import com.ryanf.bnf.types.AstNodeType;

public class ParseTableBuilder {
	ParseTable table;
	Vector<String> addedRows;
	Vector<String> addedCols;
	
	private ParseTableBuilder() {
		init();
	}
	
	private void init() {
		table = new ParseTable();
		addedRows = new Vector<String>();
		addedCols = new Vector<String>();
	}
	
	private void addRow(String rowName) {
		if (!addedRows.contains(rowName)) {
			table.addRow(rowName);
			addedRows.add(rowName);
		}
	}
	
	private void addColumn(String colName) {
		if (!addedCols.contains(colName)) {
			table.addColumn(colName);
			addedCols.add(colName);
		}
	}
	
	private void setEntries(IAstTree tree, String rowName, IAstNode firstNode) {
		for (String first : AstNodeHelper.getFirsts(tree, firstNode)) {
			addColumn(first);
			if (firstNode.getType() == AstNodeType.IDENT)
				table.setEntry(rowName, first, firstNode.toString());
			else
				table.setEntry(rowName, first, first);
		}
	}
	
	public static IParseTable createParseTable(IAstTree tree) {
		if (tree.getRoot().getType() == AstNodeType.STATLIST) {
			
			ParseTableBuilder builder = new ParseTableBuilder();		
			IAstNode statListNode = tree.getRoot();
		
			for (int i = 0; i < statListNode.getChildrenCount(); i++) {
				IAstNode lhs = statListNode.getChild(i).getChild(0);
				builder.addRow(lhs.toString());
			}
			
			for (int i = 0; i < statListNode.getChildrenCount(); i++) {
				IAstNode lhs = statListNode.getChild(i).getChild(0);
				IAstNode rhs = statListNode.getChild(i).getChild(1);
				
				if (rhs.getType() == AstNodeType.NODELIST)
					builder.setEntries(tree, lhs.toString(), rhs.getChild(0));
				else if (rhs.getType() == AstNodeType.ALTERNODELIST) {
					for (int j = 0 ; j < rhs.getChildrenCount(); j++)
						builder.setEntries(tree, lhs.toString(), rhs.getChild(j));
				}
			}
			
			return builder.table;
		}
		return null;
	}
}
