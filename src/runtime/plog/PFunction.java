package runtime.plog;

public abstract class PFunction extends PObject {
	private int arity;

	public PFunction(String name, int arity) {
		super(name, Builtin.pfunc);
		this.arity = arity;
	}

	
	public PObject invoke(PObject self, PObject... args) {
		if (args.length != arity) {
			throw new RuntimeException(name() + "() takes exactly " + arity
					+ " arguments (" + args.length + " given)");
		}
		return execute(self, args);
	}

	protected abstract PObject execute(PObject self, PObject... args);
}
