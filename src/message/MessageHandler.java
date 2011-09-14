package message;

import java.io.IOException;

import javax.swing.event.EventListenerList;

import token.ErrorCode;
import token.Token;

public class MessageHandler {
	private static final int MAX_ERRORS = 10;
	private static MessageHandler instance;

	public static MessageHandler getInstance() {
		if (instance == null) {
			instance = new MessageHandler();
		}
		return instance;
	}

	private EventListenerList listeners = new EventListenerList();
	private int errors = 0;

	private MessageHandler() {

	}

	public void summary(long took) {
		for (ParseListener l : listeners.getListeners(ParseListener.class)) {
			l.summary(took, errors);
		}
	}

	public void token(Token token) {
		for (ParseListener l : listeners.getListeners(ParseListener.class)) {
			l.token(token);
		}
	}

	public void error(Token token, ErrorCode code) {
		errors++;
		for (ParseListener l : listeners.getListeners(ParseListener.class)) {
			l.error(token.line(), token.position(), token.text(), code
					.description(), errors > MAX_ERRORS);
		}
	}

	public void line(int num, String line) {
		for (SourceListener l : listeners.getListeners(SourceListener.class)) {
			l.line(num, line);
		}
	}

	public void addSourceListener(SourceListener l) {
		listeners.add(SourceListener.class, l);
	}

	public void addParseListener(ParseListener l) {
		listeners.add(ParseListener.class, l);
	}

	public void fatal(ErrorCode errorCode, IOException e) {
		for (ParseListener l : listeners.getListeners(ParseListener.class)) {
			l.error(0, 0, "", "FATAL: " + errorCode.description(), true);
		}
	}

	public void fatal(ErrorCode code) {
		fatal(code, null);
	}
}