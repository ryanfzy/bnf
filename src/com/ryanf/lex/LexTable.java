package com.ryanf.lex;

import java.util.Stack;
import java.util.Vector;

import com.ryanf.lex.types.ValueType;

public class LexTable extends Vector<Vector<Integer>> {
	Vector<Long> colHeaders;
	Vector<Integer> fillValues;
	Vector<String> rowNames;
	int errorNum = -99;
	int REVER_ONE_CHAR_FLAG = -100;
	public static int ERROR_NUMBER = -99;
	
	public LexTable() {
		colHeaders = new Vector<Long>();
		rowNames = new Vector<String>();
		fillValues = new Vector<Integer>();
	}
	
	public Vector<Long> colHeaders() {
		return colHeaders;
	}
	
	public int sizeOfColHeaders() {
		return colHeaders.size();
	}
	
	public boolean ofColHeaders(long header) {
		return colHeaders.contains(header);
	}
	
	public int indexOfColHeaders(long header) {
		return colHeaders.indexOf(header);
	}
	
	public void addColHeader(long header) {
		colHeaders.add(header);
		appendTableRows();
	}
	
	public void addRow() {
		addRow(errorNum);
	}
	
	public void addRow(int prependValue) {
		Vector<Integer> newRow = new Vector<Integer>();
		prependTableRow(newRow, prependValue);
		add(newRow);
		fillValues.add(prependValue);
	}
	
	public int nextTokenNumber() {
		return rowNames.size() * -1;
	}
	
	public int nextReverseOneTokenNumber() {
		return rowNames.size() * -1 + REVER_ONE_CHAR_FLAG;
	}
	
	public int valueOfColHeader(long header) {
		return firstRow().get(indexOfColHeaders(header));
	}
	
	public void addFirstRow(int row) {
		get(0).add(row);
	}
	
	public int sizeOfRows() {
		return size();
	}
	
	public Vector<Integer> firstRow() {
		if (size() == 0)
			addRow();
		return get(0);
	}
	
	public Vector<Integer> lastRow() {
		return lastElement();
	}
	
	public void addTokenName(String name) {
		rowNames.add(name);
	}
	
	/*
	private void addValue(TokenValue tokenValue, Vector<Integer> row, int rowValue, Stack<Integer> stack) {
		for (int i = 0; i < tokenValue.Values().size(); i++) {
			int oldRowValue = -99;
			if (stack.size() > 0)
				oldRowValue = stack.pop();
			long value = tokenValue.Values().get(i);
			if (!ofColHeaders(value)) {
				addColHeader(value);
				appendTableRows(errorNum);
			}
			else if (valueOfColHeader(value) > 0) {
				stack.push(valueOfColHeader(value));
				rowValue = sizeOfRows();
			}
			int colHeaderIndex = indexOfColHeaders(value);
			row.set(colHeaderIndex, rowValue);
			if (oldRowValue > 0) {
				Vector<Integer> oldRow = get(oldRowValue);
				for (int j = 0; j < oldRow.size(); j ++) {
					if (oldRow.get(j) > 0)
						row.set(j, oldRowValue);
				}
			}
		}
	}*/

	public void addRow(Token token) {
		token.addToTable(this);
		/*
		if (size() == 0)
			add(new Vector<Integer>());
		rowNames.add(token.Name());
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < token.Values().size(); i++) {
			TokenValue value = token.Values().get(i);
			Vector<Integer> row = firstRow();
			if (i > 0) {
				row = new Vector<Integer>();
				prependTableRow(row, errorNum);
				add(row);
			}
			int rowIndex = sizeOfRows();
			if (value.Type() == ValueType.Single && i == token.Values().size()-1)
				rowIndex = -(rowNames.size());
			addValue(value, row, rowIndex, stack);
			if (value.Type() == ValueType.AnyOneOrMore || token.Values().size() == 1) {
				row = new Vector<Integer>();
				prependTableRow(row, REVER_ONE_CHAR_FLAG - rowNames.size());
				add(row);
				addValue(value, row, rowIndex, stack);
			}*/
		
			/*
			for (int j = 0; j < value.Values().size(); j++) {
				addValue(value.Values().get(j), row, rowIndex);
			}*/
			/*
			boolean hasColHeader = ofColHeaders(value);
			if (!hasColHeader) {
				addColHeader(value);
				appendTableRows(errorNum);
			}
			int colHeaderIndex = indexOfColHeaders(value);
			Vector<Integer> row = null;
			if (token.ValueType() == ValueType.Single) {
				if (i == 0)
					row = firstRow();
				else {
					row = new Vector<Integer>();
					prependTableRow(row, errorNum);
					add(row);
				}
				int rowIndex = sizeOfRows();
				if (i == token.Values().size()-1)
					rowIndex = tokenNum;
				row.set(colHeaderIndex, rowIndex);
			}
			else if (token.ValueType() == ValueType.AnyOneOrMore) {
				if (i == 0) {
					row = new Vector<Integer>();
					prependTableRow(row, REVER_ONE_CHAR_FLAG + tokenNum);
					add(row);
				}
				firstRow().set(colHeaderIndex, size());
				lastRow().set(colHeaderIndex, size());
			}
			if (i == token.Values().size() -1)
				tokenNum -= 1;
		}*/
	}
	
	private void appendTableRows() {
		for (int i = 0; i < size(); i++) {
			get(i).add(fillValues.get(i));
		}
	}
	
	private void prependTableRow(Vector<Integer> row, int value) {
		for (int j = 0; j < sizeOfColHeaders(); j++)
			row.add(value);
	}
}