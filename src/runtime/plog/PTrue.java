package runtime.plog;

public class PTrue extends PObject {

	public static final PTrue instance = new PTrue();

	private PTrue() {
		super(Builtin.ptrue);
	}
}
