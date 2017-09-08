package com.ryanf.bnf.helpers;

import java.util.HashMap;
import java.util.Map;

import com.ryanf.bnf.builders.ParseTreeBuilder;
import com.ryanf.bnf.interfaces.IAstNode;
import com.ryanf.bnf.interfaces.IAstTree;
import com.ryanf.bnf.types.AstNodeType;
import com.ryanf.bnf.types.QuantifierType;

public class AstTreeNormaliser {
	Map<String, IAstNode> normalisedNodes;
	IAstTree tree;
	IAstNode statListNode;
	int nodeNum;
	
	private AstTreeNormaliser(IAstTree tree) {
		this.tree = tree;
		statListNode = tree.getRoot();
		init();
	}
	
	private void init() {
		normalisedNodes = new HashMap<String, IAstNode>();
		nodeNum = 1;
	}
	
    private String createNodeName() {
    	return "normalised-node-" + nodeNum++;
    }
    
    private void addNode(String key, IAstNode asignNode) {
    	addNode(asignNode);
    	normalisedNodes.put(key, asignNode);
    }
    
    private void addNode(IAstNode asignNode) {
    	statListNode.addChild(asignNode);
    }
    
    private void addEmptyNode(IAstNode identNode) {
    	addNode(ParseTreeBuilder.createAsignStatNode(identNode, ParseTreeBuilder.createEmptyNode()));
    }
    
    private boolean hasNode(String key) {
    	return normalisedNodes.containsKey(key);
    }
    
    private IAstNode getNode(String key) {
    	return normalisedNodes.get(key).getChild(0);
    }
    
    private IAstNode createOneOrMoreNode(IAstNode node) {
    	IAstNode identNode = null;
    	if (hasNode(node.toString()))
    		identNode = getNode(node.toString());
    	else {
    		identNode = ParseTreeBuilder.createIdentNode(createNodeName());
    		IAstNode listNode = null;
    		if (node.getType() == AstNodeType.NODELIST)
    			listNode = node;
    		else {
    			listNode = ParseTreeBuilder.createListNode();
    			listNode.addChild(node);
    		}
	    	listNode.addChild(createZeroOrMoreNode(node));
	    	IAstNode normalisedAsignNode = ParseTreeBuilder.createAsignStatNode(identNode, listNode);
	    	addNode(node.toString(), normalisedAsignNode);
	    	node.setQuantifier(QuantifierType.ONE);
	    	normalise(listNode);
    	}
    	return identNode;
    }
    
    private IAstNode createZeroOrOneNode(IAstNode node) {
    	IAstNode identNode = null;
    	if (hasNode(node.toString()))
    		identNode = getNode(node.toString());
    	else {
    		identNode = ParseTreeBuilder.createIdentNode(createNodeName());
    		IAstNode normalisedAsignNode = ParseTreeBuilder.createAsignStatNode(identNode, node);
    		addNode(node.toString(), normalisedAsignNode);
    		node.setQuantifier(QuantifierType.ONE);
    		addEmptyNode(identNode);
    		normalise(normalisedAsignNode);
    	}
    	return identNode;
    }
    
    private IAstNode createZeroOrMoreNode(IAstNode node) {
    	IAstNode identNode = null;
    	if (hasNode(node.toString()))
    		identNode = getNode(node.toString());
    	else {
    		identNode = ParseTreeBuilder.createIdentNode(createNodeName());
    		IAstNode listNode = null;
    		if (node.getType() == AstNodeType.NODELIST)
    			listNode = node;
    		else {
    			listNode = ParseTreeBuilder.createListNode();
    			listNode.addChild(node);
    		}
	    	listNode.addChild(identNode);
	    	IAstNode normalisedAsignNode = ParseTreeBuilder.createAsignStatNode(identNode, listNode);
	    	addNode(node.toString(), normalisedAsignNode);
	    	node.setQuantifier(QuantifierType.ONE);
	    	addEmptyNode(identNode);
	    	normalise(listNode);
    	}
    	return identNode;
    }
	
	private IAstNode normalise(IAstNode node) {
		//System.out.println(node.toString());
		if (node.getQuantifier() == QuantifierType.ZERO_OR_MORE) {
			return createZeroOrMoreNode(node);
		}
		else if (node.getQuantifier() == QuantifierType.ZERO_OR_ONE) {
			return createZeroOrOneNode(node);
		}
		else if (node.getQuantifier() == QuantifierType.ONE_OR_MORE) {
			return createOneOrMoreNode(node);
		}
		else if (node.getType() == AstNodeType.NODELIST) {
			for (int i = 0; i < node.getChildrenCount(); i++) {
				IAstNode normalisedNode = normalise(node.getChild(i));
				if (normalisedNode != null)
					node.setChild(i, normalisedNode);
			}
		}
		else if (node.getType() == AstNodeType.ALTERNODELIST) {
			IAstNode identNode = ParseTreeBuilder.createIdentNode(createNodeName());
			for (int i = 0; i < node.getChildrenCount(); i++) {
				IAstNode asignNode = ParseTreeBuilder.createAsignStatNode(identNode, node.getChild(i));
				addNode(asignNode);
				IAstNode normalisedNode = normalise(asignNode.getChild(1));
				if (normalisedNode != null)
					asignNode.setChild(1, normalisedNode);
			}
			return identNode;
		}
		return null;
	}
	
	private void normalise() {
		for (int i = 0; i < statListNode.getChildrenCount(); i++) {
			IAstNode statNode = statListNode.getChild(i);
			IAstNode normalisedNode = normalise(statNode.getChild(1));
			if (normalisedNode != null)
				statNode.setChild(1, normalisedNode);
		}
	}
	
	public static void normalise(IAstTree tree) {
		AstTreeNormaliser normaliser = new AstTreeNormaliser(tree);
		normaliser.normalise();
	}
}
