package runtime.plog;

import java.util.HashMap;
import java.util.Map;

public class PObject implements Comparable<PObject> {

	private PObject prototype;
	private PObject bound;

	private Map<String, PObject> dict = new HashMap<String, PObject>();

	public PObject(String name, PObject prototype) {
		this(prototype);
		dict("name", new PString(name));
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

	public boolean respondTo(String name) {
		return dict.containsKey(name)
				|| (prototype != null && prototype.respondTo(name));
	}

	public PObject dict(String name, PObject value) {
		this.dict.put(name, value);
		return value;
	}

	public void bind(PObject to) {
		bound = to;
	}

	public PObject binder() {
		return bound;
	}

	public boolean isBound() {
		return bound != null;
	}

	public PObject invoke(PObject self, String func, PObject... args) {
		PFunction function = (PFunction) dict(func);
		this.bind(self);
		PObject value = function.invoke(this, args);
		this.bind(null);
		return value;
	}

	public PObject dict(String str) {
		PObject proto = this;
		PObject func = null;
		while (proto != null) {
			func = proto.dict.get(str);
			if (func != null) {
				break;
			} else {
				proto = proto.prototype;
			}
		}

		return func;
	}

	public void func(PFunction f) {
		f.bind(this);
		dict.put(f.name().toString(), f);
	}

	public PObject name() {
		return dict("name");
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
			return ((PString) dict("name")).toString();
		} catch (Exception e) {
			return super.toString();
		}
	}
}
