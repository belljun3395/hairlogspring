package jongjun.hairlog.data.repository.query;

import javax.persistence.EntityManager;
import jongjun.hairlog.data.config.BaseQueryImplTest;
import jongjun.hairlog.data.config.initializer.MemberInitializer;
import jongjun.hairlog.data.entity.MemberEntity;
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
		initializer.initialize();
		String memberEmail = initializer.getData().getEmail();
		entityManager.flush();

		log.info("=== findInfoViewByEmail ===");
		repository.findTopInfoViewByEmailAndDeletedFalse(memberEmail);
	}

	@Test
	@DisplayName("[MemberQuery] findByEmailAndDeletedTrue")
	void findDeletedMemberByEmailQuery() {
		initializer.initialize();
		MemberEntity member = initializer.getData();
		repository.delete(member);
		entityManager.flush();

		log.info("==== findByEmailAndDeletedTrue =====");
		repository.findTopByEmailAndDeletedTrueOrderById(member.getEmail());
	}

	@Test
	@DisplayName("[MemberQuery] findAuthInfoViewByEmail")
	void findByEmailAuthQuery() {
		initializer.initialize();
		String memberEmail = initializer.getData().getEmail();
		entityManager.flush();

		log.info("=== findAuthInfoViewByEmail ===");
		repository.findTopAuthInfoViewByEmailAndDeletedFalse(memberEmail);
	}

	@Test
	@DisplayName("[MemberQuery] existsMemberEntitiesByEmailAndDeletedFalse")
	void isExistEmailQuery() {
		initializer.initialize();
		String memberEmail = initializer.getData().getEmail();
		entityManager.flush();

		log.info("=== existsMemberEntitiesByEmailAndDeletedFalse ===");
		repository.existsMemberEntitiesByEmailAndDeletedFalse(memberEmail);
	}
}
