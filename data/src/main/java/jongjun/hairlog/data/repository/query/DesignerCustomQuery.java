package jongjun.hairlog.data.repository.query;

import java.util.List;
import jongjun.hairlog.data.dto.designer.DesignerDeletedDTO;
import jongjun.hairlog.data.entity.DesignerEntity;

public interface DesignerCustomQuery {

	List<DesignerEntity> findAllByMemberIdQuery(Long memberId);

	List<DesignerEntity> searchByNameAndMemberIdQuery(String designerName, Long memberId);

	List<DesignerDeletedDTO> findAllDeletedByMemberIdQuery(Long memberId);
}
