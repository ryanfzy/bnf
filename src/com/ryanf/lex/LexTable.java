package com.ryanf.lex;

import java.util.Stack;
import java.util.Vector;

import com.ryanf.lex.types.ValueType;

public class LexTable extends Vector<Vector<Integer>> {
	Vector<Long> colHeaders;
	Vector<Integer> fillValues;
	Vector<String> rowNames;
	Vector<Vector<TokenInfo>> tokenInfoList;
	int errorNum = -99;
	int REVER_ONE_CHAR_FLAG = -100;
	public static int ERROR_NUMBER = -99;
	
	public LexTable() {
		colHeaders = new Vector<Long>();
		rowNames = new Vector<String>();
		fillValues = new Vector<Integer>();
		tokenInfoList = new Vector<Vector<TokenInfo>>();
	}
	
	public void addTokenInfo(TokenInfo tokenInfo) {
		tokenInfoList.lastElement().add(tokenInfo);
	}
	
	public void addTokenInfo(int tokenId, TokenInfo tokenInfo) {
		tokenId = tokenId < 0 ? -tokenId : tokenId;
		//tokenId = tokenId > 100 ? tokenId - 100 : tokenId;
		tokenInfoList.get(tokenId-1).add(tokenInfo);
	}
	
	public Vector<String> TokenNames() {
		return rowNames;
	}
	
	public Vector<Vector<TokenInfo>> TokenInfoList() {
		return tokenInfoList;
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
		tokenInfoList.add(new Vector<TokenInfo>());
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
	
	public void fillRow(int rowIndex, int value) {
		if (rowIndex < size()) {
			Vector<Integer> row = get(rowIndex);
			for (int i = 0; i <row.size(); i ++)
				row.set(i, value);
			fillValues.set(rowIndex, value);
		}
	}
	
	public String GetCFileOutput() {
		StringBuilder builder = new StringBuilder();
		
		// output token id
		for (int i = 0; i < rowNames.size(); i++) {
			builder.append(String.format("#define %s %d\n", rowNames.get(i), i));
		}
		
		// output token info
		builder.append("typedef struct _tokenInfo\n{\n    int id;\n    bool moveNext;\n} TokenInfo;\n");
		builder.append("typedef struct _tokenInfoList\n{\n    int count;\n    TokenInfo *plist;\n} TokenInfoList;\n");
		for (int i = 0; i < tokenInfoList.size(); i++) {
			Vector<TokenInfo> tokens = tokenInfoList.get(i);
			builder.append(String.format("TokenInfo tokenInfo%d[%d] = { ", i, tokens.size()));
			for (int j = 0; j < tokens.size(); j++) {
				if (j > 0)
					builder.append(", ");
				builder.append(String.format("{ %s, %s }", tokens.get(j).Name(), tokens.get(j).MoveNext() ? "true" : "false"));
			}
			builder.append(" };\n");
		}
		builder.append(String.format("TokenInfoList TOKEN_INFO_LIST[%d] =\n{\n", tokenInfoList.size()));
		for (int i = 0; i < tokenInfoList.size(); i++) {
			builder.append(String.format("    { %d, (TokenInfo*)&tokenInfo%d }", tokenInfoList.get(i).size(), i));
			if (i < tokenInfoList.size()-1)
				builder.append(",");
			builder.append("\n");
		}
		builder.append("};\n");
		
		// output lex table
		builder.append(String.format("int lex_table[%d][%d] =\n{\n", size(), colHeaders.size()));
		for (int i = 0; i < size(); i++) {
			builder.append("    {");
			Vector<Integer> row = get(i);
			for (int j = 0; j < row.size(); j++) {
				builder.append(String.format("%4d", row.get(j)));
				if (j < row.size()-1)
					builder.append(String.format(",", row.get(j)));
			}
			builder.append("},\n");
		}
		builder.append("};\n");
		
		// output token info
		builder.append(String.format("TokenInfo TokenInfoList[%d] =\n{\n", rowNames.size()));
		for (int i = 0; i < tokenInfoList.size(); i++) {
			Vector<TokenInfo> tokens = tokenInfoList.get(i);
			builder.append("    { ");
			for (int j = 0; j < tokens.size(); j++) {
				TokenInfo tokenInfo = tokens.get(j);
				if (j > 0)
					builder.append(", ");
				builder.append(String.format("{ %s, %s }", tokenInfo.Name(), tokenInfo.MoveNext() ? "true" : "false"));
			}
			if (i == tokenInfoList.size()-1)
				builder.append(" }\n");
			else
				builder.append(" },\n");
		}
		builder.append("};\n");
		
		// output char to code function
		builder.append("int chr_to_code(long ch)\n{\n");
		for (int i = 0; i < colHeaders.size(); i++)
			builder.append(String.format("    if (ch == 0x%x) return %d;\n", colHeaders.get(i), i));
		builder.append(String.format("    return %d;\n}\n", ERROR_NUMBER));
		
		// output code to token id
		builder.append("int code_to_token_entry(int code)\n{\n");
		for (int i = 0; i < rowNames.size(); i++)
			builder.append(String.format("    if (code == %d) return %d;\n", (i+1)*-1, i));
		builder.append(String.format("    return %d;\n}\n", ERROR_NUMBER*-1));
		return builder.toString();
	}
}