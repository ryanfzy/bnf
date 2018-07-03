package com.ryanf.bnf.builders;

import com.ryanf.bnf.Source;
import com.ryanf.bnf.StringSource;
import com.ryanf.bnf.interfaces.ISource;

public class SourceBuilder {
	public static ISource createSource(String fileName) {
		return new Source(fileName);
	}
	
	public static ISource createStringSource(String str) {
		return new StringSource(str);
	}
}
