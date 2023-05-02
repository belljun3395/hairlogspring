package jongjun.hairlog.data.dto.designer;

import java.math.BigInteger;
import lombok.Getter;

@Getter
public class DesignerDTO {

	private final Long designerId;
	private final String designerName;
	private final String designerSalon;
	private final Boolean designerFav;

	public DesignerDTO(
			BigInteger designerId, String designerName, String designerSalon, Boolean designerFav) {
		this.designerId = Long.valueOf(designerId.toString());
		this.designerName = designerName;
		this.designerSalon = designerSalon;
		this.designerFav = designerFav;
	}

	public DesignerDTO(
			Long designerId, String designerName, String designerSalon, Boolean designerFav) {
		this.designerId = designerId;
		this.designerName = designerName;
		this.designerSalon = designerSalon;
		this.designerFav = designerFav;
	}
}
