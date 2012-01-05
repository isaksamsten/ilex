package token.plog;

import java.io.IOException;
import java.math.BigInteger;

import parser.Source;
import token.ErrorCode;
import token.Token;

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
				type = TokenType.NUMBER;
				value = Double.parseDouble(text);
			} else {
				type = TokenType.NUMBER;
				try {
					value = Long.parseLong(text);
				} catch (Exception e) {
					if (type == TokenType.NUMBER) {
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
