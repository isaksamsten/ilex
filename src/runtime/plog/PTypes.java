package runtime.plog;

public class PTypes {

	public static PObject value(boolean b) {
		return b ? PTrue.instance : PFalse.instance;
	}

	public static PObject value(String str) {
		return new PString(str);
	}
}
