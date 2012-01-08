package runtime.plog;

import java.util.HashMap;
import java.util.Map;

public class PObject implements Comparable<PObject> {

	private PClass base;
	private Map<String, Object> dict = new HashMap<String, Object>();

	public PObject(PClass base) {
		this.base = base;
	}

	public PClass base() {
		return this.base;
	}

	public Object dict(String name) {
		Object item = dict.get(name);
		if (item != null) {
			return item;
		} else {
			throw new RuntimeException(name + " is not in dict of "
					+ toString());
		}
	}

	public void dict(String name, Object value) {
		this.dict.put(name, value);
	}

	public PObject invoke(String func, PObject... args) {
		PFunction function = base().func(func);
		return function.execute(this, args);
	}

	public boolean isTrue() {
		return true;
	}

	@Override
	public int compareTo(PObject o) {
		return hashCode() - o.hashCode();
	}
}
