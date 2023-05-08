package jongjun.hairlog.data.dto.record;

import java.math.BigInteger;
import java.util.Date;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.enums.SatisfactionRate;
import lombok.Getter;

/** @implSpec {@link jongjun.hairlog.data.entity.record.RecordEntity} 관련 DTO 경우 상속하여 사용한다. */
@Getter
public abstract class RecordDTO {

	private final Long id;

	private final Date recordDate;

	private final Long recordCost;

	private final String recordEtc;

	private final SatisfactionRate recordGrade;

	private final MemberEntity member;

	private final DesignerEntity designer;

	public RecordDTO(
			Long id,
			Date recordDate,
			Long recordCost,
			String recordEtc,
			SatisfactionRate recordGrade,
			Long member,
			Long designer) {
		this.id = id;
		this.recordDate = recordDate;
		this.recordCost = recordCost;
		this.recordEtc = recordEtc;
		this.recordGrade = recordGrade;
		this.member = MemberEntity.builder().id(member).build();
		this.designer = DesignerEntity.builder().id(designer).build();
	}

	public RecordDTO(
			BigInteger id,
			Date recordDate,
			BigInteger recordCost,
			String recordEtc,
			String recordGrade,
			BigInteger member,
			BigInteger designer) {
		this.id = Long.valueOf(id.toString());
		this.recordDate = recordDate;
		this.recordCost = Long.valueOf(recordCost.toString());
		this.recordEtc = recordEtc;
		this.recordGrade = SatisfactionRate.valueOf(recordGrade.toUpperCase());
		this.member = MemberEntity.builder().id(Long.valueOf(member.toString())).build();
		this.designer = DesignerEntity.builder().id(Long.valueOf(designer.toString())).build();
	}
}
