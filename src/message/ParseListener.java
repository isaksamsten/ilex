package message;

import java.util.EventListener;

import token.Token;

public interface ParseListener extends EventListener {
	void summary(long took, int errorCount);

	void token(Token token);

	void error(int line, int pos, String text, String error, boolean b);
}
