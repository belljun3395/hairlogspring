package jongjun.hairlog.app.web.controller.request.designer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DesignerRequest {

	private String designerName;

	private String designerSalon;

	private Boolean designerFav;
}
