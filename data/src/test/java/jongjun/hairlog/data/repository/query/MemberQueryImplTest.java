package jongjun.hairlog.data.repository.query;

import jongjun.hairlog.data.DataRdsConfig;
import jongjun.hairlog.data.JpaDataSourceConfig;
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
@ContextConfiguration(classes = {TestConfig.class, DataRdsConfig.class, JpaDataSourceConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberQueryImplTest {

	@Autowired MemberRepository repository;
	@Autowired MemberInitializer initializer;

	@Test
	@DisplayName("[MemberQuery] findByIdQuery")
	void findByIdQuery() {
		initializer.initialize();
		Long memberId = initializer.getData().getId();

		log.info("[MemberQuery] findByIdQuery");
		repository.findByIdQuery(memberId);
	}

	@Test
	@DisplayName("[MemberQuery] findByEmailQuery")
	void findByEmailQuery() {
		initializer.initialize();
		String memberEmail = initializer.getData().getEmail();

		log.info("[MemberQuery] findByEmailQuery");
		repository.findByEmailQuery(memberEmail);
	}

	@Test
	@DisplayName("[MemberQuery] findDeletedMemberByEmailQuery")
	void findDeletedMemberByEmailQuery() {
		initializer.initialize();
		MemberEntity member = initializer.getData();
		repository.delete(member);

		log.info("[MemberQuery] findDeletedMemberByEmailQuery");
		repository.findDeletedMemberByEmailQuery(member.getEmail());
	}

	@Test
	@DisplayName("[MemberQuery] findByEmailAuthQuery")
	void findByEmailAuthQuery() {
		initializer.initialize();
		String memberEmail = initializer.getData().getEmail();

		log.info("[MemberQuery] findByEmailAuthQuery");
		repository.findByEmailAuthQuery(memberEmail);
	}
}
