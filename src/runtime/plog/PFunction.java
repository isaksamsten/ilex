package runtime.plog;

import java.util.Arrays;

public abstract class PFunction extends PObject {
	private int arity;

	public PFunction(String name, int arity) {
		super(name, Builtin.pfunc);
		this.arity = arity;
	}

	public PObject invoke(PObject self, PObject invoker, PObject... args) {
		if (args.length != arity && arity != -1) {
			throw new RuntimeException(name() + "() takes exactly " + arity
					+ " arguments (" + args.length + " given)");
		}
		return execute(self, invoker, args);
	}

	protected abstract PObject execute(PObject self, PObject invoker, PObject... args);

}
