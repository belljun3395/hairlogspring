package jongjun.hairlog.data.dto.record;

import java.math.BigInteger;
import java.util.Date;
import jongjun.hairlog.data.enums.RecordCategory;
import lombok.Getter;

@Getter
public class RecordIndexDTO {

	private final BigInteger recordId;

	private final Date recordDate;

	private final RecordCategory recordCategory;

	public RecordIndexDTO(BigInteger recordId, Date recordDate, String recordCategory) {
		this.recordId = recordId;
		this.recordDate = recordDate;
		this.recordCategory = RecordCategory.valueOf(recordCategory.toUpperCase());
	}
}
