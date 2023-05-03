package jongjun.hairlog.data.dto.record;

import java.math.BigInteger;
import java.util.Date;
import jongjun.hairlog.data.enums.SatisfactionRate;
import lombok.Getter;

@Getter
public class CutDTO extends RecordDTO {

	private final String cutName;

	private final Long cutLength;

	public CutDTO(
			Long id,
			Date recordDate,
			Long recordCost,
			String recordEtc,
			SatisfactionRate recordGrade,
			Long memberId,
			Long designerId,
			String cutName,
			Long cutLength) {
		super(id, recordDate, recordCost, recordEtc, recordGrade, memberId, designerId);
		this.cutName = cutName;
		this.cutLength = cutLength;
	}

	public CutDTO(
			BigInteger id,
			Date recordDate,
			BigInteger recordCost,
			String recordEtc,
			String recordGrade,
			BigInteger memberId,
			BigInteger designerId,
			String cutName,
			BigInteger cutLength) {
		super(id, recordDate, recordCost, recordEtc, recordGrade, memberId, designerId);
		this.cutName = cutName;
		this.cutLength = Long.valueOf(cutLength.toString());
	}
}
