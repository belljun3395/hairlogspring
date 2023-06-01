package jongjun.hairlog.data.repository.query;

import javax.persistence.EntityManager;
import jongjun.hairlog.data.DataRdsConfig;
import jongjun.hairlog.data.JpaDataSourceConfig;
import jongjun.hairlog.data.entity.record.CutEntity;
import jongjun.hairlog.data.entity.record.DyeingEntity;
import jongjun.hairlog.data.entity.record.PermEntity;
import jongjun.hairlog.data.enums.RecordCategory;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@ActiveProfiles("test")
@Rollback
@Transactional
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class, DataRdsConfig.class, JpaDataSourceConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RecordQueryImplTest {

	@Autowired RecordRepository repository;
	@Autowired EntityManager entityManager;
	@Autowired CutRecordInitializer cutRecordInitializer;
	@Autowired PermRecordInitializer permRecordInitializer;
	@Autowired DyeingRecordInitializer dyeingRecordInitializer;

	@Test
	@DisplayName("[RecordQuery] findAllByMemberIdQuery")
	void findAllByMemberIdQuery() {
		cutRecordInitializer.initializePage();

		PageRequest page = PageRequest.of(0, 5);
		log.info("[RecordQuery] findAllByMemberIdQuery");
		repository.findAllByMemberIdQuery(page, 1L);
	}

	@Test
	@DisplayName("[RecordQuery] findAllByCategoryAndMemberIdQuery")
	void findAllByCategoryAndMemberIdQuery() {
		cutRecordInitializer.initializePage();
		Long memberId = cutRecordInitializer.getMember().getId();

		PageRequest page = PageRequest.of(0, 5);
		log.info("[RecordQuery] findAllByCategoryAndMemberIdQuery");
		repository.findAllByCategoryAndMemberIdQuery(page, RecordCategory.CUT, memberId);
	}

	@Test
	@DisplayName("[RecordQuery] findAllDeletedByMemberIdQuery")
	void findAllDeletedByMemberIdQuery() {
		cutRecordInitializer.initialize();
		CutEntity data = cutRecordInitializer.getData();
		Long memberId = Long.valueOf(cutRecordInitializer.getMember().getId().toString());
		repository.deleteById(data.getId());

		log.info("[RecordQuery] findAllDeletedByMemberIdQuery");
		repository.findAllDeletedByMemberIdQuery(memberId);
	}

	@Test
	@DisplayName("[RecordQuery] findByIdAndCategoryAndMemberId CUT")
	void findByIdAndCategoryAndMemberIdCut() {
		cutRecordInitializer.initialize();
		CutEntity data = cutRecordInitializer.getData();
		Long memberId = cutRecordInitializer.getMember().getId();

		log.info("[RecordQuery] findByIdAndCategoryAndMemberId CUT");
		repository.findByIdAndCategoryAndMemberId(data.getId(), RecordCategory.CUT, memberId);
	}

	@Test
	@DisplayName("[RecordQuery] findByIdAndCategoryAndMemberId PERM")
	void findByIdAndCategoryAndMemberIdPerm() {
		permRecordInitializer.initialize();
		PermEntity data = permRecordInitializer.getData();
		Long memberId = permRecordInitializer.getMember().getId();

		log.info("[RecordQuery] findByIdAndCategoryAndMemberId PERM");
		repository.findByIdAndCategoryAndMemberId(data.getId(), RecordCategory.PERM, memberId);
	}

	@Test
	@DisplayName("[RecordQuery] findByIdAndCategoryAndMemberId DYEING")
	void findByIdAndCategoryAndMemberIdDyeing() {
		dyeingRecordInitializer.initialize();
		DyeingEntity data = dyeingRecordInitializer.getData();
		Long memberId = dyeingRecordInitializer.getMember().getId();

		log.info("[RecordQuery] findByIdAndCategoryAndMemberId DYEING");
		repository.findByIdAndCategoryAndMemberId(data.getId(), RecordCategory.DYEING, memberId);
	}
}
