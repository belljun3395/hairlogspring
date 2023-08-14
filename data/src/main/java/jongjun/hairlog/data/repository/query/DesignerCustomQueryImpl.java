package jongjun.hairlog.data.repository.query;

import java.util.List;
import jongjun.hairlog.data.dto.designer.DeletedDesignerView;
import jongjun.hairlog.data.dto.designer.DesignerView;
import jongjun.hairlog.data.dto.designer.QDeletedDesignerView;
import jongjun.hairlog.data.dto.designer.QDesignerView;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.QDesignerEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class DesignerCustomQueryImpl extends QuerydslRepositorySupport
		implements DesignerCustomQuery {
	public DesignerCustomQueryImpl() {
		super(DesignerEntity.class);
	}

	@Override
	public List<DeletedDesignerView> findAllByMemberIdAndDeletedTrue(Long memberId) {
		QDesignerEntity designerEntity = QDesignerEntity.designerEntity;
		return from(designerEntity)
				.select(
						new QDeletedDesignerView(
								designerEntity.id, designerEntity.designerName, designerEntity.designerSalon))
				.where(designerEntity.member.id.eq(memberId), designerEntity.deleted.isTrue())
				.fetch();
	}

	@Override
	public List<DesignerView> findAllByDesignerNameAndMemberAndDeletedFalse(
			String designerName, Long memberId) {
		QDesignerEntity designerEntity = QDesignerEntity.designerEntity;
		return from(designerEntity)
				.select(
						new QDesignerView(
								designerEntity.id,
								designerEntity.designerName,
								designerEntity.designerSalon,
								designerEntity.designerFav))
				.where(
						designerEntity.member.id.eq(memberId),
						designerEntity.deleted.isFalse(),
						designerEntity.designerName.contains(designerName))
				.fetch();
	}
}
