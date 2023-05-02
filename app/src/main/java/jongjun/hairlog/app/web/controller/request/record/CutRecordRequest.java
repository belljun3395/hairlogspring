package jongjun.hairlog.app.web.controller.request.record;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CutRecordRequest extends RecordRequest {

	private String cutName;
	private Long cutLength;
}
