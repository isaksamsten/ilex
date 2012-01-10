package runtime.plog;

public class PNumber extends PObject {

	public PNumber(Number value) {
		super(Builtin.number);
		this.value = value;
	}

	private Number value;

	public Number value() {
		return value;
	}

	public double doubleValue() {
		return value.doubleValue();
	}

	public float floatValue() {
		return value.floatValue();
	}

	public int intValue() {
		return value.intValue();
	}

	public long longValue() {
		return value.longValue();
	}

	public short shortValue() {
		return value.shortValue();
	}

	public boolean isFloat() {
		return value instanceof Float || value instanceof Double;
	}

	public PObject add(PNumber other) {
		if (isFloat() || other.isFloat())
			return new PNumber(value.doubleValue()
					+ other.value().doubleValue());
		else
			return new PNumber(value.longValue() + other.value().longValue());
	}

	public PObject sub(PNumber other) {
		if (isFloat() || other.isFloat())
			return new PNumber(value.doubleValue()
					- other.value().doubleValue());
		else
			return new PNumber(value.longValue() - other.value().longValue());
	}

	public PObject mul(PNumber other) {
		if (isFloat() || other.isFloat())
			return new PNumber(value.doubleValue()
					* other.value().doubleValue());
		else
			return new PNumber(value.longValue() * other.value().longValue());
	}

	public PObject div(PNumber other) {
		if (isFloat() || other.isFloat())
			return new PNumber(value.doubleValue()
					/ other.value().doubleValue());
		else
			return new PNumber(value.longValue() / other.value().longValue());
	}

	public PObject mod(PNumber other) {
		if (isFloat() || other.isFloat())
			return new PNumber(value.doubleValue()
					% other.value().doubleValue());
		else
			return new PNumber(value.longValue() % other.value().longValue());
	}

	@Override
	public int compareTo(PObject o) {
		PNumber n = (PNumber) o;
		return (int) (value.doubleValue() - n.value.doubleValue());
	}

	@Override
	public String toString() {
		return value.toString();
	}

}
