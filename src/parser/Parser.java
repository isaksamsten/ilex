package parser;

import java.io.IOException;

import parser.tree.Node;

import message.MessageHandler;
import token.Token;
import token.plog.ErrorCode;

public abstract class Parser {

	private Tokenizer tokenizer;
	private int startLine = 0;

	public Parser(Tokenizer tokenizer) {
		this.tokenizer = tokenizer;
		this.startLine = tokenizer().source().line();
	}

	public Parser(Parser parent) {
		this(parent.tokenizer());
	}

	public Tokenizer tokenizer() {
		return tokenizer;
	}

	public int startLine() {
		return startLine;
	}

	public boolean errors() {
		return MessageHandler.getInstance().errors() > 0;
	}

	protected void error(Token token, ErrorCode code) {
		if (token.eof()) {
			MessageHandler.getInstance().error(token, ErrorCode.PREMATURE_EOF);
		} else {
			MessageHandler.getInstance().error(token, code);
		}
	}

	protected void error(ErrorCode code) {
		error(tokenizer().current(), code);
	}

	public Node parse() throws IOException {
		return parse(tokenizer().next());
	}

	/**
	 * 
	 * @param t
	 * @return a {@link Node} if parse was successful, otherwise null
	 * @throws IOException
	 */
	public abstract Node parse(Token t) throws IOException;

}
