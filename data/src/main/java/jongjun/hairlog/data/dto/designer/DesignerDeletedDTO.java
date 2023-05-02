package jongjun.hairlog.data.dto.designer;

import java.math.BigInteger;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DesignerDeletedDTO {

	private final Long designerId;
	private final String designerName;
	private final String designerSalon;

	public DesignerDeletedDTO(BigInteger designerId, String designerName, String designerSalon) {
		this.designerId = Long.valueOf(designerId.toString());
		this.designerName = designerName;
		this.designerSalon = designerSalon;
	}
}
