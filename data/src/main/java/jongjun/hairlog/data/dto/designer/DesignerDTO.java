package jongjun.hairlog.data.dto.designer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DesignerDTO {

	private final Long designerId;
	private final String designerName;
	private final String designerSalon;
	private final Boolean designerFav;
}
