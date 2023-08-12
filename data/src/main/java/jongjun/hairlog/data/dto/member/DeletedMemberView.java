package jongjun.hairlog.data.dto.member;

import com.querydsl.core.annotations.QueryProjection;
import jongjun.hairlog.data.enums.MemberSex;
import lombok.Getter;

@Getter
public class DeletedMemberView {

	private String email;
	private MemberSex sex;

	@QueryProjection
	public DeletedMemberView(String email, MemberSex sex) {
		this.email = email;
		this.sex = sex;
	}
}
