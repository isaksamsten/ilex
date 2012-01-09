package runtime.plog;

public abstract class PFunction extends PObject {
	private int arity;

	public PFunction(String name, int arity) {
		super(name, Builtin.pfunc);
		this.arity = arity;
	}

	public PObject invoke(PObject self, PObject... args) {
		if (args.length != arity) {
			throw new RuntimeException();
		}
		return execute(self, args);
	}

	abstract PObject execute(PObject self, PObject... args);
}
