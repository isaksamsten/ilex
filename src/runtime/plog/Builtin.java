package runtime.plog;

public final class Builtin {
	public static final PClass pclass = new PClass("Class");
	public static final PClass object = new PClass("Object");
	public static final PClass number = new PClass("Number", object);
	public static final PClass bool = new PClass("Boolean", object);
	public static final PClass string = new PClass("String", object);

	static {
		
		pclass.func(new PFunction("new", 0) {
			
			@Override
			PObject execute(PObject self, PObject... args) {
				return new PObject((PClass) self);
			}
		});
		
		number.func(new PFunction("add", 1) {

			@Override
			PObject execute(PObject self, PObject... args) {
				PNumber num = (PNumber) self;
				return num.add((PNumber) args[0]);
			}
		});

		number.func(new PFunction("sub", 1) {

			@Override
			PObject execute(PObject self, PObject... args) {
				PNumber num = (PNumber) self;
				return num.sub((PNumber) args[0]);
			}
		});

		number.func(new PFunction("mul", 1) {

			@Override
			PObject execute(PObject self, PObject... args) {
				PNumber num = (PNumber) self;
				return num.mul((PNumber) args[0]);
			}
		});

		number.func(new PFunction("div", 1) {

			@Override
			PObject execute(PObject self, PObject... args) {
				PNumber num = (PNumber) self;
				return num.div((PNumber) args[0]);
			}
		});

		number.func(new PFunction("mod", 1) {

			@Override
			PObject execute(PObject self, PObject... args) {
				PNumber num = (PNumber) self;
				return num.mod((PNumber) args[0]);
			}
		});
		
		string.func(new PFunction("len",0) {
			
			@Override
			PObject execute(PObject self, PObject... args) {
				return new PNumber(((PString) self).value().length());
			}
		});

		object.func(new PFunction("cmp", 1) {

			@Override
			PObject execute(PObject self, PObject... args) {
				return new PNumber(self.compareTo(args[0]));
			}
		});
		
		object.func(new PFunction("str",0) {
			
			@Override
			PObject execute(PObject self, PObject... args) {
				return new PString(self.toString());
			}
		});

		object.func(new PFunction("lt", 1) {

			@Override
			PObject execute(PObject self, PObject... args) {
				PNumber cmp = (PNumber) self.invoke("cmp", args);
				if (cmp.value().intValue() < 0)
					return PTrue.instance;
				else
					return PFalse.instance;
			}
		});

		object.func(new PFunction("gt", 1) {

			@Override
			PObject execute(PObject self, PObject... args) {
				PNumber cmp = (PNumber) self.invoke("cmp", args);
				if (cmp.value().intValue() > 0)
					return PTrue.instance;
				else
					return PFalse.instance;
			}
		});

		object.func(new PFunction("eq", 1) {

			@Override
			PObject execute(PObject self, PObject... args) {
				PNumber cmp = (PNumber) self.invoke("cmp", args);
				if (cmp.value().intValue() == 0)
					return PTrue.instance;
				else
					return PFalse.instance;
			}
		});

		object.func(new PFunction("lte", 1) {

			@Override
			PObject execute(PObject self, PObject... args) {
				PNumber cmp = (PNumber) self.invoke("cmp", args);
				if (cmp.value().intValue() <= 0)
					return PTrue.instance;
				else
					return PFalse.instance;
			}
		});

		object.func(new PFunction("gte", 1) {

			@Override
			PObject execute(PObject self, PObject... args) {
				PNumber cmp = (PNumber) self.invoke("cmp", args);
				if (cmp.value().intValue() >= 0)
					return PTrue.instance;
				else
					return PFalse.instance;
			}
		});

	}

}
