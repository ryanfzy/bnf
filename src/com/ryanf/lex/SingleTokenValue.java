package com.ryanf.lex;

import java.util.Vector;

import com.ryanf.lex.types.ValueType;

public class SingleTokenValue extends TokenValue {
	protected long value;
	
	public SingleTokenValue(long value) {
		this.value = value;
	}
	
	public long Value() {
		return value;
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
		table.addTokenInfo(new TokenInfo(table.TokenNames().lastElement(), true));
		if (lastValue != LexTable.ERROR_NUMBER) {
			Vector<Integer> row = table.get(lastValue);
			for (int i = 0; i < row.size(); i++) {
				if (row.get(i) > LexTable.ERROR_NUMBER) {
					int colValue = getReplacedValue(table, lastValue, i);
					table.lastElement().set(i, colValue);
				}
			}
		}
	}
	
	private int getReplacedValue(LexTable table, int rowIndex, int colIndex) {
		int value = table.get(rowIndex).get(colIndex);
		if (value > LexTable.ERROR_NUMBER && table.FillValues().get(rowIndex) < LexTable.ERROR_NUMBER)
			return getReplacedValue(table, value, colIndex);
		return rowIndex;
	}

	@Override
	public void addToTableFirstLast(LexTable table) {
		if (!table.ofColHeaders(value))
			table.addColHeader(value);
		int colIndex = table.indexOfColHeaders(value);
		int colValue = table.nextTokenNumber();
		if (table.firstRow().get(colIndex) != LexTable.ERROR_NUMBER) {
			int lastIndex = table.firstRow().get(colIndex);
			table.addRow(table.nextTokenNumber());
			Vector<Integer> row = table.get(lastIndex);
			for (int i = 0; i < row.size(); i++) {
				if (row.get(i) != LexTable.ERROR_NUMBER)
					table.lastElement().set(i, row.get(i));
			}
			colValue = table.sizeOfRows()-1;
		}
		table.firstRow().set(colIndex, colValue);
		table.addTokenInfo(table.nextTokenNumber(), new TokenInfo(table.TokenNames().lastElement(), true));
	}
}
