package main;

import java.lang.reflect.Method;

public class IMethodFunc extends IFunc {

	private Object instance;
	private Method method;

	public IMethodFunc(Object instance, Method method) {
		this.instance = instance;
		this.method = method;
	}

	@Override
	public IObject invoke(Object... args) {
		Object o;
		try {
			o = method.invoke(instance, args);
			if (o instanceof IObject)
				return (IObject) o;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return INil.get();
	}
}
