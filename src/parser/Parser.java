package parser;

import java.io.IOException;

import message.MessageHandler;

import parser.tree.Node;
import token.ErrorCode;
import token.Token;

public abstract class Parser<TNode> {

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

	protected void error(Token token, ErrorCode code) {
		MessageHandler.getInstance().error(token, code);
	}

	public abstract TNode parse() throws IOException;
}
