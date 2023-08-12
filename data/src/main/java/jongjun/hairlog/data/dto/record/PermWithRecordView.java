package jongjun.hairlog.data.dto.record;

import com.querydsl.core.annotations.QueryProjection;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.entity.record.CommonRecordInfo;
import jongjun.hairlog.data.enums.HurtRate;
import jongjun.hairlog.data.enums.RecordCategory;
import lombok.Getter;

@Getter
public class PermWithRecordView extends RecordView {

	private String permName;

	private Long permTime;

	private HurtRate permHurt;

	@QueryProjection
	public PermWithRecordView(
			Long id,
			CommonRecordInfo recordInfo,
			RecordCategory recordCategory,
			Long subId,
			MemberEntity member,
			DesignerEntity designer,
			String permName,
			Long permTime,
			HurtRate permHurt) {
		super(id, recordInfo, recordCategory, subId, member, designer);
		this.permName = permName;
		this.permTime = permTime;
		this.permHurt = permHurt;
	}
}
