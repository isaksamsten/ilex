package token.plog;

import java.io.IOException;

import parser.Source;
import token.Token;

public class StringToken extends Token {

	public static final char STRING = '"';

	public StringToken(Source source) {
		super(source);
	}

	@Override
	public void extract() throws IOException {
		char current = source().next(); // consume "
		StringBuilder builder = new StringBuilder();
		while (current != STRING && current != Source.EOF) {
			builder.append(current);
			current = source().next();
		}
		text = "\"" + builder.toString() + "\"";
		value = builder.toString().replace("\\n", "\n");
		if (current == Source.EOF) {
			type = TokenType.ERROR;
		} else {
			source().next(); // consume "
			type = TokenType.STRING;
		}
	}

}
