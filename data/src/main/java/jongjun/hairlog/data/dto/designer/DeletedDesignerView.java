package jongjun.hairlog.data.dto.designer;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class DeletedDesignerView {

	private Long id;
	private String designerName;
	private String designerSalon;

	@QueryProjection
	public DeletedDesignerView(Long id, String designerName, String designerSalon) {
		this.id = id;
		this.designerName = designerName;
		this.designerSalon = designerSalon;
	}
}
