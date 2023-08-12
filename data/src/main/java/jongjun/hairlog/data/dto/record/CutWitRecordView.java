package jongjun.hairlog.data.dto.record;

import com.querydsl.core.annotations.QueryProjection;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.entity.record.CommonRecordInfo;
import jongjun.hairlog.data.enums.RecordCategory;
import lombok.Getter;

@Getter
public class CutWitRecordView extends RecordView {

	private String cutName;

	private Long cutLength;

	@QueryProjection
	public CutWitRecordView(
			Long id,
			CommonRecordInfo recordInfo,
			RecordCategory recordCategory,
			Long subId,
			MemberEntity member,
			DesignerEntity designer,
			String cutName,
			Long cutLength) {
		super(id, recordInfo, recordCategory, subId, member, designer);
		this.cutName = cutName;
		this.cutLength = cutLength;
	}
}
