package jongjun.hairlog.app.domain.model.designer;

import java.time.LocalDateTime;
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
public class Designer {

	private Long id;

	private String designerName;

	private String designerSalon;

	private Boolean designerFav;

	private LocalDateTime createAt;
}
