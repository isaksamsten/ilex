package token;

import java.io.IOException;

import parser.Source;

public class SpecialToken extends Token {

	public SpecialToken(Source source) {
		super(source);
	}

	@Override
	public void extract() throws IOException {
		text = String.valueOf(source().current());
		type = TokenType.special(text);
		value = text;

		source().next();
	}

}
