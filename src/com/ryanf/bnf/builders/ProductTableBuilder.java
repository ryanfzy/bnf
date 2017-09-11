package com.ryanf.bnf.builders;

import java.util.Vector;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IAstTree;
import com.ryanf.bnf.interfaces.IParseTable;
import com.ryanf.bnf.types.AstNodeType;

public class ProductTableBuilder {
	public static Vector<String> createProductTable(IAstTree tree, IParseTable table) {
		Vector<String> products = new Vector<String>();
		IAstNode statListNode = tree.getRoot();
		for (int i = 0; i < statListNode.getChildrenCount(); i++) {
			IAstNode rhs = statListNode.getChild(i).getChild(1);
			StringBuilder product = new StringBuilder();
			if (rhs.getType() == AstNodeType.NODELIST) {
				for (int j = 0; j < rhs.getChildrenCount(); j++)
					product.append(getProduct(table, rhs.getChild(j)));
			}
			else
				product.append(getProduct(table, rhs));
			products.add(product.toString());
		}
		return products;
	}
	
	private static String getProduct(IParseTable table, IAstNode node) {
		if (node.getType() == AstNodeType.IDENT)
			return String.format("%03d", table.getProductId(node.toString()));
		else
			return String.format("%03d", table.getTokenId(node.toString()));
	}
}
