package jongjun.hairlog.data.dto.member;

import com.querydsl.core.annotations.QueryProjection;
import jongjun.hairlog.data.enums.MemberSex;
import lombok.Getter;

@Getter
public class MemberInfoView {

	private String email;
	private String name;
	private MemberSex sex;
	private Long cycle;

	@QueryProjection
	public MemberInfoView(String email, String name, MemberSex sex, Long cycle) {
		this.email = email;
		this.name = name;
		this.sex = sex;
		this.cycle = cycle;
	}
}
