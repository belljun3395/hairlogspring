package jongjun.hairlog.data.dto.record;

import com.querydsl.core.annotations.QueryProjection;
import jongjun.hairlog.data.entity.record.CommonRecordInfo;
import jongjun.hairlog.data.enums.RecordCategory;
import lombok.Getter;

@Getter
public class RecordIdView {

	private Long id;

	private CommonRecordInfo recordInfo;

	private RecordCategory recordCategory;

	private Long subId;

	@QueryProjection
	public RecordIdView(
			Long id, CommonRecordInfo recordInfo, RecordCategory recordCategory, Long subId) {
		this.id = id;
		this.recordInfo = recordInfo;
		this.recordCategory = recordCategory;
		this.subId = subId;
	}
}
