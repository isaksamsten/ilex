package runtime.plog;

import java.util.HashMap;
import java.util.Map;

public class PClass extends PObject {
	private String name;
	private Map<String, PFunction> functions = new HashMap<String, PFunction>();
	
	public PClass(String name) {
		super(null);
		this.name = name;
	}

	public String name() {
		return name;
	}

	public PFunction func(String str) {
		PFunction func = functions.get(str);
		if (func != null) {
			return func;
		} else {
			throw new RuntimeException(str + " is not a function of "
					+ toString());
		}
	}

	public void func(PFunction f) {
		if (!functions.containsKey(f.name())) {
			functions.put(f.name(), f);
		} else {
			throw new RuntimeException("Can't redefine " + f.name() + " of "
					+ toString());
		}
	}
}
