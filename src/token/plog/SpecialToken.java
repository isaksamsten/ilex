package token.plog;

import java.io.IOException;

import parser.Source;
import token.Token;

public class SpecialToken extends Token {

	public SpecialToken(Source source) {
		super(source);
	}

	@Override
	public void extract() throws IOException {
		char current = source().current();
		char next = source().peek();
		if (current == '<' && next == '=') {
			next = source().next();
			text = current + "" + next;
			type = TokenType.special(text);
		} else if (current == '>' && next == '=') {
			next = source().next();
			text = current + "" + next;
			type = TokenType.special(text);
		} else if (current == ':' && next == '=') {
			next = source().next();
			text = current + "" + next;
			type = TokenType.special(text);
		} else {
			text = String.valueOf(source().current());
			type = TokenType.special(text);
		}
		value = text;
		source().next();
	}

}
