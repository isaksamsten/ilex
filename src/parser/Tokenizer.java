package parser;

import java.io.IOException;

import token.Token;

public abstract class Tokenizer {

	private Source source;
	private Token current;

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

	public abstract Token extract() throws IOException;
}
