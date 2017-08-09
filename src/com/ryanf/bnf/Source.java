package com.ryanf.bnf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.ryanf.bnf.interfaces.ISource;

public class Source implements ISource {
	Path filePath;
	String fileSource;
	int charPos;
	
	public Source(String fileName) {
		filePath = Paths.get(fileName);
	}

	public char getChar() {
		if (canReadFile())
			return fileSource.charAt(charPos);
		return Character.MIN_VALUE;
		
	}
	
	public void moveForward() {
		charPos++;
	}
	
	public void moveBackward() {
		charPos--;
	}
	
	public boolean hasMore() {
		if (canReadFile())
			return (charPos + 1) < fileSource.length();
		return false;
	}
	
	private void readFile() throws IOException {
		fileSource = new String(Files.readAllBytes(filePath));
		charPos = -1;
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
		if (fileSource == null || fileSource.isEmpty()) {
			if (!tryReadFile())
				return false;
		}
		return true;
	}
}
