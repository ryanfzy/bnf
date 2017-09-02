package com.ryanf.bnf;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.ryanf.bnf.interfaces.IParseTable;

public class ParseTable implements IParseTable {
	List<List<Integer>> table;
	Vector<String> rowNames;
	Vector<String> colNames;
	
	public ParseTable() {
		table = new ArrayList<List<Integer>>();
		rowNames = new Vector<String>();
		colNames = new Vector<String>();
	}
	
	public void addRow(String name) {
		rowNames.add(name);
		table.add(new ArrayList<Integer>());
	}
	
	public void addColumn(String name) {
		colNames.add(name);
		for (List<Integer> row : table)
			row.add(-1);
	}
	
	public void setEntry(String row, String column, String toRow) {
		if (hasEntry(row, column))
			table.get(getRow(row)).set(getCol(column), getRow(toRow));
	}

	public int getEntry(String rowName, String colName) {
		if (hasEntry(rowName, colName))
			return table.get(getRow(rowName)).get(getCol(colName));
		return -1;
	}
	
	private boolean hasEntry(String rowName, String colName) {
		return rowName.contains(rowName) && colName.contains(colName);
	}
	
	private int getRow(String name) {
		for (int i = 0; i < rowNames.size(); i++) {
			if (rowNames.get(i).equals(name))
				return i;
		}
		return -1;
	}
	
	private int getCol(String name) {
		for (int i = 0; i < colNames.size(); i++) {
			if (colNames.get(i).equals(name))
				return i;
		}
		return -1;
	}

	public Vector<String> getRows() {
		Vector<String> clone = new Vector<String>();
		for (String row : rowNames)
			clone.add(row);
		return clone;
	}

	public Vector<String> getColumns() {
		Vector<String> clone = new Vector<String>();
		for (String col : colNames)
			clone.add(col);
		return clone;
	}
}
