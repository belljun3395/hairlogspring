package jongjun.hairlog.data.config.initializer;

import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.repository.DesignerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@TestComponent
public class DesignerInitializer {

	@Autowired private DesignerRepository repository;
	@Autowired private MemberInitializer memberInitializer;

	private DesignerEntity data;

	private MemberEntity member;

	@Transactional
	public void initialize() {
		log.info("=== initialize test ===");
		repository.deleteAll();
		this.save();
	}

	public DesignerEntity getData() {
		return this.data;
	}

	public MemberEntity getMember() {
		return this.member;
	}

	private void save() {
		memberInitializer.initialize();
		member = memberInitializer.getData();
		this.data =
				repository.save(
						DesignerEntity.builder()
								.id(1L)
								.designerName("testD")
								.designerSalon("testS")
								.designerFav(true)
								.member(member)
								.build());
	}
}
