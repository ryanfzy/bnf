package com.ryanf.bnf.builders;

import java.util.Vector;

import com.ryanf.bnf.ParseTable;
import com.ryanf.bnf.helpers.AstNodeHelper;
import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IAstTree;
import com.ryanf.bnf.interfaces.INormalisedAstTree;
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
	
	public static IParseTable createParseTable(IAstTree tree) throws Exception {
		if (tree.getStatListNode().getType() == AstNodeType.STATLIST) {
			
			INormalisedAstTree normalisedTree = ParseTreeBuilder.createNormalisedAstTree(tree);
			ParseTableBuilder builder = new ParseTableBuilder();		
			IAstNode statListNode = tree.getStatListNode();
		
			// add rows
			int childrenCount = statListNode.getChildrenCount();
			for (int i = 0; i < childrenCount; i++) {
				IAstNode lhs = statListNode.getChild(i).getChild(0);
				builder.addRow(lhs.toString());	
			}
			
			// add columns
			for (int i = 0; i < childrenCount; i++) {
				IAstNode firstNode = statListNode.getChild(i).getChild(1);
				if (firstNode.getType() == AstNodeType.NODELIST)
					firstNode = firstNode.getChild(0);
				for (String first : normalisedTree.getAllFirsts(firstNode))
					builder.addColumn(first);
			}
			
			return builder.table;
		}
		return null;
	}
}
