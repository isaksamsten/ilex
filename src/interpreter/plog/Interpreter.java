package interpreter.plog;

import java.util.Scanner;

import interpreter.Stack;
import interpreter.TableEntry;
import interpreter.TableKey;
import parser.tree.plog.AssignNode;
import parser.tree.plog.CompNode;
import parser.tree.plog.ExprNode;
import parser.tree.plog.NumNode;
import parser.tree.plog.ReadNode;
import parser.tree.plog.StmtListNode;
import parser.tree.plog.TermNode;
import parser.tree.plog.VarNode;
import parser.tree.plog.WhileNode;
import parser.tree.plog.WriteNode;

public class Interpreter extends Visitor {

	private static final Stack stack = Stack.getInstance();
	private static final Scanner in = new Scanner(System.in);

	private int calculate(Number a, Object b, String op) {
		if (b == null)
			return a.intValue();

		if (op.equals("PLUS")) {
			return a.intValue() + ((Number) b).intValue();
		} else if (op.equals("MINUS")) {
			return a.intValue() - ((Number) b).intValue();
		} else if (op.equals("STAR")) {
			return a.intValue() * ((Number) b).intValue();
		} else {
			return a.intValue() / ((Number) b).intValue();
		}

	}

	private boolean compare(Number a, Number b, String op) {
		if (op.equals("EQUAL")) {
			return a.intValue() == ((Number) b).intValue();
		} else if (op.equals("LT")) {
			return a.intValue() < ((Number) b).intValue();
		} else {
			return a.intValue() > ((Number) b).intValue();
		}
	}

	@Override
	public Object visitComp(CompNode n) {
		Number a = (Number) visit(n.lhs());
		Number b = (Number) visit(n.rhs());

		return compare(a, b, n.compareOp());
	}

	@Override
	public Object visitExpr(ExprNode n) {
		Number a = (Number) visit(n.lhs());
		Object b = visit(n.rhs());

		return calculate(a, b, n.operator());
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
		return n.value();
	}

	@Override
	public Object visitStmtList(StmtListNode n) {
		visit(n.statements());
		return null;
	}

	@Override
	public Object visitWhile(WhileNode n) {
		boolean compared = (Boolean) visit(n.compare());
		stack.push();
		while (compared) {
			visit(n.statementList());
			compared = (Boolean) visit(n.compare());
		}
		stack.pop();
		return null;
	}

	@Override
	public Object visitRead(ReadNode n) {
		String var = (String) visit(n.var());
		System.out.print("?- ");
		int value = in.nextInt();

		stack.enter(var).putAttribute(TableKey.CONSTANT, value);
		return value;
	}

	@Override
	public Object visitWrite(WriteNode n) {
		Object value = visit(n.expr());
		System.out.println(value);

		return value;
	}

	@Override
	public Object visitTerm(TermNode n) {
		if (n.isNumber()) {
			return visit(n.number());
		} else {
			String var = (String) visit(n.var());
			TableEntry entry = stack.lookup(var);
			if (entry != null)
				return entry.getAttribute(TableKey.CONSTANT);
			else
				throw new IntepreterException("Var '" + var + "' not initialized");
		}
	}
}
