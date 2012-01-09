package parser.tree.plog;

import runtime.plog.PNumber;
import runtime.plog.PObject;
import interpreter.plog.Visitor;

public class NumNode extends TermNode {

	private PNumber number;

	public NumNode(int line, Number number) {
		super(line);
		this.number = new PNumber(number);
	}

	public PObject number() {
		return number;
	}

	@Override
	public String toTreeString() {
		return "(" + toString() + " value='" + number + "')";
	}

	@Override
	public Object visit(Visitor visitor) {
		return visitor.visitNum(this);
	}

}
