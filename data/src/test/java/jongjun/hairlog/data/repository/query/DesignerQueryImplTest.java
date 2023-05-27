package jongjun.hairlog.data.repository.query;

import javax.persistence.EntityManager;
import jongjun.hairlog.data.DataRdsConfig;
import jongjun.hairlog.data.JpaDataSourceConfig;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.enums.MemberSex;
import jongjun.hairlog.data.repository.DesignerRepository;
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
class DesignerQueryImplTest {

	@Autowired EntityManager entityManager;
	@Autowired DesignerRepository repository;

	@Test
	@DisplayName("[DesignerQuery] searchByNameAndMemberIdQuery")
	void searchByNameAndMemberIdQuery() {
		repository.searchByNameAndMemberIdQuery("testdname", 1L);
	}

	@Test
	@DisplayName("[DesignerQuery] findAllByMemberIdQuery")
	void findAllByMemberIdQuery() {
		repository.findAllByMemberIdQuery(1L);
	}

	@Test
	@DisplayName("[DesignerQuery] findAllDeletedByMemberIdQuery")
	void findAllDeletedByMemberIdQuery() {
		MemberEntity member =
				MemberEntity.builder()
						.email("test2@test.com")
						.password("password")
						.name("name")
						.sex(MemberSex.M)
						.build();
		entityManager.merge(member);

		DesignerEntity designer =
				DesignerEntity.builder()
						.designerName("dn")
						.designerFav(true)
						.designerSalon("ds")
						.member(member)
						.build();
		repository.save(designer);

		repository.delete(designer);

		repository.findAllDeletedByMemberIdQuery(member.getId());
	}
}
