package jongjun.hairlog.data.repository.query;

import javax.persistence.EntityManager;
import jongjun.hairlog.data.DataRdsConfig;
import jongjun.hairlog.data.config.EntityJpaDataSourceConfig;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.repository.MemberRepository;
import jongjun.hairlog.data.repository.TestConfig;
import jongjun.hairlog.data.repository.initializer.MemberInitializer;
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
class MemberQueryImplTest {

	@Autowired MemberRepository repository;
	@Autowired MemberInitializer initializer;

	@Test
	@DisplayName("[MemberQuery] findInfoViewByEmail")
	void findByEmailQuery() {
		initializer.initialize();
		String memberEmail = initializer.getData().getEmail();

		log.info("=== findInfoViewByEmail ===");
		repository.findTopInfoViewByEmailAndDeletedFalse(memberEmail);
	}

	@Test
	@DisplayName("[MemberQuery] findByEmailAndDeletedTrue")
	void findDeletedMemberByEmailQuery() {
		initializer.initialize();
		MemberEntity member = initializer.getData();
		repository.delete(member);

		log.info("==== findByEmailAndDeletedTrue =====");
		repository.findTopByEmailAndDeletedTrueOrderById(member.getEmail());
	}

	@Test
	@DisplayName("[MemberQuery] findAuthInfoViewByEmail")
	void findByEmailAuthQuery() {
		initializer.initialize();
		String memberEmail = initializer.getData().getEmail();

		log.info("=== findAuthInfoViewByEmail ===");
		repository.findTopAuthInfoViewByEmailAndDeletedFalse(memberEmail);
	}

	@Test
	@DisplayName("[MemberQuery] existsMemberEntitiesByEmailAndDeletedFalse")
	void isExistEmailQuery() {
		initializer.initialize();
		String memberEmail = initializer.getData().getEmail();

		log.info("=== existsMemberEntitiesByEmailAndDeletedFalse ===");
		repository.existsMemberEntitiesByEmailAndDeletedFalse(memberEmail);
	}
}
