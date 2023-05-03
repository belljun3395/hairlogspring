package jongjun.hairlog.data.repository.query;

import jongjun.hairlog.data.DataRdsConfig;
import jongjun.hairlog.data.JpaDataSourceConfig;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.enums.MemberSex;
import jongjun.hairlog.data.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@DataJpaTest
@ContextConfiguration(classes = {DataRdsConfig.class, JpaDataSourceConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberQueryImplTest {

	@Autowired MemberRepository repository;

	@Test
	@DisplayName("[MemberQuery] findByIdQuery")
	void findByIdQuery() {
		repository.findByIdQuery(1L);
	}

	@Test
	@DisplayName("[MemberQuery] findByEmailQuery")
	void findByEmailQuery() {
		repository.findByEmailQuery("test@test.com");
	}

	@Test
	@DisplayName("[MemberQuery] findDeletedMemberByEmailQuery")
	void findDeletedMemberByEmailQuery() {
		MemberEntity member =
				MemberEntity.builder().email("delete@delete.com").sex(MemberSex.M).build();
		repository.save(member);
		repository.delete(member);
		repository.findDeletedMemberByEmailQuery("delete@delete.com");
	}
}
