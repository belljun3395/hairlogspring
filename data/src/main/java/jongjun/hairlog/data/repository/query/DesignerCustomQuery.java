package jongjun.hairlog.data.repository.query;

import java.util.List;
import jongjun.hairlog.data.dto.designer.DesignerDTO;
import jongjun.hairlog.data.dto.designer.DesignerDeletedDTO;

public interface DesignerCustomQuery {

	List<DesignerDTO> findAllByMemberIdQuery(Long memberId);

	List<DesignerDTO> searchByNameAndMemberIdQuery(String designerName, Long memberId);

	List<DesignerDeletedDTO> findAllDeletedByMemberIdQuery(Long memberId);
}
