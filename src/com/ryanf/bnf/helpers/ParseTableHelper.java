package com.ryanf.bnf.helpers;

import java.util.Vector;

//import com.ryanf.bnf.interfaces.IAstNode;
//import com.ryanf.bnf.interfaces.IAstTree;
//import com.ryanf.bnf.interfaces.INormalisedAstTree;
import com.ryanf.bnf.interfaces.IParseTable;
import com.ryanf.bnf.types.AstNodeType;

public class ParseTableHelper {
	/*
	public static String toHtml(IParseTable table) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("<table border=\"1\" style=\"white-space:nowrap;\">");
		
		Vector<String> columns = table.getColumns();
		
		// write column indexes
		strBuilder.append("<tr>");
		strBuilder.append("<th></th><th></th>");
		for (int i = 0; i < columns.size(); i++)
			strBuilder.append("<th>" + i + "</th>");
		strBuilder.append("</tr>");
		
		// write column titles
		strBuilder.append("<tr>");
		strBuilder.append("<th></th><th></th>");
		for (String column : table.getColumns())
			strBuilder.append("<th>" + escapeHtml(column) + "</th>");
		strBuilder.append("</tr>");
		
		// write rows
		int num = 0;
		for (String row : table.getRows()) {
			strBuilder.append("<tr>");
			//writer row index
			strBuilder.append("<th>" + num++ + "</th>");
			// write row title
			strBuilder.append("<th>" + row + "</th>");
			//write entries
			for (String column : columns) {
				int entry = table.getEntry(row, column);
				strBuilder.append("<td>" + (entry > -1 ? entry : "") + "</td>");
			}
			strBuilder.append("</tr>");
		}
		strBuilder.append("</table>");
		return strBuilder.toString();
	}
	
	private static String escapeHtml(String str) {
		return str.replace("<", "&lt;").replace(">", "&gt;");
	}
	
	public static void setTableEntries(INormalisedAstTree tree, IParseTable table) throws Exception {
		if (tree != null && table != null) {
			
			// set firsts
			IAstNode statListNode = tree.getRoot();
			for (IAstNode statNode : statListNode.getChildren()) {
				IAstNode lhs = statNode.getChild(0);
				IAstNode rhs = statNode.getChild(1);
				
				String firstNodeName = rhs.getName();
				if (rhs.getType() == AstNodeType.NODELIST)
					firstNodeName = rhs.getChild(0).getName();
				Vector<IAstNode> nodes = tree.getStatNodes(firstNodeName);
				int productId = table.getProductId(firstNodeName);

				for (IAstNode node : nodes) {
					for (String first : tree.getFirsts(node.getChild(1)))
						table.setEntry(lhs.getName(), first, productId);
				}
			}
			
			// set follows
			for (String row : table.getRows()) {
				int productId = table.getProductId(row) + 1;
				if (tree.containsEmptyNode(row)) {
					Vector<IAstNode> nodes = tree.getStatNodes(row);
					for (int i = 0; i < nodes.size(); i++) {
						for (String follow : tree.getFollows(nodes.get(i).getChild(0)))
							table.setEntry(row, follow, productId);
						productId++;
					}
				}
			}
		}
	}*/
}
