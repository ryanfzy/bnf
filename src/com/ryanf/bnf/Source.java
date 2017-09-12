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
	int line;
	
	public Source(String fileName) {
		filePath = Paths.get(fileName);
	}

	public char getChar() {
		return getChar(line, column);
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getLine() {
		return line;
	}
	
	public void next() throws OutOfCharException {
		if (hasMoreColumn())
			nextColumn();
		else if (hasMoreLine())
			nextLine();
		else
			throw new OutOfCharException();
	}
	
	public void previous() {
		throw new UnsupportedOperationException();
	}
	
	public char peek(int pos) throws OutOfCharException {
		int curColumn = column + pos;
		int curLine = line;
		while (curColumn > fileLines.get(curLine).length() - 1) {
			if (curLine >= fileLines.size())
				throw new OutOfCharException();
			curColumn = fileLines.get(curLine).length() - curColumn;
			curLine++;
		}
		return getChar(curLine, curColumn);
	}
	
	public boolean hasMore() {
		if (canReadFile())
			return hasMoreColumn() || hasMoreLine();
		return false;
	}
	
	private void init() {
		line = 0;
		column = -1;
	}
	
	private  char getChar(int line, int column) {
		return fileLines.get(line).charAt(column);
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
	
	private boolean hasMoreColumn() {
		return column < fileLines.get(line).length() - 1;
	}
	
	private boolean hasMoreLine() {
		return line < fileLines.size() - 1;
	}
	
	private void nextColumn() {
		column++;
	}
	
	private void nextLine() {
		line++;
		column = 0;
		while (!hasMoreColumn())
			nextLine();
	}
}
