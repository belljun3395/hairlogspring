package jongjun.hairlog.app.web.controller.request.member;

import jongjun.hairlog.data.enums.MemberSex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {

	private String email;
	private String password;
	private String name;
	private MemberSex sex;
	private Long cycle;
}
