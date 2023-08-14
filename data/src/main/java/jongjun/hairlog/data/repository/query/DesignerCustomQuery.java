package jongjun.hairlog.data.repository.query;

import java.util.List;
import jongjun.hairlog.data.dto.designer.DeletedDesignerView;
import jongjun.hairlog.data.dto.designer.DesignerView;

public interface DesignerCustomQuery {

	List<DeletedDesignerView> findAllByMemberIdAndDeletedTrue(Long memberId);

	List<DesignerView> findAllByDesignerNameAndMemberAndDeletedFalse(
			String designerName, Long memberId);
}
