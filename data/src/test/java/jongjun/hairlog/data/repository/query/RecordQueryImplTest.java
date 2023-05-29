package jongjun.hairlog.data.repository.query;

import java.util.Date;
import javax.persistence.EntityManager;
import jongjun.hairlog.data.DataRdsConfig;
import jongjun.hairlog.data.JpaDataSourceConfig;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.entity.record.CutEntity;
import jongjun.hairlog.data.enums.MemberSex;
import jongjun.hairlog.data.enums.RecordCategory;
import jongjun.hairlog.data.enums.SatisfactionRate;
import jongjun.hairlog.data.repository.RecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@Slf4j
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
		log.info("[RecordQuery] findAllByMemberIdQuery");
		repository.findAllByMemberIdQuery(page, 1L);
	}

	@Test
	@DisplayName("[RecordQuery] findAllByCategoryAndMemberIdQuery")
	void findAllByCategoryAndMemberIdQuery() {
		PageRequest page = PageRequest.of(0, 5);
		log.info("[RecordQuery] findAllByCategoryAndMemberIdQuery");
		repository.findAllByCategoryAndMemberIdQuery(page, RecordCategory.CUT, 1L);
	}

	@Test
	@DisplayName("[RecordQuery] findAllByCategoryAndMemberIdQuery")
	void findAllDeletedByMemberIdQuery() {
		MemberEntity member =
				MemberEntity.builder()
						.email("test2@test.com")
						.password("password")
						.name("name")
						.sex(MemberSex.M)
						.build();
		entityManager.merge(member);
		DesignerEntity designer = DesignerEntity.builder().designerName("name").build();
		entityManager.merge(designer);
		CutEntity cut =
				CutEntity.builder()
						.recordCost(1L)
						.recordDate(new Date())
						.recordGrade(SatisfactionRate.H)
						.cutLength(1L)
						.cutName("name")
						.member(member)
						.designer(designer)
						.build();
		repository.save(cut);
		repository.delete(cut);

		log.info("[RecordQuery] findAllByCategoryAndMemberIdQuery");
		repository.findAllDeletedByMemberIdQuery(member.getId());
	}

	@Test
	@DisplayName("[RecordQuery] findByIdAndCategoryAndMemberId CUT")
	void findByIdAndCategoryAndMemberIdCut() {
		log.info("[RecordQuery] findByIdAndCategoryAndMemberId CUT");
		repository.findByIdAndCategoryAndMemberId(1L, RecordCategory.CUT, 1L);
	}

	@Test
	@DisplayName("[RecordQuery] findByIdAndCategoryAndMemberId PERM")
	void findByIdAndCategoryAndMemberIdPerm() {
		log.info("[RecordQuery] findByIdAndCategoryAndMemberId PERM");
		repository.findByIdAndCategoryAndMemberId(2L, RecordCategory.PERM, 1L);
	}

	@Test
	@DisplayName("[RecordQuery] findByIdAndCategoryAndMemberId DYEING")
	void findByIdAndCategoryAndMemberIdDyeing() {
		log.info("[RecordQuery] findByIdAndCategoryAndMemberId DYEING");
		repository.findByIdAndCategoryAndMemberId(3L, RecordCategory.DYEING, 1L);
	}
}
