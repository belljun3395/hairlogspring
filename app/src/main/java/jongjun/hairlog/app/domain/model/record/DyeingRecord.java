package jongjun.hairlog.app.domain.model.record;

import jongjun.hairlog.data.enums.HurtRate;
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
@EqualsAndHashCode(callSuper = true)
@ToString
public class DyeingRecord extends Record {
	private String dyeingColor;

	private String dyeingDecolorization;

	private Long dyeingTime;

	private HurtRate dyeingHurt;
}
