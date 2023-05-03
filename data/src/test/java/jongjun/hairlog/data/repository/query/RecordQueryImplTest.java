package jongjun.hairlog.data.repository.query;

import javax.persistence.EntityManager;
import jongjun.hairlog.data.DataRdsConfig;
import jongjun.hairlog.data.JpaDataSourceConfig;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.entity.record.CutEntity;
import jongjun.hairlog.data.enums.RecordCategory;
import jongjun.hairlog.data.repository.RecordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@DataJpaTest
@ContextConfiguration(classes = {DataRdsConfig.class, JpaDataSourceConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RecordQueryImplTest {

	@Autowired RecordRepository repository;

	@Autowired EntityManager entityManager;

	@Test
	@DisplayName("[RecordQuery] findAllByMemberIdQuery")
	void findAllByMemberIdQuery() {
		PageRequest page = PageRequest.of(0, 5);
		repository.findAllByMemberIdQuery(page, 1L);
	}

	@Test
	@DisplayName("[RecordQuery] findAllByCategoryAndMemberIdQuery")
	void findAllByCategoryAndMemberIdQuery() {
		PageRequest page = PageRequest.of(0, 5);
		repository.findAllByCategoryAndMemberIdQuery(page, RecordCategory.CUT, 1L);
	}

	@Test
	@DisplayName("[RecordQuery] findAllByCategoryAndMemberIdQuery")
	void findAllDeletedByMemberIdQuery() {
		MemberEntity member = MemberEntity.builder().build();
		entityManager.merge(member);
		DesignerEntity designer = DesignerEntity.builder().build();
		entityManager.merge(designer);
		CutEntity cut = CutEntity.builder().member(member).designer(designer).build();
		repository.save(cut);
		repository.delete(cut);
		repository.findAllDeletedByMemberIdQuery(member.getId());
	}

	@Test
	@DisplayName("[RecordQuery] findByIdAndCategoryAndMemberId CUT")
	void findByIdAndCategoryAndMemberIdCut() {
		repository.findByIdAndCategoryAndMemberId(1L, RecordCategory.CUT, 1L);
		repository.findByIdAndCategoryAndMemberId(2L, RecordCategory.PERM, 1L);
		repository.findByIdAndCategoryAndMemberId(3L, RecordCategory.DYEING, 1L);
	}

	@Test
	@DisplayName("[RecordQuery] findByIdAndCategoryAndMemberId PERM")
	void findByIdAndCategoryAndMemberIdPerm() {
		repository.findByIdAndCategoryAndMemberId(2L, RecordCategory.PERM, 1L);
	}

	@Test
	@DisplayName("[RecordQuery] findByIdAndCategoryAndMemberId DYEING")
	void findByIdAndCategoryAndMemberIdDyeing() {
		repository.findByIdAndCategoryAndMemberId(3L, RecordCategory.DYEING, 1L);
	}
}
