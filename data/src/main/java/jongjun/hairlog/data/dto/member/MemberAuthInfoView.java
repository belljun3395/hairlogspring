package jongjun.hairlog.data.dto.member;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MemberAuthInfoView {

	private String name;
	private String email;
	private String password;

	@QueryProjection
	public MemberAuthInfoView(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
}
