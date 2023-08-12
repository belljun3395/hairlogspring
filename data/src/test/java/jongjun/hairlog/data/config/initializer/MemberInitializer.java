package jongjun.hairlog.data.config.initializer;

import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.enums.MemberSex;
import jongjun.hairlog.data.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@TestComponent
public class MemberInitializer {

	@Autowired private MemberRepository repository;

	private MemberEntity data;

	@Transactional
	public void initialize() {
		log.info("=== initialize test ===");
		repository.deleteAll();
		this.save();
	}

	public MemberEntity getData() {
		return this.data;
	}

	private void save() {
		this.data =
				repository.save(
						MemberEntity.builder()
								.email("testM@test.com")
								.password("testP")
								.name("testMember")
								.sex(MemberSex.M)
								.cycle(1L)
								.build());
	}
}
