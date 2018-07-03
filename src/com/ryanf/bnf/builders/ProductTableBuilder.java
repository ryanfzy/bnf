package com.ryanf.bnf.builders;

import java.util.Vector;

//import com.ryanf.bnf.interfaces.IAstNode;
//import com.ryanf.bnf.interfaces.IAstTree;
import com.ryanf.bnf.interfaces.IParseTable;
import com.ryanf.bnf.tree.AstNode;
import com.ryanf.bnf.tree.AstTree;
import com.ryanf.bnf.types.AstNodeType;

public class ProductTableBuilder {
	public static Vector<String> createProductTable(AstTree tree, IParseTable table) {
		return new Vector<String>();
		/*
		Vector<String> products = new Vector<String>();
		IAstNode statListNode = tree.getRoot();
		
		for (IAstNode statNode : statListNode.getChildren()) {
			IAstNode rhs = statNode.getChild(1);
			StringBuilder product = new StringBuilder();
			if (rhs.getType() == AstNodeType.NODELIST) {
				for (IAstNode child : rhs.getChildren())
					product.append(getProduct(table, child));
			}
			else
				product.append(getProduct(table, rhs));
			products.add(product.toString());
		}
		return products;*/
	}
	
	/*
	private static String getProduct(IParseTable table, AstNode node) {
		if (node.getType() == AstNodeType.IDENT)
			return String.format("%03d", table.getProductId(node.toString()));
		else
			return String.format("%03d", table.getTokenId(node.toString()));
	}*/
}
