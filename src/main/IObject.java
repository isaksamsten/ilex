package main;

import java.util.HashMap;
import java.util.Map;

public class IObject {
	private static int ID = 0;

	private static final synchronized int incrementId() {
		return ID++;
	}

	protected IObject parent = null;
	protected Map<String, Object> properties = new HashMap<String, Object>();
	protected Map<String, IFunc> methods = new HashMap<String, IFunc>();
	private int id;

	public IObject(int id) {
		this.id = id;
	}

	public IObject() {
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

	public IObject parent() {
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

	public IFunc function(String name) {
		return methods.get(name);
	}

	public void function(String name, IFunc function) {
		methods.put(name, function);
	}

	public IObject invoke(String function, Object... args) {
		IObject current = this;
		while (current != null) {
			IFunc func = function(function);
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
