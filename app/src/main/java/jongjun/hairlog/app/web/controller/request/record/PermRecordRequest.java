package jongjun.hairlog.app.web.controller.request.record;

import jongjun.hairlog.data.enums.HurtRate;
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
public class PermRecordRequest extends RecordRequest {

	private String permName;

	private Long permTime;

	private HurtRate permHurt;
}
