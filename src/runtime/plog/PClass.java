package runtime.plog;

import java.util.HashMap;
import java.util.Map;

public class PClass extends PObject {
	private String name;
	private Map<String, PFunction> functions = new HashMap<String, PFunction>();
	private PClass base;

	public PClass(String name, PClass base) {
		super(Builtin.pclass);
		this.name = name;
		this.base = base;
	}

	public PClass(String name) {
		this(name, null);
	}

	public String name() {
		return name;
	}

	public PFunction func(String str) {
		PFunction func = functions.get(str);
		if (func != null) {
			return func;
		} else if (base != null && (func = base.func(str)) != null) {
			return func;
		} else {
			throw new RuntimeException(str + " is not a function of "
					+ toString());
		}
	}

	public void func(PFunction f) {
		functions.put(f.name(), f);
	}
	
	@Override
	public String toString() {
		return "Class: " + name;
	}
}
