package jongjun.hairlog.app.domain.model.record;

import java.util.Date;
import jongjun.hairlog.data.enums.RecordCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RecordIndex {

	private Long recordId;

	private Date recordDate;

	private RecordCategory recordCategory;
}
