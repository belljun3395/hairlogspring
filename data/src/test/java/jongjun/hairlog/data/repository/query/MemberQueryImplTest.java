package jongjun.hairlog.data.repository.query;

import jongjun.hairlog.data.DataRdsConfig;
import jongjun.hairlog.data.JpaDataSourceConfig;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.enums.MemberSex;
import jongjun.hairlog.data.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
@ActiveProfiles("test")
@DataJpaTest
@ContextConfiguration(classes = {DataRdsConfig.class, JpaDataSourceConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberQueryImplTest {

	@Autowired MemberRepository repository;

	@Test
	@DisplayName("[MemberQuery] findByIdQuery")
	void findByIdQuery() {
		log.info("[MemberQuery] findByIdQuery");
		repository.findByIdQuery(1L);
	}

	@Test
	@DisplayName("[MemberQuery] findByEmailQuery")
	void findByEmailQuery() {
		log.info("[MemberQuery] findByEmailQuery");
		repository.findByEmailQuery("test@test.com");
	}

	@Test
	@DisplayName("[MemberQuery] findDeletedMemberByEmailQuery")
	void findDeletedMemberByEmailQuery() {
		MemberEntity member =
				MemberEntity.builder()
						.email("delete@delete.com")
						.password("password")
						.name("name")
						.sex(MemberSex.M)
						.build();
		repository.save(member);
		repository.delete(member);

		log.info("[MemberQuery] findDeletedMemberByEmailQuery");
		repository.findDeletedMemberByEmailQuery("delete@delete.com");
	}

	@Test
	@DisplayName("[MemberQuery] findByEmailAuthQuery")
	void findByEmailAuthQuery() {
		log.info("[MemberQuery] findByEmailAuthQuery");
		repository.findByEmailAuthQuery("test@test.com");
	}
}
