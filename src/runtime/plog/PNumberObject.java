package runtime.plog;

public class PNumberObject extends PObject {

	public PNumberObject(Number value) {
		super(new PNumber());
		this.value = value;
	}

	private Number value;

	public Number value() {
		return value;
	}

	public PObject add(PNumberObject other) {
		return new PNumberObject(value.intValue() + other.value().intValue());
	}

}
