package token;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum TokenType {
	IDENTIFIER, INTEGER, DOUBLE, ERROR, OBJECT,

	RETURN, FUNCTION,

	PLUS("+"), MINUS("-"), STAR("*"), SLASH("/"), LEFT_BRACE("{"), RIGHT_BRACE(
			"}"), EQUAL("="), SEMI_COLON(";"), AMPERSAND("&"), LEFT_PAREN("("), RIGHT_PAREN(
			")"), LEFT_BRACKET("["), RIGHT_BRACKET("]");

	private static Set<String> reserved = new HashSet<String>();
	private static Map<String, TokenType> special = new HashMap<String, TokenType>();
	static {
		TokenType[] tokens = TokenType.values();
		for (int i = RETURN.ordinal(); i <= FUNCTION.ordinal(); i++) {
			reserved.add(tokens[i].text());
		}

		for (int i = PLUS.ordinal(); i <= RIGHT_BRACKET.ordinal(); i++) {
			special.put(tokens[i].text(), tokens[i]);
		}
	}

	public static boolean isReserved(String str) {
		return reserved.contains(str);
	}

	public static boolean isSpecial(String str) {
		return special.containsKey(str);
	}

	public static TokenType special(String str) {
		return special.get(str);
	}

	private String text;

	TokenType(String text) {
		this.text = text;
	}

	TokenType() {
		this.text = this.toString().toLowerCase();
	}

	public String text() {
		return text;
	}
}
