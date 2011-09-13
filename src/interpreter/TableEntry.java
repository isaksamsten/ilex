package interpreter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableEntry {

	private Table parent = null;
	private String name = null;
	private List<Integer> lines = new ArrayList<Integer>();
	private Map<TableKey, Object> entries = new HashMap<TableKey, Object>();

	public TableEntry(String name, Table parent) {
		this.name = name;
		this.parent = parent;
	}

	public String name() {
		return name;
	}

	public Table parent() {
		return parent;
	}

	public void addLine(int line) {
		lines.add(line);
	}

	public List<Integer> lines() {
		return Collections.unmodifiableList(lines);
	}

	public void putAttribute(TableKey key, Object value) {
		entries.put(key, value);
	}

	public Object getAttribute(TableKey key) {
		return entries.get(key);
	}

}
