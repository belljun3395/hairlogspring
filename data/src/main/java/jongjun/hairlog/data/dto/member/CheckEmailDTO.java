package jongjun.hairlog.data.dto.member;

import java.math.BigInteger;
import lombok.Getter;

@Getter
public class CheckEmailDTO {

	private final Long count;

	public CheckEmailDTO(Long count) {
		this.count = count;
	}

	public CheckEmailDTO(BigInteger count) {
		this.count = Long.valueOf(count.toString());
	}

	public boolean isExist() {
		return count > 0;
	}
}
