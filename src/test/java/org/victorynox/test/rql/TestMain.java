package org.victorynox.test.rql;

import org.victorynox.rql.Glob;
import org.victorynox.rql.TokenType;

/**
 * @author victorynox
 * @version 0.1
 */
public class TestMain {

	public static void main(String[] args) {

		Glob glob1 = new Glob("asd");
		Glob glob2 = new Glob("as**d");
		Glob glob3 = new Glob("as.d%");
		Glob glob4 = new Glob("a.s.*d");
		Glob glob5 = new Glob("a%s_d");
		Glob[] globs = {glob1, glob2, glob3, glob4, glob5};

		for (Glob glob : globs) {
			System.out.println("Glob: " + glob);
			System.out.println("toLike: " + glob.toLike());
			System.out.println("toRegex: " + glob.toRegex());
			System.out.println("toRql: " + glob.toRql());
			System.out.println();
		}
	}
}
