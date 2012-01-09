package runtime.plog;

public class PFalse extends PObject {

	public static final PFalse instance = new PFalse();

	public PFalse() {
		super(Builtin.pfalse);
	}

	@Override
	public boolean isTrue() {
		return false;
	}

}
