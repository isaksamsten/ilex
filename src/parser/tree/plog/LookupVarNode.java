package parser.tree.plog;

import interpreter.plog.Visitor;

public class LookupVarNode extends VarNode{

	public LookupVarNode(int line, String var) {
		super(line, var);
	}
	
	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitLookupVar(this);
	}

}
