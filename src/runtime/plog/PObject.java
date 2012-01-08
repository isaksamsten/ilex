package runtime.plog;

import java.util.HashMap;
import java.util.Map;

public class PObject {

	private PClass base;
	private Map<String, PObject> dict = new HashMap<String, PObject>();

	public PObject(PClass base) {
		this.base = base;
	}

	public PClass base() {
		return this.base;
	}

	public PObject dict(String name) {
		PObject item = dict.get(name);
		if (item != null) {
			return item;
		} else {
			throw new RuntimeException(name + " is in dict of " + toString());
		}
	}

	public void dict(String name, PObject value) {
		this.dict.put(name, value);
	}

	public PObject invoke(String func, PObject... args) {
		PFunction function = base().func(func);
		return function.execute(this, args);
	}
}
