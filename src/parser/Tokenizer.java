package parser;

import java.io.IOException;

import token.Token;

public abstract class Tokenizer {

	private Source source;
	private Token current;
	private char startComment;

	public Tokenizer(Source source) {
		this.source = source;
	}

	public Source source() {
		return source;
	}

	public Token current() {
		return current;
	}

	public Token next() throws IOException {
		current = extract();
		return current;
	}

	public void startComment(char start) {
		startComment = start;
	}

	public char startComment() {
		return startComment;
	}

	protected void skipWhitespace() throws IOException {
		char current = source().current();
		while (Character.isWhitespace(current) || current == startComment()) {
			if (current == startComment()) {
				do {
					current = source().next();
				} while (current != Source.EOL && current != Source.EOF);
				if (current == Source.EOL) {
					current = source().next();
				}
			} else {
				current = source().next();
			}
		}
	}

	public abstract Token extract() throws IOException;
}
