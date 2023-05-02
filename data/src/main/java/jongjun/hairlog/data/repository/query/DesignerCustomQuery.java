package jongjun.hairlog.data.repository.query;

import java.util.List;
import jongjun.hairlog.data.dto.designer.DesignerDeletedDTO;
import jongjun.hairlog.data.entity.DesignerEntity;

public interface DesignerCustomQuery {

	List<DesignerEntity> findByMemberIdQuery(Long memberId);

	List<DesignerEntity> searchByNameAndMemberIdQuery(String designerName, Long memberId);

	List<DesignerDeletedDTO> findDeletedDesignersByMemberIdQuery(Long memberId);
}
