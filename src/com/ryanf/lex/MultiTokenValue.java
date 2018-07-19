package com.ryanf.lex;

import java.util.Vector;

import com.ryanf.lex.types.ValueType;

public class MultiTokenValue extends TokenValue {
	protected Vector<Long> values;
	
	public MultiTokenValue(Vector<Long> values) {
		this.values = values;
	}
	
	public Vector<Long> Values() {
		return this.values;
	}
	
	private void addToTableRow(LexTable table, Vector<Integer> row) {
		for (long value : values) {
			if (!table.ofColHeaders(value))
				table.addColHeader(value);
			int colIndex = table.indexOfColHeaders(value);
			if (row.get(colIndex) == LexTable.ERROR_NUMBER)
				row.set(colIndex, table.sizeOfRows());
			else if (row.get(colIndex) < 0)
				table.addTokenInfo(row.get(colIndex), new TokenInfo(table.TokenNames().lastElement(), false));
			else
				addToTableRow(table, table.get(row.get(colIndex)));
		}
	}
	
	@Override
	public int addToTableFirst(LexTable table, int lastIndex) {
		addToTableRow(table, table.firstRow());
		/*
		for (long value : values) {
			if (!table.ofColHeaders(value))
				table.addColHeader(value);
			int colIndex = table.indexOfColHeaders(value);
			if (table.firstRow().get(colIndex) == LexTable.ERROR_NUMBER)
				table.firstRow().set(colIndex, table.sizeOfRows());
		}*/
		table.addRow();
		return LexTable.ERROR_NUMBER;
	}

	@Override
	public int addToTable(LexTable table, int lastIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addToTableLast(LexTable table, int lastIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addToTableFirstLast(LexTable table) {
		throw new UnsupportedOperationException();
	}
}
