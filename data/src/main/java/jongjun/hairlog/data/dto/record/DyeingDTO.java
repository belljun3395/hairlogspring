package jongjun.hairlog.data.dto.record;

import java.math.BigInteger;
import java.util.Date;
import jongjun.hairlog.data.enums.HurtRate;
import jongjun.hairlog.data.enums.SatisfactionRate;
import lombok.Getter;

@Getter
public class DyeingDTO extends RecordDTO {

	private final String dyeingColor;

	private final String dyeingDecolorization;

	private final Long dyeingTime;

	private final HurtRate dyeingHurt;

	public DyeingDTO(
			Long id,
			Date recordDate,
			Long recordCost,
			String recordEtc,
			SatisfactionRate recordGrade,
			Long memberId,
			Long designerId,
			String dyeingColor,
			String dyeingDecolorization,
			Long dyeingTime,
			String dyeingHurt) {
		super(id, recordDate, recordCost, recordEtc, recordGrade, memberId, designerId);
		this.dyeingColor = dyeingColor;
		this.dyeingDecolorization = dyeingDecolorization;
		this.dyeingTime = dyeingTime;
		this.dyeingHurt = HurtRate.valueOf(dyeingHurt.toUpperCase());
	}

	public DyeingDTO(
			BigInteger id,
			Date recordDate,
			BigInteger recordCost,
			String recordEtc,
			String recordGrade,
			BigInteger memberId,
			BigInteger designerId,
			String dyeingColor,
			String dyeingDecolorization,
			BigInteger dyeingTime,
			String dyeingHurt) {
		super(id, recordDate, recordCost, recordEtc, recordGrade, memberId, designerId);
		this.dyeingColor = dyeingColor;
		this.dyeingDecolorization = dyeingDecolorization;
		this.dyeingTime = Long.valueOf(dyeingTime.toString());
		this.dyeingHurt = HurtRate.valueOf(dyeingHurt.toUpperCase());
	}
}
