package parser;

import java.io.IOException;

import main.IKeyword;

import token.EofToken;
import token.ErrorCode;
import token.ErrorToken;
import token.KeywordToken;
import token.NumberToken;
import token.SpecialToken;
import token.Token;
import token.TokenType;
import token.WordToken;

public class IlexTokenizer extends Tokenizer {

	public IlexTokenizer(Source source) {
		super(source);
	}

	@Override
	public Token extract() throws IOException {
		skipWhitespace();
		Token token = null;
		char currentChar = source().current();
		if (currentChar == Source.EOF) {
			token = new EofToken(source());
		} else if (Character.isLetter(currentChar)) {
			token = new WordToken(source());
		} else if (currentChar == IKeyword.START
				&& Character.isLetter(source().peek())) {
			token = new KeywordToken(source());
		} else if (TokenType.isSpecial(String.valueOf(currentChar))) {
			token = new SpecialToken(source());
		} else if (Character.isDigit(currentChar)) {
			token = new NumberToken(source());
		} else {
			token = new ErrorToken(source(), ErrorCode.INVALID_CHARACTER,
					String.valueOf(currentChar));
		}

		token.extract();
		return token;
	}

	private void skipWhitespace() throws IOException {
		char current = source().current();
		while (Character.isWhitespace(current) || current == '#') {
			if (current == '#') {
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
}
