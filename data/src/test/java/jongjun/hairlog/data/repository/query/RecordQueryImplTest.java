package jongjun.hairlog.data.repository.query;

import javax.persistence.EntityManager;
import jongjun.hairlog.data.DataRdsConfig;
import jongjun.hairlog.data.config.EntityJpaDataSourceConfig;
import jongjun.hairlog.data.entity.record.RecordEntity;
import jongjun.hairlog.data.repository.CutRepository;
import jongjun.hairlog.data.repository.DyeingRepository;
import jongjun.hairlog.data.repository.PermRepository;
import jongjun.hairlog.data.repository.RecordRepository;
import jongjun.hairlog.data.repository.TestConfig;
import jongjun.hairlog.data.repository.initializer.CutRecordInitializer;
import jongjun.hairlog.data.repository.initializer.DyeingRecordInitializer;
import jongjun.hairlog.data.repository.initializer.PermRecordInitializer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@ActiveProfiles("test")
@Rollback
@Transactional
@SpringBootTest
@ContextConfiguration(
		classes = {TestConfig.class, DataRdsConfig.class, EntityJpaDataSourceConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RecordQueryImplTest {

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
		cutRepository.findCutWithRecordByRecordIdAndDeletedFalse(cutRecord.getId()).orElse(null);

		log.info("=== perm ===");
		permRecordInitializer.initialize();
		RecordEntity permRecord = permRecordInitializer.getRecord();
		entityManager.flush();

		log.info("=== perm findCutWithRecordByRecordIdAndDeletedFalse ===");
		permRepository.findCutWithRecordByRecordIdAndDeletedFalse(permRecord.getId()).orElse(null);

		log.info("=== dyeing ===");
		dyeingRecordInitializer.initialize();
		RecordEntity dyeingRecord = dyeingRecordInitializer.getRecord();
		entityManager.flush();

		log.info("=== dyeing findCutWithRecordByRecordIdAndDeletedFalse ===");
		dyeingRepository.findCutWithRecordByRecordIdAndDeletedFalse(dyeingRecord.getId()).orElse(null);
	}
}
