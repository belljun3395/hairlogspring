package jongjun.hairlog.data.repository.query;

import javax.persistence.EntityManager;
import jongjun.hairlog.data.DataRdsConfig;
import jongjun.hairlog.data.JpaDataSourceConfig;
import org.hibernate.cfg.NotYetImplementedException;
import org.junit.jupiter.api.BeforeEach;
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
class CustomQueryImplTest {

	static Long count = 1L;

	@Autowired EntityManager em;

	@BeforeEach
	public void setData() {
		//		데이터 초기화 예시
		if (count != 1L) {
			return;
		}
		count++;
		throw new NotYetImplementedException();
	}

	@Test
	@DisplayName("체크할 쿼리")
	void checkQuery() {
		throw new NotYetImplementedException();
	}
}
