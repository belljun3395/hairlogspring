package jongjun.hairlog.data.dto.record;

import java.math.BigInteger;
import java.util.Date;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RecordDeletedDTO {

	private final Long recordId;
	private final Date recordDate;

	public RecordDeletedDTO(BigInteger recordId, Date recordDate) {
		this.recordId = Long.valueOf(recordId.toString());
		this.recordDate = recordDate;
	}
}
