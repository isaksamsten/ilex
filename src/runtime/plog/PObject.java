package runtime.plog;

import java.util.HashMap;
import java.util.Map;

public class PObject implements Comparable<PObject> {

	private PObject prototype;
	private Map<String, PObject> dict = new HashMap<String, PObject>();

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

	public boolean respondTo(String name) {
		return dict.containsKey(name)
				|| (prototype != null && prototype.respondTo(name));
	}

	public PObject dict(String name, PObject value) {
		this.dict.put(name, value);
		return value;
	}

	public PObject invoke(PObject self, String func, PObject... args) {
		PFunction function = (PFunction) dict(func);
		PObject value = function.invoke(this, self, args);
		return value;
	}

	public PObject dict(String str) {
		PObject proto = this;
		PObject itm = null;
		while (proto != null) {
			itm = proto.dict.get(str);
			if (itm != null) {
				break;
			} else {
				proto = proto.prototype;
			}
		}

		return itm;
	}

	public void func(PFunction f) {
		dict.put(f.name().toString(), f);
	}

	public PObject name() {
		return dict("__name__");
	}

	public boolean isTrue() {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		return invoke(this, "eq", (PObject) obj).isTrue();
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
