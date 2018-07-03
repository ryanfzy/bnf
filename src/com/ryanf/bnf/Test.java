package com.ryanf.bnf;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

import com.ryanf.bnf.builders.ParseTableBuilder;
//import com.ryanf.bnf.builders.ParseTreeBuilder;
import com.ryanf.bnf.builders.ParserBuilder;
import com.ryanf.bnf.builders.ProductTableBuilder;
import com.ryanf.bnf.converters.CConverter;
import com.ryanf.bnf.exceptions.ParserException;
import com.ryanf.bnf.helpers.AstTreeNormaliser;
import com.ryanf.bnf.helpers.ParseTableHelper;
//import com.ryanf.bnf.interfaces.IAstNode;
//import com.ryanf.bnf.interfaces.IAstTree;
//import com.ryanf.bnf.interfaces.INormalisedAstTree;
import com.ryanf.bnf.interfaces.IParseTable;

public class Test {

	public static void main(String[] args) {
		/*
		String filePath = "D:\\eclipse-workspace\\bnf\\xml.txt";
		String tableHtml = "D:\\eclipse-workspace\\bnf\\table.html";
		String output = "D:\\eclipse-workspace\\bnf\\output.c";
	
		//System.out.println("Start");
		Parser parser = ParserBuilder.createParser(filePath);
		
		try {
			CConverter converter = new CConverter(parser.parse());
			Files.write(Paths.get(output), converter.toCode().getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("done.");*/
		/*
		//IAstTree tree = null;
		INormalisedAstTree normalisedTree = null;
		try {
			normalisedTree = ParseTreeBuilder.createNormalisedAstTree(parser.parse());
			//AstTreeNormaliser.normalise(tree);
			//System.out.println(tree.getRoot().toString());
		} catch (ParserException e) {
			e.printStackTrace();
			return;
		}
		//System.out.println("End");
		
		System.out.println("first:");
		if (normalisedTree != null) {
			IAstNode root = normalisedTree.getStatListNode();
			for (int i = 0; i < root.getChildrenCount(); i++) {
				System.out.println("("+i+")"+root.getChild(i).toString());
				try {
					System.out.print(" => ");
					System.out.println(normalisedTree.getFirsts(root.getChild(i)).toString());
					if (root.getChild(i).getChild(1).contains(ParseTreeBuilder.createEmptyNode())) {
						System.out.print(" => ");
						System.out.println(normalisedTree.getFollows(root.getChild(i)));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
			}
		}
		System.out.println("End first");

		//System.out.println("row names:");
		IParseTable table = null;
		try {
			table = ParseTableBuilder.createParseTable(normalisedTree);
			Vector<String> products = ProductTableBuilder.createProductTable(normalisedTree, table);
			ParseTableHelper.setTableEntries(normalisedTree, table);
			for (String product : products)
				System.out.println(product);
			Files.write(Paths.get(tableHtml), ParseTableHelper.toHtml(table).getBytes());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	}
}
