package jongjun.hairlog.app.domain.converter.designer;

import jongjun.hairlog.app.domain.model.designer.Designer;
import jongjun.hairlog.data.entity.DesignerEntity;
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
}
