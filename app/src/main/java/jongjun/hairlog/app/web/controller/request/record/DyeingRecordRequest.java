package jongjun.hairlog.app.web.controller.request.record;

import jongjun.hairlog.data.enums.HurtRate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DyeingRecordRequest extends RecordRequest {

	private String dyeingColor;

	private String dyeingDecolorization;

	private Long dyeingTime;

	private HurtRate dyeingHurt;
}
