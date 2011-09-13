package main;

import java.util.HashMap;
import java.util.Map;

public class Keyword extends God {

	private static Map<String, Keyword> keywords = new HashMap<String, Keyword>();

	public static Keyword get(String name) {
		Keyword keyword = keywords.get(name);
		if (keyword == null) {
			keyword = new Keyword(name);
			keywords.put(name, keyword);
		}

		return keyword;
	}

	public static final char START = ':';

	protected Keyword(String name) {
		this.parent = new God(this.id());
		prop("name", name);
	}

	@Override
	public String name() {
		return prop("name");
	}

	@Override
	public void initialize(Map<String, Object> args) {
		super.initialize(args);
	}

}
