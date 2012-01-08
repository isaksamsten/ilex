package interpreter.plog;

import interpreter.Stack;
import interpreter.TableEntry;
import interpreter.TableKey;

import java.util.Scanner;

import parser.tree.plog.AssignNode;
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

public class Interpreter extends Visitor {

	private static final Stack stack = Stack.getInstance();
	private static final Scanner in = new Scanner(System.in);

	private Object calculate(Object a, Object b, String op) {
		if (b == null)
			return a;

		if (!(a instanceof Number))
			throw new IntepreterException("\"" + a + "\" is not a number.");

		if (!(b instanceof Number))
			throw new IntepreterException("\"" + b + "\" is not a number.");

		if (op.equals("PLUS")) {
			return ((Number) a).intValue() + ((Number) b).intValue();
		} else if (op.equals("MINUS")) {
			return ((Number) a).intValue() - ((Number) b).intValue();
		} else if (op.equals("STAR")) {
			return ((Number) a).intValue() * ((Number) b).intValue();
		} else if (op.equals("PERCENT")) {
			return ((Number) a).intValue() % ((Number) b).intValue();
		} else {
			return ((Number) a).intValue() / ((Number) b).intValue();
		}

	}

	private boolean compare(Number a, Number b, Operator op) {
		if (op == Operator.EQUAL) {
			return a.intValue() == ((Number) b).intValue();
		} else if (op == Operator.LESS_THAN) {
			return a.intValue() < ((Number) b).intValue();
		} else if (op == Operator.LESS_THAN_EQUAL) {
			return a.intValue() <= ((Number) b).intValue();
		} else if (op == Operator.GREATER_THAN_EQUAL) {
			return a.intValue() >= ((Number) b).intValue();
		} else {
			return a.intValue() > ((Number) b).intValue();
		}
	}

	@Override
	public Object visitComp(CompNode n) {
		Number a = (Number) visit(n.lhs());
		Number b = (Number) visit(n.rhs());

		return compare(a, b, n.operator());
	}

	@Override
	public Object visitExpr(ExprNode n) {
		Object a = visit(n.lhs());
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
		return n.var();
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
		int value = in.nextInt();

		stack.enter(var).putAttribute(TableKey.CONSTANT, value);
		return value;
	}

	@Override
	public Object visitWrite(WriteNode n) {
		for (ExprNode expr : n.expr()) {
			Object value = visit(expr);
			System.out.print(value);
		}

		return null;
	}

	@Override
	public Object visitTerm(TermNode n) {
		return visit(n.term());
	}

	@Override
	public Object visitIf(IfNode n) {
		boolean compare = (Boolean) visit(n.compare());
		if (compare) {
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
}
