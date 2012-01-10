package runtime.plog;

public final class Builtin {
	public static final PObject object = new PObject("Object");

	public static final PObject pbool = new PObject("Boolean", object);
	public static final PObject pfalse = new PObject("False", pbool);
	public static final PObject ptrue = new PObject("True", pbool);

	public static final PObject number = new PObject("Number", object);
	public static final PObject string = new PObject("String", object);
	public static final PObject pfunc = new PObject("Function", object);

	public static final PObject system = new PObject("System", object);

	static {

		object.func(new PFunction("clone", 0) {

			@Override
			protected PObject execute(PObject self, PObject... args) {
				return new PObject(self);
			}
		});

		pbool.func(new PFunction("clone", 0) {

			@Override
			protected PObject execute(PObject self, PObject... args) {
				return self;
			}
		});

		number.func(new PFunction("add", 1) {

			@Override
			protected PObject execute(PObject self, PObject... args) {
				PNumber num = (PNumber) self;
				return num.add((PNumber) args[0]);
			}
		});

		number.func(new PFunction("sub", 1) {

			@Override
			protected PObject execute(PObject self, PObject... args) {
				PNumber num = (PNumber) self;
				return num.sub((PNumber) args[0]);
			}
		});

		number.func(new PFunction("mul", 1) {

			@Override
			protected PObject execute(PObject self, PObject... args) {
				PNumber num = (PNumber) self;
				return num.mul((PNumber) args[0]);
			}
		});

		number.func(new PFunction("div", 1) {

			@Override
			protected PObject execute(PObject self, PObject... args) {
				PNumber num = (PNumber) self;
				return num.div((PNumber) args[0]);
			}
		});

		number.func(new PFunction("mod", 1) {

			@Override
			protected PObject execute(PObject self, PObject... args) {
				PNumber num = (PNumber) self;
				return num.mod((PNumber) args[0]);
			}
		});

		string.func(new PFunction("len", 0) {

			@Override
			protected PObject execute(PObject self, PObject... args) {
				return new PNumber(((PString) self).value().length());
			}
		});

		string.func(new PFunction("clone", 0) {

			@Override
			protected PObject execute(PObject self, PObject... args) {
				return new PString("");
			}
		});

		pfalse.func(new PFunction("true", 0) {

			@Override
			protected PObject execute(PObject self, PObject... args) {
				return PFalse.instance;
			}
		});

		object.func(new PFunction("true", 0) {

			@Override
			protected PObject execute(PObject self, PObject... args) {
				return PTrue.instance;
			}
		});

		object.func(new PFunction("cmp", 1) {

			@Override
			protected PObject execute(PObject self, PObject... args) {
				return new PNumber(self.compareTo(args[0]));
			}
		});

		object.func(new PFunction("str", 0) {

			@Override
			protected PObject execute(PObject self, PObject... args) {
				return new PString(self.toString());
			}
		});

		object.func(new PFunction("lt", 1) {

			@Override
			protected PObject execute(PObject self, PObject... args) {
				PNumber cmp = (PNumber) self.invoke("cmp", args);
				if (cmp.value().intValue() < 0)
					return PTrue.instance;
				else
					return PFalse.instance;
			}
		});

		object.func(new PFunction("gt", 1) {

			@Override
			protected PObject execute(PObject self, PObject... args) {
				PNumber cmp = (PNumber) self.invoke("cmp", args);
				if (cmp.value().intValue() > 0)
					return PTrue.instance;
				else
					return PFalse.instance;
			}
		});

		object.func(new PFunction("eq", 1) {

			@Override
			protected PObject execute(PObject self, PObject... args) {
				PNumber cmp = (PNumber) self.invoke("cmp", args);
				if (cmp.value().intValue() == 0)
					return PTrue.instance;
				else
					return PFalse.instance;
			}
		});

		object.func(new PFunction("lte", 1) {

			@Override
			protected PObject execute(PObject self, PObject... args) {
				PNumber cmp = (PNumber) self.invoke("cmp", args);
				if (cmp.value().intValue() <= 0)
					return PTrue.instance;
				else
					return PFalse.instance;
			}
		});

		object.func(new PFunction("gte", 1) {

			@Override
			protected PObject execute(PObject self, PObject... args) {
				PNumber cmp = (PNumber) self.invoke("cmp", args);
				if (cmp.value().intValue() >= 0)
					return PTrue.instance;
				else
					return PFalse.instance;
			}
		});

		system.func(new PFunction("exit", 0) {

			@Override
			protected PObject execute(PObject self, PObject... args) {
				System.exit(0);
				return null;
			}
		});

	}

}
