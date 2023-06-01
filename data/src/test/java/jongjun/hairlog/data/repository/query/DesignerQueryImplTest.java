package jongjun.hairlog.data.repository.query;

import javax.persistence.EntityManager;
import jongjun.hairlog.data.DataRdsConfig;
import jongjun.hairlog.data.JpaDataSourceConfig;
import jongjun.hairlog.data.entity.DesignerEntity;
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
@ContextConfiguration(classes = {TestConfig.class, DataRdsConfig.class, JpaDataSourceConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DesignerQueryImplTest {

	@Autowired EntityManager entityManager;
	@Autowired DesignerRepository repository;
	@Autowired DesignerInitializer initializer;

	@Test
	@DisplayName("[DesignerQuery] searchByNameAndMemberIdQuery")
	void searchByNameAndMemberIdQuery() {
		initializer.initialize();
		String designerName = initializer.getData().getDesignerName();
		Long memberId = initializer.getMember().getId();

		log.info("[DesignerQuery] searchByNameAndMemberIdQuery");
		repository.searchByNameAndMemberIdQuery(designerName, memberId);
	}

	@Test
	@DisplayName("[DesignerQuery] findAllByMemberIdQuery")
	void findAllByMemberIdQuery() {
		initializer.initialize();
		Long memberId = initializer.getMember().getId();

		log.info("[DesignerQuery] findAllByMemberIdQuery");
		repository.findAllByMemberIdQuery(memberId);
	}

	@Test
	@DisplayName("[DesignerQuery] findAllDeletedByMemberIdQuery")
	void findAllDeletedByMemberIdQuery() {
		initializer.initialize();
		DesignerEntity data = initializer.getData();
		Long memberId = initializer.getMember().getId();

		repository.delete(data);

		log.info("[DesignerQuery] findAllDeletedByMemberIdQuery");
		repository.findAllDeletedByMemberIdQuery(memberId);
	}
}
