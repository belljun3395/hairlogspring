package jongjun.hairlog.data.repository.query;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import jongjun.hairlog.data.config.BaseQueryImplTest;
import jongjun.hairlog.data.config.initializer.CutRecordInitializer;
import jongjun.hairlog.data.config.initializer.DyeingRecordInitializer;
import jongjun.hairlog.data.config.initializer.PermRecordInitializer;
import jongjun.hairlog.data.dto.record.CutWitRecordView;
import jongjun.hairlog.data.dto.record.DyeingWithRecordView;
import jongjun.hairlog.data.dto.record.PermWithRecordView;
import jongjun.hairlog.data.entity.record.RecordEntity;
import jongjun.hairlog.data.repository.CutRepository;
import jongjun.hairlog.data.repository.DyeingRepository;
import jongjun.hairlog.data.repository.PermRepository;
import jongjun.hairlog.data.repository.RecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class RecordQueryImplTest extends BaseQueryImplTest {

	@Autowired RecordRepository repository;
	@Autowired EntityManager entityManager;
	@Autowired CutRecordInitializer cutRecordInitializer;
	@Autowired PermRecordInitializer permRecordInitializer;
	@Autowired DyeingRecordInitializer dyeingRecordInitializer;
	@Autowired CutRepository cutRepository;
	@Autowired PermRepository permRepository;
	@Autowired DyeingRepository dyeingRepository;

	@Test
	@DisplayName("[Record] save - cut, perm, dyeing")
	void save() {
		cutRecordInitializer.initialize();
		permRecordInitializer.initialize();
		dyeingRecordInitializer.initialize();
	}

	@Test
	@DisplayName("[Record] findCutWithRecordByRecordIdAndDeletedFalse")
	void findCutWithRecordByRecordIdAndDeletedFalse() {
		log.info("=== cut ===");
		cutRecordInitializer.initialize();
		RecordEntity cutRecord = cutRecordInitializer.getRecord();
		entityManager.flush();

		log.info("=== cut findCutWithRecordByRecordIdAndDeletedFalse ===");
		CutWitRecordView cutWitRecordView =
				cutRepository.findCutWithRecordByRecordIdAndDeletedFalse(cutRecord.getId()).orElse(null);

		assertThat(cutWitRecordView.getId()).isEqualTo(cutRecord.getId());

		log.info("=== perm ===");
		permRecordInitializer.initialize();
		RecordEntity permRecord = permRecordInitializer.getRecord();
		entityManager.flush();

		log.info("=== perm findCutWithRecordByRecordIdAndDeletedFalse ===");
		PermWithRecordView permWithRecordView =
				permRepository.findCutWithRecordByRecordIdAndDeletedFalse(permRecord.getId()).orElse(null);

		assertThat(permWithRecordView.getId()).isEqualTo(permRecord.getId());

		log.info("=== dyeing ===");
		dyeingRecordInitializer.initialize();
		RecordEntity dyeingRecord = dyeingRecordInitializer.getRecord();
		entityManager.flush();

		log.info("=== dyeing findCutWithRecordByRecordIdAndDeletedFalse ===");
		DyeingWithRecordView dyeingWithRecordView =
				dyeingRepository
						.findCutWithRecordByRecordIdAndDeletedFalse(dyeingRecord.getId())
						.orElse(null);

		assertThat(dyeingWithRecordView.getId()).isEqualTo(dyeingRecord.getId());
	}
}
