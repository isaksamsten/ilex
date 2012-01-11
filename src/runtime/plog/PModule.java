package runtime.plog;

public class PModule extends PObject {

	public PModule(String name) {
		super(Builtin.pmodule);

		dict("__file__", new PString(name));
	}
}
