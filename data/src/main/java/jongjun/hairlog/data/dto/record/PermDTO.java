package jongjun.hairlog.data.dto.record;

import java.math.BigInteger;
import java.util.Date;
import jongjun.hairlog.data.enums.HurtRate;
import jongjun.hairlog.data.enums.SatisfactionRate;
import lombok.Getter;

@Getter
public class PermDTO extends RecordDTO {

	private final String permName;

	private final Long permTime;

	private final HurtRate permHurt;

	public PermDTO(
			Long id,
			Date recordDate,
			Long recordCost,
			String recordEtc,
			SatisfactionRate recordGrade,
			Long memberId,
			Long designerId,
			String permName,
			Long permTime,
			HurtRate permHurt) {
		super(id, recordDate, recordCost, recordEtc, recordGrade, memberId, designerId);
		this.permName = permName;
		this.permTime = permTime;
		this.permHurt = permHurt;
	}

	public PermDTO(
			BigInteger id,
			Date recordDate,
			BigInteger recordCost,
			String recordEtc,
			String recordGrade,
			BigInteger memberId,
			BigInteger designerId,
			String permName,
			BigInteger permTime,
			String permHurt) {
		super(id, recordDate, recordCost, recordEtc, recordGrade, memberId, designerId);
		this.permName = permName;
		this.permTime = Long.valueOf(permTime.toString());
		this.permHurt = HurtRate.valueOf(permHurt.toUpperCase());
	}
}
