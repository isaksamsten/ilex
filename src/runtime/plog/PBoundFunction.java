package runtime.plog;

public class PBoundFunction extends PObject {
	private PFunction function;
	
	public PBoundFunction(PFunction func, PObject binder) {
		this.function = func;
		bind(binder);
	}

	@Override
	public void bind(PObject to) {
		// TODO Auto-generated method stub
		super.bind(to);
	}

	@Override
	public PObject invoke(PObject self, String func, PObject... args) {
		return super.invoke(self, func, args);
	}
	
	
	
	
	
}
