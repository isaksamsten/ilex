package runtime.plog;

import java.util.Arrays;

public final class Builtin {
	public static final PObject proto = new PObject("Proto");
	public static final PObject object = new PObject("Object", proto);

	public static final PObject pbool = new PObject("Boolean", object);
	public static final PObject pfalse = new PObject("False", pbool);
	public static final PObject ptrue = new PObject("True", pbool);

	public static final PObject number = new PObject("Number", object);
	public static final PObject string = new PObject("String", object);
	public static final PObject pfunc = new PObject("Function", object);

	public static final PObject system = new PObject("System", object);

	public static final PObject test = new PFunction("test", 0) {

		@Override
		protected PObject execute(PObject self, PObject invoker,
				PObject... args) {
			System.out.println("Test");
			return new PObject();
		}
	};
	public static final PObject pmodule = new PObject("Module", object);
	public static final PObject parray = new PObject("Array", object);

	static {

		proto.func(new PFunction("clone", 0) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				return new PObject(self);
			}
		});

		pbool.func(new PFunction("clone", 0) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				return self;
			}
		});

		number.func(new PFunction("add", 1) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				PNumber num = (PNumber) self;
				return num.add((PNumber) args[0]);
			}
		});

		number.func(new PFunction("sub", 1) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				PNumber num = (PNumber) self;
				return num.sub((PNumber) args[0]);
			}
		});

		number.func(new PFunction("mul", 1) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				PNumber num = (PNumber) self;
				return num.mul((PNumber) args[0]);
			}
		});

		number.func(new PFunction("div", 1) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				PNumber num = (PNumber) self;
				return num.div((PNumber) args[0]);
			}
		});

		number.func(new PFunction("mod", 1) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				PNumber num = (PNumber) self;
				return num.mod((PNumber) args[0]);
			}
		});

		string.func(new PFunction("len", 0) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				return new PNumber(((PString) self).value().length());
			}
		});

		string.func(new PFunction("clone", 0) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				return new PString("");
			}
		});

		string.func(new PFunction("cmp", 1) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				PObject other = args[0].invoke(args[0], "str");
				return new PNumber(((PString) self).value().compareTo(
						((PString) other).value()));
			}
		});

		pfalse.func(new PFunction("true", 0) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				return PFalse.instance;
			}
		});

		object.func(new PFunction("true", 0) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				return PTrue.instance;
			}
		});

		object.func(new PFunction("cmp", 1) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				return new PNumber(self.compareTo(args[0]));
			}
		});

		object.func(new PFunction("str", 0) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				return new PString(self.toString());
			}
		});

		object.func(new PFunction("lt", 1) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				PNumber cmp = (PNumber) self.invoke(self, "cmp", args);
				if (cmp.value().intValue() < 0)
					return PTrue.instance;
				else
					return PFalse.instance;
			}
		});

		object.func(new PFunction("gt", 1) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				PNumber cmp = (PNumber) self.invoke(self, "cmp", args);
				if (cmp.value().intValue() > 0)
					return PTrue.instance;
				else
					return PFalse.instance;
			}
		});

		object.func(new PFunction("eq", 1) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				PNumber cmp = (PNumber) self.invoke(self, "cmp", args);
				if (cmp.value().intValue() == 0)
					return PTrue.instance;
				else
					return PFalse.instance;
			}
		});

		object.func(new PFunction("lte", 1) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				PNumber cmp = (PNumber) self.invoke(self, "cmp", args);
				if (cmp.value().intValue() <= 0)
					return PTrue.instance;
				else
					return PFalse.instance;
			}
		});

		object.func(new PFunction("gte", 1) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				PNumber cmp = (PNumber) self.invoke(self, "cmp", args);
				if (cmp.value().intValue() >= 0)
					return PTrue.instance;
				else
					return PFalse.instance;
			}
		});

		system.func(new PFunction("exit", 1) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				System.exit(((PNumber) args[0]).intValue());
				return null;
			}
		});

		pmodule.func(new PFunction("str", 0) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				PObject file = self.dict("__file__");
				return new PString("Module '" + file + "'");
			}
		});

		pfunc.func(new PFunction("__call__", -1) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				PFunction function = (PFunction) self;
				return function.invoke(invoker, self, args);
			}
		});

		parray.func(new PFunction("clone", -1) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				PArray array = new PArray(args);
				return array;
			}
		});

		parray.func(new PFunction("contains", 1) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				PArray array = (PArray) self;
				return PTypes.value(array.contains(args[0]));
			}
		});

		parray.func(new PFunction("get", 1) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				PArray array = (PArray) self;
				return array.get(((PNumber) args[0]).intValue());
			}
		});

		parray.func(new PFunction("size", 0) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				PArray array = (PArray) self;
				return new PNumber(array.size());
			}
		});

		parray.func(new PFunction("add", 1) {

			@Override
			protected PObject execute(PObject self, PObject invoker,
					PObject... args) {
				PArray array = (PArray) self;
				array.addAll(Arrays.asList(args));
				return array;
			}
		});

	}

}
