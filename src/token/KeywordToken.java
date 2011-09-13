package token;

import java.io.IOException;

import main.Keyword;

import parser.Source;

public class KeywordToken extends Token {

	public KeywordToken(Source source) {
		super(source);
	}

	@Override
	public void extract() throws IOException {
		char current = source().next();
		StringBuilder word = new StringBuilder();
		while (Character.isLetter(current)) {
			word.append(current);
			current = source().next();
			System.out.println(current);
		}

		type = TokenType.OBJECT;
		value = Keyword.get(word.toString());
		text = word.toString();
	}

}
