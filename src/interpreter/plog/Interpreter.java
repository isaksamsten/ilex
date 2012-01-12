package interpreter.plog;

import interpreter.PObjectStack;
import interpreter.Stack;
import interpreter.TableEntry;
import interpreter.TableKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import parser.tree.Node;
import parser.tree.plog.AssignNode;
import parser.tree.plog.AttrNode;
import parser.tree.plog.CallNode;
import parser.tree.plog.CompNode;
import parser.tree.plog.ExprListNode;
import parser.tree.plog.ExprNode;
import parser.tree.plog.IfNode;
import parser.tree.plog.LookupVarNode;
import parser.tree.plog.NumNode;
import parser.tree.plog.ReadNode;
import parser.tree.plog.StmtListNode;
import parser.tree.plog.StringNode;
import parser.tree.plog.VarNode;
import parser.tree.plog.WhileNode;
import parser.tree.plog.WriteNode;
import runtime.plog.PModule;
import runtime.plog.PNumber;
import runtime.plog.PObject;

public class Interpreter extends Visitor {

	// private static final Stack stack = Stack.getInstance();
	private static final Scanner in = new Scanner(System.in);

	private PObjectStack stack;
	private PObjectStack callstack;

	public Interpreter(PModule module) {
		stack = new PObjectStack(module);
		callstack = new PObjectStack(module);
	}

	@Override
	public Object visitComp(CompNode n) {
		PObject a = (PObject) visit(n.lhs());
		PObject b = (PObject) visit(n.rhs());

		return a.invoke(stack.local(), n.operator().function(), b);
	}

	@Override
	public Object visitExpr(ExprNode n) {
		PObject a = (PObject) visit(n.lhs());
		PObject b = (PObject) visit(n.rhs());
		if (b == null) {
			return a;
		}
		return a.invoke(stack.local(), n.operator().function(), b);
	}

	@Override
	public Object visitAssign(AssignNode n) {
		AttrNode node = (AttrNode) ((ExprNode) n.var()).lhs();

		if (node.elements().size() != 1) {
			PObject object = (PObject) visit(node.elements().get(0));
			for (int i = 1; i < node.elements().size() - 1; i++) {
				stack.push(object);
				object = (PObject) visit(node.elements().get(i));
				stack.pop();
			}
			stack.push(object);
		}

		PObject value = (PObject) visit(n.expr());

		String var = ((VarNode) node.elements().get(node.elements().size() - 1))
				.var();
		stack.enter(var, value);
		stack.pop();

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
		visit(n.elements());
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

		stack.enter(var, new PNumber(value));
		return value;
	}

	@Override
	public Object visitWrite(WriteNode n) {
		for (Node expr : n.elements()) {
			PObject value = (PObject) visit(expr);
			System.out.print(value.invoke(stack.local(), "str"));
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
		PObject obj = stack.lookup(n.var());
		if (obj != null)
			return obj;
		else
			throw new RuntimeException(n.var() + " is not in __dict__");

	}

	@Override
	public Object visitString(StringNode n) {
		return n.string();
	}

	@Override
	public Object visitCall(CallNode node) {
		PObject object = (PObject) visit(node.name());
		if (object.respondTo("__call__")) {
			if (node.argument() != null) {
				PObject[] args = (PObject[]) visit(node.argument());
				object = object.invoke(stack.local(), "__call__", args);
			} else {
				object = object.invoke(stack.local(), "__call__");
			}
		} else {
			throw new IntepreterException(object.toString()
					+ " is not callable.");
		}
		return object;
	}

	@Override
	public Object visitExprList(ExprListNode exprListNode) {
		List<PObject> obj = new ArrayList<PObject>();
		for (Node n : exprListNode.elements()) {
			obj.add((PObject) visit(n));
		}

		return obj.toArray(new PObject[0]);
	}

	@Override
	public Object visitAttr(AttrNode node) {
		PObject object = (PObject) visit(node.elements().get(0));
		for (int n = 1; n < node.elements().size(); n++) {
			stack.push(object);
			object = (PObject) visit(node.elements().get(n));
			stack.pop();
		}
		return object;
	}
}
