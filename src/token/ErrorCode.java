package token;

public enum ErrorCode {
	INVALID_CHARACTER("Invalid character"), INVALID_NUMBER("Invalid number"), IO_ERROR(
			"Fatal input/output error");

	private String desc;

	private ErrorCode(String desc) {
		this.desc = desc;
	}

	public String description() {
		return desc;
	}
}
