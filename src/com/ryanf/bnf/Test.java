package com.ryanf.bnf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.ryanf.bnf.builders.ParseTableBuilder;
import com.ryanf.bnf.builders.ParserBuilder;
import com.ryanf.bnf.exceptions.ParserException;
import com.ryanf.bnf.helpers.ParseTableHelper;
import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IParseTable;

public class Test {

	public static void main(String[] args) {
		String filePath = "D:\\eclipse-workspace\\bnf\\xml.txt";
		String tableHtml = "D:\\eclipse-workspace\\bnf\\table.html";
	
		//System.out.println("Start");
		Parser parser = ParserBuilder.createParser(filePath);
		IAstNode parseTree = null;
		try {
			parseTree = parser.parse();
			//System.out.println(parseTree.toString());
		} catch (ParserException e) {
			System.out.println("Exception");
		}
		//System.out.println("End");
		
		/*
		System.out.println("first:");
		if (parseTree != null) {
			for (int i = 0; i < parseTree.getChildrenCount(); i++) {
				System.out.println(parseTree.getChild(i).toString());
				System.out.print(" => ");
				System.out.println(parseTree.getChild(i).firsts().toString());
			}
		}
		System.out.println("End first");
		
		System.out.println("row names:");*/
		IParseTable table = ParseTableBuilder.createParseTable(parseTree);
		for (String row : table.getRows()) {
			System.out.print(row + ":");
			for (String column : table.getColumns()) {
				System.out.print(table.getEntry(row, column));
				System.out.print(",");
			}
			System.out.println();
		}
		/*
		for (String row : table.getRows())
			System.out.println(row);
		System.out.println("col names:");
		for (String col : table.getColumns())
			System.out.println(col);*/
		
		try {
			Files.write(Paths.get(tableHtml), ParseTableHelper.toHtml(table).getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
