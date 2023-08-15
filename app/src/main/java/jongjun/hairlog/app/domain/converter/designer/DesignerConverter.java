package jongjun.hairlog.app.domain.converter.designer;

import jongjun.hairlog.app.config.security.AuditorHolder;
import jongjun.hairlog.app.domain.model.designer.Designer;
import jongjun.hairlog.app.web.controller.request.designer.DesignerRequest;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import org.springframework.stereotype.Component;

/** todoo response converter */
@Component
public class DesignerConverter {
	public Designer from(DesignerEntity source) {
		return Designer.builder()
				.id(source.getId())
				.designerName(source.getDesignerName())
				.designerSalon(source.getDesignerSalon())
				.designerFav(source.getDesignerFav())
				.build();
	}

	public DesignerEntity to(DesignerRequest source) {
		Long memberId = AuditorHolder.get();
		return DesignerEntity.builder()
				.designerName(source.getDesignerName())
				.designerSalon(source.getDesignerSalon())
				.designerFav(source.getDesignerFav())
				.member(MemberEntity.builder().id(memberId).build())
				.build();
	}
}
