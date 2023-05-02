package jongjun.hairlog.app.domain.converter.designer;

import jongjun.hairlog.app.domain.model.designer.Designer;
import jongjun.hairlog.app.web.controller.request.designer.DesignerRequest;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import org.springframework.stereotype.Component;

@Component
public class DesignerConverter {

	public Designer from(DesignerEntity source) {
		return Designer.builder()
				.id(source.getId())
				.designerName(source.getDesignerName())
				.designerSalon(source.getDesignerSalon())
				.designerFav(source.getDesignerFav())
				.createAt(source.getCreateAt())
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
