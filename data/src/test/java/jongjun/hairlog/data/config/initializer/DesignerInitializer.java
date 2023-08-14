package jongjun.hairlog.data.config.initializer;

import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.repository.DesignerRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Getter
@RequiredArgsConstructor
public class DesignerInitializer implements ApplicationRunner {

	private final DesignerRepository repository;
	private final MemberInitializer memberInitializer;

	private DesignerEntity designer;
	private MemberEntity member;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("=== initialize designer ===");
		this.initialize();
		log.info("*** designer id : {}", designer.getId());
		log.info("=== end initialize designer ===");
	}

	@Transactional
	public void initialize() {
		repository.deleteAll();
		this.save();
	}

	private void save() {
		member = memberInitializer.getMember();
		this.designer =
				repository.save(
						DesignerEntity.builder()
								.designerName("testD")
								.designerSalon("testS")
								.designerFav(true)
								.member(member)
								.build());
	}
}
