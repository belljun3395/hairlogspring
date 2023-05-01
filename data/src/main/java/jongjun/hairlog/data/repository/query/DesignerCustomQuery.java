package jongjun.hairlog.data.repository.query;

import java.util.List;
import jongjun.hairlog.data.entity.DesignerEntity;

public interface DesignerCustomQuery {

	List<DesignerEntity> findByMemberIdQuery(Long memberId);
}
