package parser;

import interpreter.Stack;
import interpreter.TableEntry;

import java.io.IOException;

import message.MessageHandler;

import token.EofToken;
import token.ErrorCode;
import token.Token;
import token.TokenType;

public class IlexParser extends Parser {

	private static final Stack stack = Stack.getInstance();
	private static final MessageHandler messages = MessageHandler.getInstance();

	public IlexParser(Tokenizer tokenizer) {
		super(tokenizer);
	}

	public IlexParser(Parser parent) {
		this(parent.tokenizer());
	}

	@Override
	public void parse() {
		long elapsed = System.currentTimeMillis();
		Token token = null;
		try {
			while (!((token = tokenizer().next()) instanceof EofToken)) {
				TokenType type = token.type();
				if (type == TokenType.IDENTIFIER) {
					String name = token.text();

					TableEntry entry = stack.lookup(name);
					if (entry == null) {
						entry = stack.enter(name);
					}

					entry.addLine(token.line());
				} else if (type == TokenType.ERROR) {
					messages.error(token, (ErrorCode) token.value());
				}

				if (type != TokenType.ERROR) {
					messages.token(token);
				}

			}
		} catch (IOException e) {
			MessageHandler.getInstance().fatal(ErrorCode.IO_ERROR, e);
		}

		elapsed = System.currentTimeMillis() - elapsed;
		MessageHandler.getInstance().summary(elapsed);
	}
}
