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
		String designerName = initializer.getDesigner().getDesignerName();
		Long memberId = initializer.getMember().getId();

		log.info("=== findAllByDesignerNameAndMember ===");
		List<DesignerView> designerViews =
				repository.findAllByDesignerNameAndMemberAndDeletedFalse(designerName, memberId);

		DesignerView designerView = designerViews.get(0);

		assertThat(designerViews).isNotEmpty();
		assertThat(designerView.getDesignerName()).isEqualTo(designerName);
	}

	@Test
	@DisplayName("[DesignerQuery] findAllByMemberIdAndDeletedFalse")
	void findAllByMemberIdQuery() {
		Long memberId = initializer.getMember().getId();
		DesignerEntity designer = initializer.getDesigner();

		log.info("=== findAllByMemberIdAndDeletedFalse ===");
		List<DesignerView> designerViews = repository.findAllByMemberIdAndDeletedFalse(memberId);

		DesignerView designerView = designerViews.get(0);

		assertThat(designerViews).isNotEmpty();
		assertThat(designerView.getDesignerName()).isEqualTo(designer.getDesignerName());
		assertThat(designerView.getDesignerId()).isEqualTo(designer.getId());
		assertThat(designerView.getDesignerSalon()).isEqualTo(designer.getDesignerSalon());
		assertThat(designerView.getDesignerFav()).isEqualTo(designer.getDesignerFav());
	}

	@Test
	@DisplayName("[DesignerQuery] findAllByMemberAndDeletedTrue")
	void findAllDeletedByMemberIdQuery() {
		MemberEntity member = initializer.getMember();
		DesignerEntity designer =
				repository.save(
						DesignerEntity.builder()
								.designerName("testD")
								.designerSalon("testS")
								.designerFav(true)
								.member(member)
								.build());

		log.info("=== delete for test ===");
		repository.delete(designer);
		entityManager.flush();

		log.info("=== findAllByMemberAndDeletedTrue ===");
		List<DeletedDesignerView> deletedDesignerViews =
				repository.findAllByMemberIdAndDeletedTrue(member.getId());

		DeletedDesignerView deletedDesignerView = deletedDesignerViews.get(0);

		assertThat(deletedDesignerViews).isNotEmpty();
		assertThat(deletedDesignerView.getDesignerName()).isEqualTo(designer.getDesignerName());
		assertThat(deletedDesignerView.getDesignerSalon()).isEqualTo(designer.getDesignerSalon());
	}
}
