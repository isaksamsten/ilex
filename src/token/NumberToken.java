package token;

import java.io.IOException;
import java.math.BigInteger;

import parser.Source;

public class NumberToken extends Token {

	public NumberToken(Source source) {
		super(source);
	}

	@Override
	public void extract() throws IOException {
		StringBuilder builder = new StringBuilder();
		char current = source().current();
		while (Character.isDigit(current)
				|| (current == '.' && builder.indexOf(".") < 0)
				|| Character.isLetter(current)) {
			builder.append(current);
			current = source().next();
		}

		text = builder.toString();
		try {
			if (builder.indexOf(".") > -1) {
				type = TokenType.DOUBLE;
				value = Double.parseDouble(text);
			} else {
				type = TokenType.INTEGER;
				try {
					value = Long.parseLong(text);
				} catch (Exception e) {
					if (type == TokenType.INTEGER) {
						value = new BigInteger(text);
					}
				}
			}
		} catch (Exception e) {
			type = TokenType.ERROR;
			value = ErrorCode.INVALID_NUMBER;
		}

	}
}
