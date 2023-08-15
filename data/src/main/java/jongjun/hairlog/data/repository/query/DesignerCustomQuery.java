package jongjun.hairlog.data.repository.query;

import java.util.List;
import jongjun.hairlog.data.entity.DesignerEntity;

public interface DesignerCustomQuery {

	List<DesignerEntity> findAllByMemberIdAndDeletedTrue(Long memberId);

	List<DesignerEntity> findAllByDesignerNameAndMemberAndDeletedFalse(
			String designerName, Long memberId);
}
