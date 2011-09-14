package token;

import parser.Source;

public class EofToken extends Token {

	public EofToken(Source source) {
		super(source);
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
