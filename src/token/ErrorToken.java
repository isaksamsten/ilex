package token;

import java.io.IOException;

import parser.Source;

public class ErrorToken extends Token {

	public ErrorToken(Source source, ErrorCode code, String errorChar) {
		super(source);

		text = errorChar;
		value = code;
		type = TokenType.ERROR;
	}

	@Override
	public void extract() throws IOException {
		source().next();
	}
}
