package jongjun.hairlog.data.repository.query;

import java.util.Optional;
import jongjun.hairlog.data.dto.record.CutWitRecordView;
import jongjun.hairlog.data.dto.record.QCutWitRecordView;
import jongjun.hairlog.data.entity.record.CutEntity;
import jongjun.hairlog.data.entity.record.QCutEntity;
import jongjun.hairlog.data.entity.record.QRecordEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class CutCustomQueryImpl extends QuerydslRepositorySupport implements CutCustomQuery {
	public CutCustomQueryImpl() {
		super(CutEntity.class);
	}

	@Override
	public Optional<CutWitRecordView> findCutWithRecordByRecordIdAndDeletedFalse(Long recordId) {
		QCutEntity cutEntity = QCutEntity.cutEntity;
		QRecordEntity recordEntity = QRecordEntity.recordEntity;

		return Optional.ofNullable(
				from(cutEntity)
						.select(
								new QCutWitRecordView(
										recordEntity.id,
										recordEntity.recordInfo,
										recordEntity.recordCategory,
										recordEntity.subId,
										recordEntity.member,
										recordEntity.designer,
										cutEntity.cutName,
										cutEntity.cutLength))
						.join(recordEntity)
						.on(
								recordEntity.id.eq(recordId),
								recordEntity.deleted.isFalse(),
								cutEntity.id.eq(recordEntity.subId))
						.fetchJoin()
						.fetchOne());
	}
}
