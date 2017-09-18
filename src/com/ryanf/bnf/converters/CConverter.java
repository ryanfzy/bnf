package com.ryanf.bnf.converters;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

import com.ryanf.bnf.builders.ParseTableBuilder;
import com.ryanf.bnf.builders.ParseTreeBuilder;
import com.ryanf.bnf.builders.ProductTableBuilder;
import com.ryanf.bnf.exceptions.ParserException;
import com.ryanf.bnf.helpers.ParseTableHelper;
import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IAstTree;
import com.ryanf.bnf.interfaces.INormalisedAstTree;
import com.ryanf.bnf.interfaces.IParseTable;

public class CConverter {
	StringBuilder strBuilder;
	IAstTree tree;
	INormalisedAstTree normalisedTree;
	IParseTable table;
	
	public CConverter(IAstTree tree) {
		this.tree = tree;
		init();
	}
	
	private void init() {
		strBuilder = new StringBuilder();
	}
	
	public String toCode() throws Exception {
		writeHeaders();
		writeLine();
		writeParseTable();
		writeLine();
		writeProductTable();
		
		return strBuilder.toString();
	}
	
	private void writeLine() {
		strBuilder.append("\n");
	}
	
	private void writeHeaders() {
		strBuilder.append("#include <stdio.h>\n");
		strBuilder.append("#include <stdlib.h>\n");
		strBuilder.append("#include <string.h>\n");
	}
	
	private void writeParseTable() throws Exception {
		IParseTable table = getParseTable();
		strBuilder.append(String.format("int parse_table[%d][%d] =\n{\n", table.getRowCount(), table.getColumnCount()));
		for (String row : table.getRows()) {
			strBuilder.append("    {");
			String comma = "";
			for (String col : table.getColumns()) {
				strBuilder.append(comma);
				comma = ",";
				strBuilder.append(table.getEntry(row, col));
			}
			strBuilder.append("},\n");
		}
	}
	
	private void writeProductTable() throws Exception {
		Vector<String> products = ProductTableBuilder.createProductTable(getNormalisedTree(), getParseTable());
		strBuilder.append("char *products_tbl[] =\n{\n");
		for (String product : products)
			strBuilder.append(String.format("    \"%s\",\n", product));
		strBuilder.append("};");
	}
	
	private IParseTable getParseTable() throws Exception {
		if (table == null) {
			table = ParseTableBuilder.createParseTable(getNormalisedTree());
			ParseTableHelper.setTableEntries(normalisedTree, table);
		}
		return table;
	}
	
	private INormalisedAstTree getNormalisedTree() {
		if (normalisedTree == null)
			normalisedTree = ParseTreeBuilder.createNormalisedAstTree(tree);
		return normalisedTree;
	}
}
