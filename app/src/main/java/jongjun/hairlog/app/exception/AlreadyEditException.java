package jongjun.hairlog.app.exception;

import java.util.Objects;

public class AlreadyEditException extends RuntimeException {
	private Long id;
	private String message;

	public AlreadyEditException(Long id) {
		this.id = id;
	}

	public AlreadyEditException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return Objects.isNull(message) ? "이미 수정한 정보입니다. id: " + id : message;
	}
}
