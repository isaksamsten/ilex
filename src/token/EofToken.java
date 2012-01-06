package token;

import parser.Source;
import token.plog.TokenType;

public class EofToken extends Token {

	public EofToken(Source source) {
		super(source);
		text = "EOF";
		value = "EOF";
		type = TokenType.END_OF_FILE;
	}

	@Override
	public boolean eof() {
		return true;
	}

	@Override
	public void extract() {
		// EoFtoken
	}

}
