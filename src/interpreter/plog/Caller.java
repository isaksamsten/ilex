package interpreter.plog;

import parser.tree.plog.AttrNode;
import parser.tree.plog.CallNode;
import parser.tree.plog.VarNode;
import runtime.plog.PModule;
import runtime.plog.PObject;

public class Caller extends Interpreter {

	private PObject object;
	private PObject caller;

	public Caller(PModule module, PObject caller) {
		super(module);
		object = caller;
		this.caller = caller;
	}

	@Override
	public Object visitCall(CallNode node) {
		object = object.dict(((VarNode) node.name()).var());
		if (object.respondTo("__call__")) {
			if (node.argument() != null) {
				Interpreter inter = new Interpreter(module());
				PObject[] args = (PObject[]) inter.visit(node.argument());
				
				object = object.invoke(caller, "__call__", args);
			} else {
				object = object.invoke(caller, "__call__");
			}
			
			caller = object;
		} else {
			throw new IntepreterException(object.toString()
					+ " is not callable.");
		}
		return object;
	}

	@Override
	public Object visitAttr(AttrNode node) {
		for (int n = 1; n < node.elements().size(); n++) {
			object = (PObject) visit(node.elements().get(n));
		}
		return object;
	}
}
