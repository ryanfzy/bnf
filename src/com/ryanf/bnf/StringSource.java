package com.ryanf.bnf;

import com.ryanf.bnf.exceptions.OutOfCharException;
import com.ryanf.bnf.interfaces.ISource;

public class StringSource implements ISource {
	private String str;
	private int curPos;

	public StringSource(String str) {
		this.str = str;
		curPos = -1;
	}
	
	@Override
	public char getChar() {
		return str.charAt(curPos);
	}

	@Override
	public int getColumn() {
		return curPos;
	}

	@Override
	public int getLine() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasMore() {
		return curPos < str.length() - 1;
	}

	@Override
	public char peek(int pos) throws OutOfCharException {
		int peekPos = curPos + pos;
		if (peekPos >= str.length())
			throw new OutOfCharException();
		return str.charAt(peekPos);
	}

	@Override
	public void next() throws OutOfCharException {
		int nextPos = curPos + 1;
		if (nextPos >= str.length())
			throw new OutOfCharException();
		curPos = nextPos;
	}

	@Override
	public void previous() {
		curPos = curPos == 0 ? 0 : curPos - 1;
	}

}
