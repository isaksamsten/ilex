package token.plog;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import token.ITokenType;

public enum TokenType implements ITokenType {
	IDENTIFIER, NUMBER, DOUBLE, ERROR,

	PLUS("+"), MINUS("-"), STAR("*"), SLASH("/"), GT(">"), LT("<"), EQUAL("="), GTE(
			">="), GTL("<="), COLON(":");

	private static Set<String> reserved = new HashSet<String>();
	private static Map<String, TokenType> special = new HashMap<String, TokenType>();
	static {
		TokenType[] tokens = TokenType.values();
		for (int i = PLUS.ordinal(); i <= GTL.ordinal(); i++) {
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
