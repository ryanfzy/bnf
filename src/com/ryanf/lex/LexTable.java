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
	
	public Vector<Integer> FillValues(){
		return fillValues;
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

	public void addRow(Token token) {
		token.addToTable(this);
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
	
	public void fillRow(Vector<Integer> row, int value) {
		for (int i = 0; i < row.size(); i++)
			row.set(i, value);
	}
}