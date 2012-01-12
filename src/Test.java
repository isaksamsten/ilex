import runtime.plog.Builtin;
import runtime.plog.PNumber;
import runtime.plog.PObject;
import runtime.plog.PString;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PObject obj = new PString("hello world");
		PObject res = obj.dict("len");
		
		System.out.println(res.invoke(obj, "__call__").dict("gt").invoke(new PNumber(2), "__call__", new PNumber(3)));
	}

}
