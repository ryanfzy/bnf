package com.ryanf.bnf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.ryanf.bnf.builders.ParseTableBuilder;
import com.ryanf.bnf.builders.ParserBuilder;
import com.ryanf.bnf.exceptions.ParserException;
import com.ryanf.bnf.helpers.AstNodeHelper;
import com.ryanf.bnf.helpers.ParseTableHelper;
import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IAstTree;
import com.ryanf.bnf.interfaces.IParseTable;

public class Test {

	public static void main(String[] args) {
		String filePath = "D:\\eclipse-workspace\\bnf\\xml1.txt";
		String tableHtml = "D:\\eclipse-workspace\\bnf\\table1.html";
	
		//System.out.println("Start");
		Parser parser = ParserBuilder.createParser(filePath);
		IAstTree tree = null;
		try {
			tree = parser.parse();
			//System.out.println(tree.getRoot().toString());
		} catch (ParserException e) {
			e.printStackTrace();
			return;
		}
		//System.out.println("End");
		
		System.out.println("first:");
		if (tree != null) {
			IAstNode root = tree.getRoot();
			for (int i = 0; i < root.getChildrenCount(); i++) {
				System.out.println(root.getChild(i).toString());
				System.out.print(" => ");
				System.out.println(AstNodeHelper.GetFirsts(tree, root.getChild(i)).toString());
			}
		}
		System.out.println("End first");
		
		//System.out.println("row names:");
		IParseTable table = ParseTableBuilder.createParseTable(tree);
		
		try {
			Files.write(Paths.get(tableHtml), ParseTableHelper.toHtml(table).getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
