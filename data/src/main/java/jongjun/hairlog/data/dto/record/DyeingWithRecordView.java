package jongjun.hairlog.data.dto.record;

import com.querydsl.core.annotations.QueryProjection;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.entity.record.CommonRecordInfo;
import jongjun.hairlog.data.enums.HurtRate;
import jongjun.hairlog.data.enums.RecordCategory;
import lombok.Getter;

@Getter
public class DyeingWithRecordView extends RecordView {

	private String dyeingColor;

	private String dyeingDecolorization;

	private Long dyeingTime;

	private HurtRate dyeingHurt;

	@QueryProjection
	public DyeingWithRecordView(
			Long id,
			CommonRecordInfo recordInfo,
			RecordCategory recordCategory,
			Long subId,
			MemberEntity member,
			DesignerEntity designer,
			String dyeingColor,
			String dyeingDecolorization,
			Long dyeingTime,
			HurtRate dyeingHurt) {
		super(id, recordInfo, recordCategory, subId, member, designer);
		this.dyeingColor = dyeingColor;
		this.dyeingDecolorization = dyeingDecolorization;
		this.dyeingTime = dyeingTime;
		this.dyeingHurt = dyeingHurt;
	}
}
