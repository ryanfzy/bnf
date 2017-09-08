package com.ryanf.bnf.builders;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IAstTree;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.tree.AlterListNode;
import com.ryanf.bnf.tree.AsignStatNode;
import com.ryanf.bnf.tree.AstTree;
import com.ryanf.bnf.tree.CharListNode;
import com.ryanf.bnf.tree.CharNode;
import com.ryanf.bnf.tree.CharRangeNode;
import com.ryanf.bnf.tree.EmptyNode;
import com.ryanf.bnf.tree.IdentNode;
import com.ryanf.bnf.tree.ListNode;
import com.ryanf.bnf.tree.NumberNode;
import com.ryanf.bnf.tree.StatListNode;
import com.ryanf.bnf.tree.StrNode;
import com.ryanf.bnf.tree.SubStatNode;
import com.ryanf.bnf.types.AstNodeType;

public class ParseTreeBuilder {
	static IAstNode emptyNode;
	
	public static IAstNode createStatListNode() {
		return new StatListNode();
	}
	
	public static IAstNode createAsignStatNode() {
		return new AsignStatNode();
	}
	
	public static IAstNode createAsignStatNode(IAstNode lhs, IAstNode rhs) {
		return new AsignStatNode(lhs, rhs);
	}
	
	public static IAstNode createIdentNode(String name) {
		return new IdentNode(name);
	}
	
	public static IAstNode createIdentNode(IAstNode identNode) {
		if (identNode.getType() == AstNodeType.IDENT)
			return new IdentNode((IdentNode)identNode);
		return null;
	}
	
	public static IAstNode createListNode() {
		return new ListNode();
	}
	
	public static IAstNode createNumberNode(IToken token) {
		return new NumberNode(token);
	}
	
	public static IAstNode createStrNode(IToken token) {
		return new StrNode(token);
	}

	public static IAstNode createCharListNode() {
		return new CharListNode();
	}

	public static IAstNode createCharNode(IToken token) {
		return new CharNode(token);
	}

	public static IAstNode createAlterListNode() {
		return new AlterListNode();
	}

	public static IAstNode createSubStatNode() {
		return new SubStatNode();
	}
	
	public static IAstNode createSubStatNode(IAstNode left, IAstNode right) {
		return new SubStatNode(left, right);
	}
	
	public static IAstNode createCharRangeNode(IAstNode from, IAstNode to) {
		return new CharRangeNode(from, to);
	}
	
	public static IAstTree createAstTree(IAstNode statListNode) {
		return new AstTree(statListNode);
	}
	
	public static IAstNode createEmptyNode() {
		if (emptyNode == null)
			emptyNode = new EmptyNode();
		return emptyNode;
	}
}
