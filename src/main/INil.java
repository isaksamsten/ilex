package main;

public class INil extends IObject {

	private static final INil instance = new INil();

	private INil() {
	}

	@Override
	public boolean isNil() {
		return true;
	}

	public static IObject get() {
		return instance;
	}
}
