package jongjun.hairlog.data.repository.query;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import jongjun.hairlog.data.config.BaseQueryImplTest;
import jongjun.hairlog.data.config.initializer.MemberInitializer;
import jongjun.hairlog.data.dto.member.DeletedMemberView;
import jongjun.hairlog.data.dto.member.MemberAuthInfoView;
import jongjun.hairlog.data.dto.member.MemberInfoView;
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
		MemberInfoView memberInfoView =
				repository.findTopInfoViewByEmailAndDeletedFalse(memberEmail).orElse(null);

		assertThat(memberEmail).isEqualTo(memberInfoView.getEmail());
	}

	@Test
	@DisplayName("[MemberQuery] findByEmailAndDeletedTrue")
	void findDeletedMemberByEmailQuery() {
		initializer.initialize();
		MemberEntity member = initializer.getData();
		repository.delete(member);
		entityManager.flush();

		log.info("==== findByEmailAndDeletedTrue =====");
		DeletedMemberView deletedMemberView =
				repository.findTopByEmailAndDeletedTrueOrderById(member.getEmail()).orElse(null);

		assertThat(member.getEmail()).isEqualTo(deletedMemberView.getEmail());
	}

	@Test
	@DisplayName("[MemberQuery] findAuthInfoViewByEmail")
	void findByEmailAuthQuery() {
		initializer.initialize();
		String memberEmail = initializer.getData().getEmail();
		entityManager.flush();

		log.info("=== findAuthInfoViewByEmail ===");
		MemberAuthInfoView memberAuthInfoView =
				repository.findTopAuthInfoViewByEmailAndDeletedFalse(memberEmail).orElse(null);

		assertThat(memberEmail).isEqualTo(memberAuthInfoView.getEmail());
	}

	@Test
	@DisplayName("[MemberQuery] existsMemberEntitiesByEmailAndDeletedFalse")
	void isExistEmailQuery() {
		initializer.initialize();
		String memberEmail = initializer.getData().getEmail();
		entityManager.flush();

		log.info("=== existsMemberEntitiesByEmailAndDeletedFalse ===");
		Boolean isExist = repository.existsMemberEntitiesByEmailAndDeletedFalse(memberEmail);

		assertThat(isExist).isTrue();
	}
}
