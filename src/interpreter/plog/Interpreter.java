package interpreter.plog;

import interpreter.Stack;
import interpreter.TableEntry;
import interpreter.TableKey;

import java.util.Scanner;

import parser.tree.plog.AssignNode;
import parser.tree.plog.CallNode;
import parser.tree.plog.CompNode;
import parser.tree.plog.ExprNode;
import parser.tree.plog.IfNode;
import parser.tree.plog.LookupVarNode;
import parser.tree.plog.NumNode;
import parser.tree.plog.Operator;
import parser.tree.plog.ReadNode;
import parser.tree.plog.StmtListNode;
import parser.tree.plog.StringNode;
import parser.tree.plog.TermNode;
import parser.tree.plog.VarNode;
import parser.tree.plog.WhileNode;
import parser.tree.plog.WriteNode;
import runtime.plog.PNumber;
import runtime.plog.PObject;

public class Interpreter extends Visitor {

	private static final Stack stack = Stack.getInstance();
	private static final Scanner in = new Scanner(System.in);

	@Override
	public Object visitComp(CompNode n) {
		PObject a = (PObject) visit(n.lhs());
		PObject b = (PObject) visit(n.rhs());

		return a.invoke(n.operator().function(), b);
	}

	@Override
	public Object visitExpr(ExprNode n) {
		PObject a = (PObject) visit(n.lhs());
		PObject b = (PObject) visit(n.rhs());
		if (b == null) {
			return a;
		}
		return a.invoke(n.operator().function(), b);
	}

	@Override
	public Object visitAssign(AssignNode n) {
		String var = (String) visit(n.var());
		Object value = visit(n.expr());

		TableEntry entry = stack.lookup(var);
		if (entry == null) {
			entry = stack.enter(var);
		}
		entry.putAttribute(TableKey.CONSTANT, value);

		return null;
	}

	@Override
	public Object visitNum(NumNode n) {
		return n.number();
	}

	@Override
	public Object visitVar(VarNode n) {
		return n.var();
	}

	@Override
	public Object visitStmtList(StmtListNode n) {
		visit(n.statements());
		return null;
	}

	@Override
	public Object visitWhile(WhileNode n) {
		PObject compared = (PObject) visit(n.compare());
		stack.push();
		while (compared.isTrue()) {
			visit(n.statementList());
			compared = (PObject) visit(n.compare());
		}
		stack.pop();
		return null;
	}

	@Override
	public Object visitRead(ReadNode n) {
		String var = (String) visit(n.var());
		int value = in.nextInt();

		stack.enter(var).putAttribute(TableKey.CONSTANT, new PNumber(value));
		return value;
	}

	@Override
	public Object visitWrite(WriteNode n) {
		for (ExprNode expr : n.expr()) {
			PObject value = (PObject) visit(expr);
			System.out.print(value.invoke("str"));
		}

		return null;
	}

	@Override
	public Object visitIf(IfNode n) {
		PObject compare = (PObject) visit(n.compare());
		if (compare.isTrue()) {
			visit(n.trueStmt());
		} else {
			visit(n.falseStmt());
		}

		return null;
	}

	@Override
	public Object visitLookupVar(LookupVarNode n) {
		TableEntry entry = stack.lookup(n.var());
		if (entry != null)
			return entry.getAttribute(TableKey.CONSTANT);
		else
			throw new IntepreterException("Var '" + n.var()
					+ "' not initialized");
	}

	@Override
	public Object visitString(StringNode n) {
		return n.string();
	}

	@Override
	public Object visitCall(CallNode callNode) {
		PObject object = (PObject) visit(callNode.names().get(0));
		return object.invoke(((VarNode) callNode.names().get(1)).var());
	}
}
