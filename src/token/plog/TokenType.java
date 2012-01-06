package token.plog;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import token.ITokenType;

public enum TokenType implements ITokenType {
	IDENTIFIER, NUMBER, END_OF_FILE, READ, WRITE, WHILE, BEGIN, END, ERROR,

	PLUS("+"), MINUS("-"), STAR("*"), SLASH("/"), GT(">"), LT("<"), EQUAL("="), COLON(
			":");

	private static Set<String> reserved = new HashSet<String>();
	private static Map<String, TokenType> special = new HashMap<String, TokenType>();
	static {
		TokenType[] tokens = TokenType.values();
		for (int i = PLUS.ordinal(); i <= COLON.ordinal(); i++) {
			special.put(tokens[i].text(), tokens[i]);
		}

		for (int i = READ.ordinal(); i <= END.ordinal(); i++) {
			reserved.add(tokens[i].text().toLowerCase());
		}
	}

	public static boolean isReserved(String str) {
		return reserved.contains(str.toLowerCase());
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