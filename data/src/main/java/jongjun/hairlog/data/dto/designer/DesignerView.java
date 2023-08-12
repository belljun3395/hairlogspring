package jongjun.hairlog.data.dto.designer;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class DesignerView {

	private Long designerId;
	private String designerName;
	private String designerSalon;
	private Boolean designerFav;

	@QueryProjection
	public DesignerView(Long id, String designerName, String designerSalon, Boolean designerFav) {
		this.designerId = id;
		this.designerName = designerName;
		this.designerSalon = designerSalon;
		this.designerFav = designerFav;
	}
}
