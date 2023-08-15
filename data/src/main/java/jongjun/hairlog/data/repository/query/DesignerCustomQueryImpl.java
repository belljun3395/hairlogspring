package jongjun.hairlog.data.repository.query;

import java.util.List;
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
	public List<DesignerEntity> findAllByMemberIdAndDeletedTrue(Long memberId) {
		QDesignerEntity designerEntity = QDesignerEntity.designerEntity;
		return from(designerEntity)
				.where(designerEntity.member.id.eq(memberId), designerEntity.deleted.isTrue())
				.fetch();
	}

	@Override
	public List<DesignerEntity> findAllByDesignerNameAndMemberAndDeletedFalse(
			String designerName, Long memberId) {
		QDesignerEntity designerEntity = QDesignerEntity.designerEntity;
		return from(designerEntity)
				.where(
						designerEntity.member.id.eq(memberId),
						designerEntity.deleted.isFalse(),
						designerEntity.designerName.contains(designerName))
				.fetch();
	}
}
