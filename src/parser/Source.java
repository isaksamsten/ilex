package parser;

import java.io.IOException;

public interface Source {
	public static final char EOF = (char) 0;
	public static final char EOL = '\n';

	char current() throws IOException;

	char next() throws IOException;

	char peek() throws IOException;

	int line();

	int position();
}
