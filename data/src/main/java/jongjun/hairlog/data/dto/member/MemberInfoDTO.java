package jongjun.hairlog.data.dto.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberInfoDTO {

	private final String email;
	private final String name;
}
