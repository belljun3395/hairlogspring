package jongjun.hairlog.app.exception;

import java.util.Objects;

/** memberId 로 회원 조회 실패시 발생하는 예외 */
public class MemberNotFoundException extends RuntimeException {
	private Long memberId;
	private String email;

	public MemberNotFoundException(Long memberId) {
		this.memberId = memberId;
	}

	public MemberNotFoundException(String email) {
		this.email = email;
	}

	@Override
	public String getMessage() {
		return Objects.isNull(email)
				? "존재하지 않는 회원입니다. memberId: " + memberId
				: "존재하지 않는 회원입니다. email : " + email;
	}
}
