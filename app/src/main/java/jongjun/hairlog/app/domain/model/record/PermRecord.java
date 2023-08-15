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
public class PermRecord extends Record {

	private String permName;

	private Long permTime;

	private HurtRate permHurt;
}
