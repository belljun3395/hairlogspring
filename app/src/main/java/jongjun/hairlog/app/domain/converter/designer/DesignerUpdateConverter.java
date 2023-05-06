package jongjun.hairlog.app.domain.converter.designer;

import jongjun.hairlog.data.entity.DesignerEntity;
import org.springframework.stereotype.Component;

@Component
public class DesignerUpdateConverter {

	public void to(DesignerEntity source, Boolean fav) {
		source.changeDesignerFav(fav);
	}
}
