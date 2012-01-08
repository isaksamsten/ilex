package runtime.plog;

public abstract class PFunction extends PClass {
	private int arity;

	public PFunction(String name, int arity) {
		super(name);
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
