package runtime.plog;

public class PBoundFunction extends PObject {
	private PFunction function;
	private PObject binder;

	public PBoundFunction(PFunction func, PObject binder) {
		this.function = func;
		this.binder = binder;
	}

	@Override
	public PObject invoke(PObject self, String func, PObject... args) {
		return function.invoke(binder, func, args);
	}

}
