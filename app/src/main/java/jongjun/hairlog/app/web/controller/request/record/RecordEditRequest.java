package jongjun.hairlog.app.web.controller.request.record;

import java.util.Date;
import jongjun.hairlog.data.enums.SatisfactionRate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecordEditRequest {

	private Long id;

	private Date recordDate;

	private Long recordCost;

	private String recordEtc;

	private SatisfactionRate recordGrade;

	private Long designerId;
}
