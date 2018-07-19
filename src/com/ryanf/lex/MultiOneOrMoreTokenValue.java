package com.ryanf.lex;

import java.util.Vector;

public class MultiOneOrMoreTokenValue extends MultiTokenValue {
	
	public MultiOneOrMoreTokenValue(Vector<Long> values) {
		super(values);
	}

	@Override
	public int addToTableFirst(LexTable table, int lastIndex) {
		for (long value : values) {
			if (!table.ofColHeaders(value))
				table.addColHeader(value);
			int colIndex = table.indexOfColHeaders(value);
			table.firstRow().set(colIndex, table.sizeOfRows());
		}
		table.addRow();
		return LexTable.ERROR_NUMBER;
	}

	@Override
	public int addToTable(LexTable table, int lastIndex) {
		// TODO Auto-generated method stub
		return LexTable.ERROR_NUMBER;
	}

	@Override
	public void addToTableLast(LexTable table, int lastIndex) {
		table.fillRow(table.size()-1, table.nextTokenNumber());
		table.addTokenInfo(table.nextTokenNumber(), new TokenInfo(table.TokenNames().lastElement(), false));
		for (long value : values) {
			if (!table.ofColHeaders(value))
				table.addColHeader(value);
			int colIndex = table.indexOfColHeaders(value);
			table.lastElement().set(colIndex, table.sizeOfRows()-1);
		}
	}

	@Override
	public void addToTableFirstLast(LexTable table) {
		addToTableFirst(table, LexTable.ERROR_NUMBER);
		addToTableLast(table, LexTable.ERROR_NUMBER);
	}
}
