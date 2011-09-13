package main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodFunction extends Function {

	private Object instance;
	private Method method;

	public MethodFunction(Object instance, Method method) {
		this.instance = instance;
		this.method = method;
	}

	@Override
	public God invoke(Object... args) {
		Object o;
		try {
			o = method.invoke(instance, args);
			if (o instanceof God)
				return (God) o;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Nil.get();
	}
}
