package interpreter;

import java.util.ArrayList;

import runtime.plog.PObject;

public class PObjectStack {

	private ArrayList<PObject> stack = new ArrayList<PObject>();
	private int currentLevel = 0;

	public PObjectStack(PObject table) {
		stack.add(table);
	}

	public int currentNestingLevel() {
		return currentLevel;
	}

	public PObject local() {
		return stack.get(currentLevel);
	}

	public PObject push() {
		++currentLevel;
		stack.add(new PObject());
		return stack.get(currentLevel);
	}

	public PObject push(PObject table) {
		++currentLevel;
		stack.add(table);
		return table;
	}

	public PObject pop() {
		if (currentLevel != 0)
			return stack.remove(currentLevel--);
		return stack.get(currentLevel);
	}

	public PObject lookupLocal(String name) {
		return stack.get(currentLevel).dict(name);
	}

	public PObject enter(String name, PObject value) {
		return stack.get(currentLevel).dict(name, value);
	}

	public PObject lookup(String name) {
		PObject found = null;
		for (int i = currentLevel; i >= 0 && found == null; i--) {
			found = stack.get(i).dict(name);
		}
		return found;
	}

}
