package runtime.plog;

import java.util.HashMap;
import java.util.Map;

public class PObject implements Comparable<PObject> {

	private PObject prototype;
	private Map<String, PObject> dict = new HashMap<String, PObject>();
	private Map<String, PFunction> functions = new HashMap<String, PFunction>();

	public PObject(String name, PObject prototype) {
		this(prototype);
		dict("__name__", new PString(name));
	}

	public PObject(String name) {
		this(name, null);
	}

	public PObject(PObject base) {
		this.prototype = base;
	}

	public PObject() {
	}

	public PObject prototype() {
		return this.prototype;
	}

	public void prototype(PObject c) {
		this.prototype = c;
	}

	public PObject dict(String name) {
		PObject item = dict.get(name);
		if (item != null) {
			return item;
		} else {
			throw new RuntimeException(name + " is not in dict of");
		}
	}

	public boolean respondTo(String name) {
		return functions.containsKey(name)
				|| (prototype != null && prototype.respondTo(name));
	}

	public void dict(String name, PObject value) {
		this.dict.put(name, value);
	}

	public PObject invoke(String func, PObject... args) {
		PFunction function = func(func);
		return function.invoke(this, args);
	}

	public PFunction func(String str) {
		PFunction func = functions.get(str);
		if (func != null) {
			return func;
		} else if (prototype != null && (func = prototype.func(str)) != null) {
			return func;
		} else {
			throw new RuntimeException(str + " is not a function of "
					+ toString());
		}
	}

	public void func(PFunction f) {
		functions.put(f.name().toString(), f);
	}

	public PObject name() {
		return dict("__name__");
	}

	public boolean isTrue() {
		return true;
	}

	@Override
	public int compareTo(PObject o) {
		return hashCode() - o.hashCode();
	}

	@Override
	public String toString() {
		try {
			return ((PString) dict("__name__")).toString();
		} catch (Exception e) {
			return super.toString();
		}
	}
}
