package jongjun.hairlog.data.config.initializer;

import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.enums.MemberSex;
import jongjun.hairlog.data.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Getter
@RequiredArgsConstructor
public class MemberInitializer implements ApplicationRunner {

	private final MemberRepository repository;

	private MemberEntity member;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("=== initialize member ===");
		this.initialize();
		log.info("*** member id : {} ***", member.getId());
		log.info("=== end initialize member ===");
	}

	@Transactional
	public void initialize() {
		repository.deleteAll();
		this.save();
	}

	private void save() {
		this.member =
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
