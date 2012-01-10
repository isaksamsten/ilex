package token.plog;

public enum ErrorCode {
	INVALID_CHARACTER("Invalid character."), INVALID_NUMBER("Invalid number."), IO_ERROR(
			"Fatal input/output error."), INVALID_STATEMENT_END(
			"Not a statement."), MISSING_TERMINATOR("Missing terminator."), PREMATURE_EOF(
			"File ended prematurly."), INVALID_ASSIGN("Var not followed by :=."), INVALID_READ(
			"Unexpected argument to read."), UNEXPECTED_START_OF_STATEMENT(
			"Unexpected start of statement."), UNEXPECTED_END_OF_STATEMENT_LIST(
			"Unexpected end of statment list."), TO_MANY_ERRORS(
			"To many errors."), INVALID_EXPR("Invalid expression."), WHILE_NO_BODY(
			"While need a body."), INVALID_WRITE(
			"Unexpected argument to write."), UNEXPECTED_OPERATOR(
			"Unexpected operator."), EXPECTED_COMPARE("Expected an comparsion."), EXPECTED_STATEMENT_LIST(
			"Expected 'begin' 'end'."), EXPECTED_BEGIN("Expected 'begin'."), MISSING_DOT(
			"Expected a DOT."), EXPECTED_IDENTIFIER("Expected an identifier."), EXPECT_END_PAREN(
			"Unexpected end of call."), EXPECT_START_PAREN(
			"Unexpected start of call.");
	private String desc;

	private ErrorCode(String desc) {
		this.desc = desc;
	}

	public String description() {
		return desc;
	}
}