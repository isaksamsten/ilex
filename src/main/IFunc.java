package main;

public class IFunc extends IObject {

	public IObject invoke(Object... args) {
		return INil.get();
	}
}
