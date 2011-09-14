package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import message.MessageHandler;

public class BufferedSource implements Source {

	private BufferedReader in;
	private String line;

	private int lineNum = 0;
	private int current = -1;

	public BufferedSource(File file) throws IOException {
		in = new BufferedReader(new FileReader(file));
		readLine();
		next();
	}

	@Override
	public char next() throws IOException {
		current++;
		return current();
	}

	@Override
	public char peek() throws IOException {
		char c = current();
		if (c == EOF) {
			return c;
		}
		int next = current + 1;
		return next < line.length() ? line.charAt(next) : EOL;
	}

	@Override
	public int line() {
		return lineNum;
	}

	@Override
	public int position() {
		return current;
	}

	@Override
	public char current() throws IOException {
		if (line == null) {
			return EOF;
		} else if (current == -1 || current == line.length()) {
			return EOL;
		} else if (current > line.length()) {
			readLine();
			return next();
		} else {
			return line.charAt(current);
		}
	}

	private void readLine() throws IOException {
		line = in.readLine();
		current = -1;
		if (line != null) {
			lineNum++;
			MessageHandler.getInstance().line(lineNum, line);
		}

	}
}