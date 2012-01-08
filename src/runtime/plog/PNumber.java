package runtime.plog;

public class PNumber extends PClass {

	public PNumber() {
		super("Number");

		func(new PFunction("add", 1) {

			@Override
			PObject execute(PObject self, PObject... args) {
				PNumberObject num = (PNumberObject) self;
				return num.add((PNumberObject) args[0]);
			}
		});
	}
}
