package com.ryanf.bnf.helpers;

import com.ryanf.bnf.interfaces.IParseTable;

public class ParseTableHelper {
	public static String toHtml(IParseTable table) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("<table border=\"1\">");
		
		// write column titles
		strBuilder.append("<tr>");
		strBuilder.append("<th></th>");
		for (String column : table.getColumns())
			strBuilder.append("<th>" + escapeHtml(column) + "</th>");
		strBuilder.append("</tr>");
		
		// write rows
		for (String row : table.getRows()) {
			strBuilder.append("<tr>");
			// write row title
			strBuilder.append("<th>" + row + "</th>");
			//write entries
			for (String column : table.getColumns()) {
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
}
