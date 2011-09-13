package main;

import java.util.HashMap;
import java.util.Map;

public class God {
	private static int ID = 0;

	private static final synchronized int incrementId() {
		return ID++;
	}

	protected God parent = null;
	protected Map<String, Object> properties = new HashMap<String, Object>();
	protected Map<String, Function> methods = new HashMap<String, Function>();
	private int id;

	public God(int id) {
		this.id = id;
	}

	public God() {
		this(incrementId());
	}

	public void initialize(Map<String, Object> args) {
		for (Map.Entry<String, Object> kv : args.entrySet()) {
			properties.put(kv.getKey(), kv.getValue());
		}
	}

	protected int id() {
		return id;
	}

	public String name() {
		return getClass().getSimpleName();
	}

	public God parent() {
		return parent;
	}

	public boolean isNil() {
		return false;
	}

	public boolean isTrue() {
		return !isNil();
	}

	public boolean isFalse() {
		return !isTrue();
	}

	public Map<String, Object> properties() {
		return properties;
	}

	@SuppressWarnings("unchecked")
	public <T> T prop(String name) {
		return (T) properties.get(name);
	}

	public void prop(String name, Object value) {
		properties.put(name, value);
	}

	public Function function(String name) {
		return methods.get(name);
	}

	public void function(String name, Function function) {
		methods.put(name, function);
	}

	public God invoke(String function, Object... args) {
		God current = this;
		while (current != null) {
			Function func = function(function);
			if (func != null) {
				return func.invoke(args);
			}
		}

		return this;
	}

	public String string() {
		return String.format("%s#%d [%s]", name(), id, properties);
	}

	@Override
	public String toString() {
		return string();
	}

}
