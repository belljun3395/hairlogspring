package jongjun.hairlog.data.dto.member;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MemberInfoView {

	private String email;
	private String name;

	@QueryProjection
	public MemberInfoView(String email, String name) {
		this.email = email;
		this.name = name;
	}
}
