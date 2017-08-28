package com.ryanf.bnf;

import com.ryanf.bnf.builders.ParserBuilder;
import com.ryanf.bnf.builders.SourceBuilder;
import com.ryanf.bnf.builders.TokensBuilder;
import com.ryanf.bnf.exceptions.ParserException;
import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.ISource;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.interfaces.ITokens;
import com.ryanf.bnf.types.AstNodeType;

public class Test {

	public static void main(String[] args) {
		String filePath = "D:\\eclipse-workspace\\bnf\\xml.txt";
	
		/*
		ITokens tokens = TokensBuilder.createTokens(SourceBuilder.createSource(filePath));
		while (tokens.hasMore()) {
			tokens.next();
			IToken token = tokens.getToken();
			System.out.println(token.toString());
		}*/
		System.out.println("Start");
		Parser parser = ParserBuilder.createParser(filePath);
		IAstNode parseTree = null;
		try {
			parseTree = parser.parse();
			System.out.println(parseTree.toString());
		} catch (ParserException e) {
			System.out.println("Exception");
		}
		System.out.println("End");
		
		System.out.println("first:");
		if (parseTree != null) {
			for (int i = 0; i < parseTree.getChildrenCount(); i++)
				printFirst(parseTree.getChild(i));
		}
		System.out.println("End first");
	}
	
	private static void printFirst(IAstNode node) {
		if (node != null) {
			for (int i = 0; i < node.getChildrenCount(); i++) {
				IAstNode child = node.getChild(i);
				if (child.getType() == AstNodeType.IDENT)
					printFirst(child);
				else
					System.out.println(node.getChild(i).firsts().toString());
			}
		}
	}
}
