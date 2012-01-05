package token;

public enum ErrorCode {
	INVALID_CHARACTER("Invalid character"), INVALID_NUMBER("Invalid number"), IO_ERROR(
			"Fatal input/output error"), INVALID_STATEMENT_END(
			"Statement not ended with a semicolon"), MISSING_TERMINATOR(
			"Missing terminator (})"), PREMATURE_EOF("File ended prematurly"), INVALID_ASSIGN(
			"var not followed by :=");

	private String desc;

	private ErrorCode(String desc) {
		this.desc = desc;
	}

	public String description() {
		return desc;
	}
}
