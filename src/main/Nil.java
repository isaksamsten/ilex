package main;

public class Nil extends God {

	private static final Nil instance = new Nil();

	private Nil() {
	}

	@Override
	public boolean isNil() {
		return true;
	}

	public static God get() {
		return instance;
	}
}
