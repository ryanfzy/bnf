package com.ryanf.lex;

import java.util.Vector;

public class SingleOneTokenValue extends SingleTokenValue {
	public SingleOneTokenValue(long value) {
		super(value);
	}

	@Override
	public int addToTableFirst(LexTable table, int lastIndex) {
		int currentValue = LexTable.ERROR_NUMBER;
		if (!table.ofColHeaders(value))
			table.addColHeader(value);
		else
			currentValue = table.firstRow().get(table.indexOfColHeaders(value));
		int colIndex = table.indexOfColHeaders(value);
		table.firstRow().set(colIndex, table.sizeOfRows());
		table.addRow();
		return currentValue;
	}
	
	@Override
	public int addToTable(LexTable table, int lastIndex) {
		if (!table.ofColHeaders(value))
			table.addColHeader(value);
		int colIndex = table.indexOfColHeaders(value);
		table.lastElement().set(colIndex, table.sizeOfRows());
		table.addRow();
		return LexTable.ERROR_NUMBER;
	}

	@Override
	public void addToTableLast(LexTable table, int lastValue) {
		if (!table.ofColHeaders(value))
			table.addColHeader(value);
		int colIndex = table.indexOfColHeaders(value);
		table.lastElement().set(colIndex, table.nextTokenNumber());
		if (lastValue != LexTable.ERROR_NUMBER) {
			Vector<Integer> row = table.get(lastValue);
			for (int i = 0; i < row.size(); i++) {
				if (row.get(i) > LexTable.ERROR_NUMBER)
					table.lastElement().set(i, lastValue);
			}
		}
		//return LexTable.ERROR_NUMBER;
	}

	@Override
	public void addToTableFirstLast(LexTable table) {
		if (!table.ofColHeaders(value))
			table.addColHeader(value);
		int colIndex = table.indexOfColHeaders(value);
		int colValue = table.nextTokenNumber();
		if (table.firstRow().get(colIndex) != LexTable.ERROR_NUMBER) {
			int lastIndex = table.firstRow().get(colIndex);
			table.addRow(table.nextReverseOneTokenNumber());
			Vector<Integer> row = table.get(lastIndex);
			for (int i = 0; i < row.size(); i++) {
				if (row.get(i) != LexTable.ERROR_NUMBER)
					table.lastElement().set(i, lastIndex);
			}
			colValue = table.sizeOfRows()-1;
		}
		table.firstRow().set(colIndex, colValue);
	}
}
