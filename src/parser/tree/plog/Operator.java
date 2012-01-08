package parser.tree.plog;

import parser.tree.IOperator;
import token.plog.TokenType;

public enum Operator implements IOperator {
	PLUS("add"), MINUS("sub"), MULTIPLICATION("mul"), DIVISION("div"), MODULA(
			"mod"), GREATER_THAN("gt"), LESS_THAN("lt"), GREATER_THAN_EQUAL(
			"gte"), LESS_THAN_EQUAL("lte"), EQUAL("eq");

	String name;

	Operator(String name) {
		this.name = name;
	}

	public String function() {
		return name;
	}

	public static Operator fromTokenType(TokenType t) {
		switch (t) {
		case PLUS:
			return PLUS;
		case MINUS:
			return MINUS;
		case STAR:
			return MULTIPLICATION;
		case PERCENT:
			return MODULA;
		case SLASH:
			return DIVISION;
		case GT:
			return GREATER_THAN;
		case GTE:
			return GREATER_THAN_EQUAL;
		case LT:
			return LESS_THAN;
		case LTE:
			return LESS_THAN_EQUAL;
		case EQUAL:
			return EQUAL;
		}

		return null;
	}
}
