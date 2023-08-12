package jongjun.hairlog.data.repository.query;

import java.util.Optional;
import jongjun.hairlog.data.dto.record.DyeingWithRecordView;
import jongjun.hairlog.data.dto.record.QDyeingWithRecordView;
import jongjun.hairlog.data.entity.record.DyeingEntity;
import jongjun.hairlog.data.entity.record.QDyeingEntity;
import jongjun.hairlog.data.entity.record.QRecordEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class DyeingCustomQueryImpl extends QuerydslRepositorySupport implements DyeingCustomQuery {
	public DyeingCustomQueryImpl() {
		super(DyeingEntity.class);
	}

	@Override
	public Optional<DyeingWithRecordView> findCutWithRecordByRecordIdAndDeletedFalse(Long recordId) {
		QDyeingEntity dyeingEntity = QDyeingEntity.dyeingEntity;
		QRecordEntity recordEntity = QRecordEntity.recordEntity;

		return Optional.ofNullable(
				from(dyeingEntity)
						.select(
								new QDyeingWithRecordView(
										recordEntity.id,
										recordEntity.recordInfo,
										recordEntity.recordCategory,
										recordEntity.subId,
										recordEntity.member,
										recordEntity.designer,
										dyeingEntity.dyeingColor,
										dyeingEntity.dyeingDecolorization,
										dyeingEntity.dyeingTime,
										dyeingEntity.dyeingHurt))
						.join(recordEntity)
						.on(
								recordEntity.id.eq(recordId),
								recordEntity.deleted.isFalse(),
								dyeingEntity.id.eq(recordEntity.subId))
						.fetchJoin()
						.fetchOne());
	}
}
