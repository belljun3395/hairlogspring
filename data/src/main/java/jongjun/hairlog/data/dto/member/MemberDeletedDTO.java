package jongjun.hairlog.data.dto.member;

import jongjun.hairlog.data.enums.MemberSex;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberDeletedDTO {

	private final String email;
	private final MemberSex sex;

	public MemberDeletedDTO(String email, String sex) {
		this.email = email;
		this.sex = MemberSex.valueOf(sex.toUpperCase());
	}
}
