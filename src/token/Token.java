package token;

import java.io.IOException;

import parser.Source;

public class Token {

	private Source source;
	protected int line;
	protected int position;

	protected Object value;
	protected String text;
	protected TokenType type;

	public Token(Source source) {
		this.source = source;
		line = source.line();
		position = source.position();
	}

	public Source source() {
		return source;
	}

	public int line() {
		return line;
	}

	public int position() {
		return position;
	}

	public Object value() {
		return value;
	}

	public String text() {
		return text;
	}

	public TokenType type() {
		return type;
	}

	public boolean eof() {
		return false;
	}

	public void extract() throws IOException {
		text = String.valueOf(source().current());
		value = null;

		source().next();
	}
}
