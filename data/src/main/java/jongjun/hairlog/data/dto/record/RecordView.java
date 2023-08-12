package jongjun.hairlog.data.dto.record;

import com.querydsl.core.annotations.QueryProjection;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.entity.record.CommonRecordInfo;
import jongjun.hairlog.data.enums.RecordCategory;
import lombok.Getter;

@Getter
public abstract class RecordView {

	private Long id;

	private CommonRecordInfo recordInfo;

	private RecordCategory recordCategory;

	private Long subId;

	private MemberEntity member;

	private DesignerEntity designer;

	@QueryProjection
	public RecordView(
			Long id,
			CommonRecordInfo recordInfo,
			RecordCategory recordCategory,
			Long subId,
			MemberEntity member,
			DesignerEntity designer) {
		this.id = id;
		this.recordInfo = recordInfo;
		this.recordCategory = recordCategory;
		this.subId = subId;
		this.member = member;
		this.designer = designer;
	}
}
