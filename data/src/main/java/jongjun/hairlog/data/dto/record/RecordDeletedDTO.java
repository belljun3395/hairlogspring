package jongjun.hairlog.data.dto.record;

import java.util.Date;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RecordDeletedDTO {

	private final Long recordId;
	private final Date recordDate;
}
