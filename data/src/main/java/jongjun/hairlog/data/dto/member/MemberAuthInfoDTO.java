package jongjun.hairlog.data.dto.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberAuthInfoDTO {

	private final Long id;
	private final String name;
	private final String email;
	private final String password;
}
