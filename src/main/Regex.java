package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex extends IObject {
	private Pattern pattern;

	public Regex(String pattern) {
		this.pattern = Pattern.compile(pattern);
		prop("pattern", pattern);
	}

	public boolean matches(CharSequence input) {
		return matcher(input).matches();
	}

	public boolean equals(Object obj) {
		return pattern.equals(obj);
	}

	public int flags() {
		return pattern.flags();
	}

	public int hashCode() {
		return pattern.hashCode();
	}

	public Matcher matcher(CharSequence input) {
		return pattern.matcher(input);
	}

	public String pattern() {
		return pattern.pattern();
	}

	public String[] split(CharSequence input, int limit) {
		return pattern.split(input, limit);
	}

	public String[] split(CharSequence input) {
		return pattern.split(input);
	}

}
