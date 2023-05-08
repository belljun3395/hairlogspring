package jongjun.hairlog.app.web.controller.request.record;

import java.util.Date;
import jongjun.hairlog.data.enums.SatisfactionRate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/** @implSpec {@link jongjun.hairlog.data.entity.record.RecordEntity} 관련 요청의 경우 상속하여 사용한다. */
@SuperBuilder(toBuilder = true)
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class RecordRequest {

	private Date recordDate;

	private Long recordCost;

	private String recordEtc;

	private SatisfactionRate recordGrade;

	private Long designerId;
}
