package com.ryanf.bnf.interfaces;

import com.ryanf.bnf.exceptions.ParserException;
import com.ryanf.bnf.tree.AstTree;

public interface IParser {
	/*IAstTree*/AstTree parse() throws ParserException;
}
