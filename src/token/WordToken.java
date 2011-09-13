package token;

import java.io.IOException;

import parser.Source;

public class WordToken extends Token {

	public WordToken(Source source) {
		super(source);
	}

	@Override
	public void extract() throws IOException {
		StringBuilder textBuilder = new StringBuilder();

		char current = source().current();
		while (Character.isLetterOrDigit(current)) {
			textBuilder.append(current);
			current = source().next();
		}

		text = textBuilder.toString();
		value = null;
		type = TokenType.isReserved(text) ? TokenType.valueOf(text
				.toUpperCase()) : TokenType.IDENTIFIER;
	}
}
