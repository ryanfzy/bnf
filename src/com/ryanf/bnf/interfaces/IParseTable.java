package com.ryanf.bnf.interfaces;

import java.util.Vector;

public interface IParseTable {
	Vector<String> getRows();
	Vector<String> getColumns();
	int getEntry(String rowName, String columnName);
	boolean hasRow(String rowName);
	boolean hasColumn(String columnName);
	
	void addRow(String name);
	void addColumn(String name);
	void setEntry(String row, String column, String toRow);
}
