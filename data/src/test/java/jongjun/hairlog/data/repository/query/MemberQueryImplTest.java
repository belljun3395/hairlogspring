package jongjun.hairlog.data.repository.query;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import jongjun.hairlog.data.config.BaseQueryImplTest;
import jongjun.hairlog.data.config.initializer.MemberInitializer;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.enums.MemberSex;
import jongjun.hairlog.data.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class MemberQueryImplTest extends BaseQueryImplTest {

	@Autowired MemberRepository repository;
	@Autowired EntityManager entityManager;
	@Autowired MemberInitializer initializer;

	@Test
	@DisplayName("[MemberQuery] findInfoViewByEmail")
	void findByEmailQuery() {
		String memberEmail = initializer.getMember().getEmail();

		log.info("=== findInfoViewByEmail ===");
		MemberEntity memberEntity =
				repository.findInfoViewByEmailAndDeletedFalse(memberEmail).orElse(null);

		assertThat(memberEmail).isEqualTo(memberEntity.getEmail());
	}

	@Test
	@DisplayName("[MemberQuery] findByEmailAndDeletedTrue")
	void findDeletedMemberByEmailQuery() {
		MemberEntity member =
				repository.save(
						MemberEntity.builder()
								.email("testM@test.com")
								.password("testP")
								.name("testMember")
								.sex(MemberSex.M)
								.cycle(1L)
								.build());

		repository.delete(member);
		entityManager.flush();

		log.info("==== findByEmailAndDeletedTrue =====");
		MemberEntity memberEntity =
				repository.findTopByEmailAndDeletedTrueOrderById(member.getEmail()).orElse(null);

		assertThat(member.getEmail()).isEqualTo(memberEntity.getEmail());
	}

	@Test
	@DisplayName("[MemberQuery] findAuthInfoViewByEmail")
	void findByEmailAuthQuery() {
		String memberEmail = initializer.getMember().getEmail();

		log.info("=== findAuthInfoViewByEmail ===");
		MemberEntity memberEntity =
				repository.findAuthInfoViewByEmailAndDeletedFalse(memberEmail).orElse(null);

		assertThat(memberEmail).isEqualTo(memberEntity.getEmail());
	}

	@Test
	@DisplayName("[MemberQuery] existsMemberEntitiesByEmailAndDeletedFalse")
	void isExistEmailQuery() {
		String memberEmail = initializer.getMember().getEmail();

		log.info("=== existsMemberEntitiesByEmailAndDeletedFalse ===");
		Boolean isExist = repository.existsMemberEntitiesByEmailAndDeletedFalse(memberEmail);

		assertThat(isExist).isTrue();
	}
}
