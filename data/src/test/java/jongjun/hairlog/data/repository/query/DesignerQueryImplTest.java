package jongjun.hairlog.data.repository.query;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import javax.persistence.EntityManager;
import jongjun.hairlog.data.config.BaseQueryImplTest;
import jongjun.hairlog.data.config.initializer.DesignerInitializer;
import jongjun.hairlog.data.dto.designer.DeletedDesignerView;
import jongjun.hairlog.data.dto.designer.DesignerView;
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
		List<DesignerView> designerViews =
				repository.findAllByDesignerNameAndMemberAndDeletedFalse(
						designerName, MemberEntity.builder().id(memberId).build());

		DesignerView designerView = designerViews.get(0);

		assertThat(designerViews).isNotEmpty();
		assertThat(designerView.getDesignerName()).isEqualTo(designerName);
	}

	@Test
	@DisplayName("[DesignerQuery] findAllByMemberIdAndDeletedFalse")
	void findAllByMemberIdQuery() {
		initializer.initialize();
		Long memberId = initializer.getMember().getId();
		DesignerEntity data = initializer.getData();
		entityManager.flush();

		log.info("=== findAllByMemberIdAndDeletedFalse ===");
		List<DesignerView> designerViews = repository.findAllByMemberIdAndDeletedFalse(memberId);

		DesignerView designerView = designerViews.get(0);

		assertThat(designerViews).isNotEmpty();
		assertThat(designerView.getDesignerName()).isEqualTo(data.getDesignerName());
		assertThat(designerView.getDesignerId()).isEqualTo(data.getId());
		assertThat(designerView.getDesignerSalon()).isEqualTo(data.getDesignerSalon());
		assertThat(designerView.getDesignerFav()).isEqualTo(data.getDesignerFav());
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
		List<DeletedDesignerView> deletedDesignerViews =
				repository.findAllByMemberAndDeletedTrue(MemberEntity.builder().id(memberId).build());

		DeletedDesignerView deletedDesignerView = deletedDesignerViews.get(0);

		assertThat(deletedDesignerViews).isNotEmpty();
		assertThat(deletedDesignerView.getDesignerName()).isEqualTo(data.getDesignerName());
		assertThat(deletedDesignerView.getDesignerSalon()).isEqualTo(data.getDesignerSalon());
	}
}
