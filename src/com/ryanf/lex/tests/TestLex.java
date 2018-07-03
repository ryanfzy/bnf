package com.ryanf.lex.tests;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import com.ryanf.lex.Lex;
import com.ryanf.lex.LexTable;
import com.ryanf.lex.Parser;
import com.ryanf.lex.Token;

public class TestLex {

	@Test
	public void test() {
		String[] strs = {
			"XML_DECL_START = <?xml",
			"XML_DECL_END = ?>",
			"VERSION = version",
			"SPACE = [#x20 #x9 #xD #xA]+",
			"TAG_START = <",
			"TAG_END = >",
			"END_TAG_START = </",
			//"ENCODING = encoding",
			//"DOUBLE_QUOTE = \"",
			//"STANDALONE = standalone",
			//"YES = yes",
			//"NO = no",
		};
		Vector<Token> tokens = new Vector<Token>();
		for (String str : strs)
			tokens.add(Parser.parseString(str));
		Lex lex = new Lex(tokens);
		LexTable table = lex.getTable();
		System.out.print(String.format("%5s", "["));
		for (long colHeader : table.colHeaders()) {
			char ch = (char)colHeader;
			if (Character.isWhitespace(ch))
				System.out.print(String.format("%4x ", colHeader));
			else
				System.out.print(String.format("%4c ", ch));
		}
		System.out.println("]");
		for (int i = 0; i < table.size(); i++) {
			System.out.print(String.format("%4d[", i));
			for (int num : table.get(i))
				System.out.print(String.format("%4d,", num));
			System.out.println("]");
		}
	}

}
