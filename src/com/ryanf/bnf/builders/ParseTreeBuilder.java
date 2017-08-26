package com.ryanf.bnf.builders;

import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IToken;
import com.ryanf.bnf.tree.AlterListNode;
import com.ryanf.bnf.tree.AsignStatNode;
import com.ryanf.bnf.tree.CharListNode;
import com.ryanf.bnf.tree.CharNode;
import com.ryanf.bnf.tree.IdentNode;
import com.ryanf.bnf.tree.ListNode;
import com.ryanf.bnf.tree.NumberNode;
import com.ryanf.bnf.tree.StatListNode;
import com.ryanf.bnf.tree.StrNode;
import com.ryanf.bnf.tree.SubStatNode;

public class ParseTreeBuilder {
	public static IAstNode createStatListNode() {
		return new StatListNode();
	}
	
	public static IAstNode createAsignStatNode() {
		return new AsignStatNode();
	}
	
	public static IAstNode createIdentNode(IToken token) {
		return new IdentNode(token);
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
}
