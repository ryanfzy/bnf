package com.ryanf.bnf.helpers;

import java.util.Vector;

import com.ryanf.bnf.interfaces.IParseTable;

public class ParseTableHelper {
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
}
