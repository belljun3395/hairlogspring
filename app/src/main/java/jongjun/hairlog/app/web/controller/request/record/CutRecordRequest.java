package jongjun.hairlog.app.web.controller.request.record;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CutRecordRequest extends RecordRequest {

	private String cutName;
	private Long cutLength;
}
