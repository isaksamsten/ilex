package runtime.plog;

public class PString extends PObject {

	private String value;

	public PString(String value) {
		super(Builtin.string);
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	@Override
	public int compareTo(PObject o) {
		return value.compareTo(((PString) o).value());
	};

	@Override
	public String toString() {
		return value;
	}

}
