package org.victorynox.rql;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Object for stage template/pattern value
 *
 * @author victorynox
 * @version 0.1
 */
public class Glob {

	/**
	 * string with glob
	 */
	private String glob;

	public Glob(String glob) {
		this.glob = glob;
	}

	/**
	 * Encode raw string value
	 *
	 * @param value non encoded string
	 * @return encoded string
	 */
	public static String encoded(String value) {
		String[] repChars = {"\\", "?", "*"};
		return escaped(value, repChars);
	}

	/**
	 * Escaping string with slash
	 *
	 * @param value    string which been escaped
	 * @param repChars array with escaped char
	 * @return escaped string
	 */
	private static String escaped(String value, String[] repChars) {
		for (String repChar : repChars) {
			if (value.contains(repChar)) {
				value = value.replace(repChar, "\\" + repChar);
			}
		}
		return value;
	}

	@Override
	public String toString() {
		return glob;
	}

	/**
	 * Convert glob to rql style
	 *
	 * @return rql style glob
	 */
	public String toRql() {
		return this.decoder("*", "?", (String s) -> {
			try {
				//TODO: refactor.
				s = URLEncoder.encode(s, "UTF-8");
				s = s.replace("+", "%20");
				s = s.replace("*", "%2A");
				s = s.replace("-", "%2D");
				s = s.replace("_", "%5F");
				s = s.replace(".", "%2E");
				s = s.replace("~", "%7E");
			} catch (UnsupportedEncodingException ignored) {
				//TODO: is bad code style.
			}
			return s;
		});
	}

	/**
	 * Convert glob to tegex pattern style
	 *
	 * @return pattern glob style string
	 */
	public String toRegex() {
		//noinspection RegExpRedundantEscape
		return this.decoder(".*", ".", (String s) -> s.replaceAll("([.\\\\+*?\\[\\^\\]$(){}=!<>|:\\-])",  "\\\\$1"));
	}

	/**
	 * Convert glob to like style
	 *
	 * @return LIKE stile glob string
	 */
	public String toLike() {
		return this.decoder("%", "_", (String s) -> {
			String[] escapedChars = {"\\", "%", "_"};
			return escaped(s, escapedChars);
		});
	}

	/**
	 * Decoded string with rules
	 *
	 * @param many    - char which visualize many chars pattern
	 * @param one     - char which visualize one char pattern
	 * @param escaper - callback ho decoded another pattern
	 * @return decoded string
	 */
	private String decoder(String many, String one, Function<String, String> escaper) {
		StringBuilder result = new StringBuilder();

		//TODO: refactor this.
		Pattern pattern = Pattern.compile("\\\\\\\\|\\\\\\*|\\\\\\?|.");
		final Matcher matcher = pattern.matcher(this.glob);

		int index = 0;
		while (matcher.find()) {
			final MatchResult matchResult = matcher.toMatchResult();
			String find = matchResult.group();
			String replaceTo;
			switch (find) {
				case "*":
					replaceTo = many;
					break;
				case "?":
					replaceTo = one;
					break;
				default:
					//TODO: add remove escape slash
					String randomHash = "pTKg1fX3mPVFcj6ICWGCao6U2FsF3SLCVozmBNfUmov";
					String val = find.replaceAll("\\\\\\\\", randomHash); //avoid removed slash escaping
					val = val.replaceAll("\\\\", "");
					val = val.replaceAll(randomHash, "\\\\"); //avoid removed slash escaping

					replaceTo = escaper.apply(val);
					break;
			}
			result.append(replaceTo);
			index = matcher.end();
		}
		if(index < this.glob.length()) {
			result.append(this.glob.substring(index));
		}
		return result.toString();
	}

	/**
	 * @param o - comparable object
	 * @return bool
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Glob glob1 = (Glob) o;
		return glob.equals(glob1.glob);
	}

	@Override
	public int hashCode() {
		return glob.hashCode();
	}
}
