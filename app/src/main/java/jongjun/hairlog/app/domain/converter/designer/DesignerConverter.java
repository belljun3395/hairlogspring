package jongjun.hairlog.app.domain.converter.designer;

import jongjun.hairlog.app.domain.model.designer.Designer;
import jongjun.hairlog.app.web.controller.request.designer.DesignerRequest;
import jongjun.hairlog.data.dto.designer.DesignerDTO;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import org.springframework.stereotype.Component;

@Component
public class DesignerConverter {

	public Designer from(DesignerDTO source) {
		return Designer.builder()
				.id(source.getDesignerId())
				.designerName(source.getDesignerName())
				.designerSalon(source.getDesignerSalon())
				.designerFav(source.getDesignerFav())
				.build();
	}

	public DesignerEntity to(DesignerRequest request) {
		return DesignerEntity.builder()
				.designerName(request.getDesignerName())
				.designerSalon(request.getDesignerSalon())
				.designerFav(request.getDesignerFav())
				.member(MemberEntity.builder().id(request.getMemberId()).build())
				.build();
	}
}
