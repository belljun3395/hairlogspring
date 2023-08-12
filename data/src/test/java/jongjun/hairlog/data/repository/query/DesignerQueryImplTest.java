package jongjun.hairlog.data.repository.query;

import javax.persistence.EntityManager;
import jongjun.hairlog.data.config.BaseQueryImplTest;
import jongjun.hairlog.data.config.initializer.DesignerInitializer;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.repository.DesignerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
class DesignerQueryImplTest extends BaseQueryImplTest {

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
