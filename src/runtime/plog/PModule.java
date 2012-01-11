package runtime.plog;

public class PModule extends PObject {

	public PModule(String name) {
		super(Builtin.pmodule);

		dict("__file__", new PString(name));
	}
	
	public PModule() {
		dict("__file__", new PString(""));
	}
	
	@Override
	public String toString() {
		return dict("__file__").toString();
	}
}
