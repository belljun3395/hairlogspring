package jongjun.hairlog.data.repository.query;

import java.util.Optional;
import jongjun.hairlog.data.dto.record.PermWithRecordView;
import jongjun.hairlog.data.dto.record.QPermWithRecordView;
import jongjun.hairlog.data.entity.record.PermEntity;
import jongjun.hairlog.data.entity.record.QPermEntity;
import jongjun.hairlog.data.entity.record.QRecordEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class PermCustomQueryImpl extends QuerydslRepositorySupport implements PermCustomQuery {
	public PermCustomQueryImpl() {
		super(PermEntity.class);
	}

	@Override
	public Optional<PermWithRecordView> findCutWithRecordByRecordIdAndDeletedFalse(Long recordId) {
		QPermEntity permEntity = QPermEntity.permEntity;
		QRecordEntity recordEntity = QRecordEntity.recordEntity;

		return Optional.ofNullable(
				from(permEntity)
						.select(
								new QPermWithRecordView(
										recordEntity.id,
										recordEntity.recordInfo,
										recordEntity.recordCategory,
										recordEntity.subId,
										recordEntity.member,
										recordEntity.designer,
										permEntity.permName,
										permEntity.permTime,
										permEntity.permHurt))
						.join(recordEntity)
						.on(
								recordEntity.id.eq(recordId),
								recordEntity.deleted.isFalse(),
								permEntity.id.eq(recordEntity.subId))
						.fetchJoin()
						.fetchOne());
	}
}
