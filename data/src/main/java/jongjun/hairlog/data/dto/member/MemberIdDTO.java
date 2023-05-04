package jongjun.hairlog.data.dto.member;

import java.math.BigInteger;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberIdDTO {

	private final Long id;

	public MemberIdDTO(BigInteger id) {
		this.id = Long.valueOf(id.toString());
	}
}
