package com.ryanf.bnf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.ryanf.bnf.exceptions.OutOfCharException;
import com.ryanf.bnf.interfaces.ISource;

public class Source implements ISource {
	Path filePath;
	List<String> fileLines;
	int column;
	int row;
	
	public Source(String fileName) {
		filePath = Paths.get(fileName);
	}

	public char getChar() {
		return getChar(row, column);
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getRow() {
		return row;
	}
	
	public void next() throws OutOfCharException {
		if (hasMoreCol())
			nextCol();
		else if (hasMoreRow())
			nextRow();
		else
			throw new OutOfCharException();
	}
	
	public void previous() {
		throw new UnsupportedOperationException();
	}
	
	public char lookAhead(int pos) throws OutOfCharException {
		int curCol = column + pos;
		int curRow = row;
		while (curCol > fileLines.get(curRow).length() - 1) {
			if (curRow >= fileLines.size())
				throw new OutOfCharException();
			curCol = fileLines.get(curRow).length() - curCol;
			curRow++;
		}
		return getChar(curRow, curCol);
	}
	
	public boolean hasMore() {
		if (canReadFile())
			return hasMoreCol() || hasMoreRow();
		return false;
	}
	
	private void init() {
		row = 0;
		column = -1;
	}
	
	private  char getChar(int row, int column) {
		return fileLines.get(row).charAt(column);
	}
	
	private void readFile() throws IOException {
		fileLines = Files.readAllLines(filePath);
		init();
	}
	
	private boolean tryReadFile() {
		try {
			readFile();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	private boolean canReadFile() {
		if (fileLines == null || fileLines.isEmpty()) {
			if (!tryReadFile())
				return false;
		}
		return true;
	}
	
	private boolean hasMoreCol() {
		return column < fileLines.get(row).length() - 1;
	}
	
	private boolean hasMoreRow() {
		return row < fileLines.size() - 1;
	}
	
	private void nextCol() {
		column++;
	}
	
	private void nextRow() {
		row++;
		column = 0;
		while (!hasMoreCol())
			nextRow();
	}
}
