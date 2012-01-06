package token;

public enum ErrorCode {
	INVALID_CHARACTER("Invalid character"), INVALID_NUMBER("Invalid number"), IO_ERROR(
			"Fatal input/output error"), INVALID_STATEMENT_END(
			"Statement not ended with a semicolon"), MISSING_TERMINATOR(
			"Missing terminator (})"), PREMATURE_EOF("File ended prematurly"), INVALID_ASSIGN(
			"var not followed by :="), READ_EXPECT_ID(
			"Read expect identifier as argument"), INVALID_START_OF_STATEMENT(
			"Invalid start of statement"), INVALID_END_OF_STATEMENT_LIST(
			"Statment list not ending with 'end'"), TO_MANY_ERRORS(
			"To many errors"), EXPECTED_EXPR("Invalid start of compare"), WHILE_NO_BODY(
			"While need a body");

	private String desc;

	private ErrorCode(String desc) {
		this.desc = desc;
	}

	public String description() {
		return desc;
	}
}
