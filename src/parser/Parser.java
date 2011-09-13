package parser;

public abstract class Parser {

	private Tokenizer tokenizer;

	public Parser(Tokenizer tokenizer) {
		this.tokenizer = tokenizer;
	}

	public Parser(Parser parent) {
		this(parent.tokenizer());
	}

	public Tokenizer tokenizer() {
		return tokenizer;
	}

	public abstract void parse();
}
