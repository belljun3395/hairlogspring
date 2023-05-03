package jongjun.hairlog.app.domain.model.record;

import java.util.Date;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.enums.SatisfactionRate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class Record {

	private Date recordDate;

	private Long recordCost;

	private String recordEtc;

	private SatisfactionRate recordGrade;

	private DesignerEntity designer;
}
