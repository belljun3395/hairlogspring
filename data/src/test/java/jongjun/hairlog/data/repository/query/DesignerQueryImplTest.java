package jongjun.hairlog.data.repository.query;

import javax.persistence.EntityManager;
import jongjun.hairlog.data.DataRdsConfig;
import jongjun.hairlog.data.config.EntityJpaDataSourceConfig;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.repository.DesignerRepository;
import jongjun.hairlog.data.repository.TestConfig;
import jongjun.hairlog.data.repository.initializer.DesignerInitializer;
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
class DesignerQueryImplTest {

	@Autowired EntityManager entityManager;
	@Autowired DesignerRepository repository;
	@Autowired DesignerInitializer initializer;

	@Test
	@DisplayName("[DesignerQuery] findAllByDesignerNameAndMemberAndDeletedFalse")
	void searchByNameAndMemberIdQuery() {
		initializer.initialize();
		String designerName = initializer.getData().getDesignerName();
		Long memberId = initializer.getMember().getId();
		entityManager.flush();

		log.info("=== findAllByDesignerNameAndMember ===");
		repository.findAllByDesignerNameAndMemberAndDeletedFalse(
				designerName, MemberEntity.builder().id(memberId).build());
	}

	@Test
	@DisplayName("[DesignerQuery] findAllByMemberIdAndDeletedFalse")
	void findAllByMemberIdQuery() {
		initializer.initialize();
		Long memberId = initializer.getMember().getId();
		entityManager.flush();

		log.info("=== findAllByMemberId ===");
		repository.findAllByMemberIdAndDeletedFalse(memberId);
	}

	@Test
	@DisplayName("[DesignerQuery] findAllByMemberAndDeletedTrue")
	void findAllDeletedByMemberIdQuery() {
		initializer.initialize();
		DesignerEntity data = initializer.getData();
		Long memberId = initializer.getMember().getId();
		entityManager.flush();

		log.info("=== delete for test ===");
		repository.delete(data);
		entityManager.flush();

		log.info("=== findAllByMemberAndDeletedTrue ===");
		repository.findAllByMemberAndDeletedTrue(MemberEntity.builder().id(memberId).build());
	}
}
