package jongjun.hairlog.data.repository.initializer;

import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.repository.DesignerRepository;
import jongjun.hairlog.data.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DesignerInitializer {

	@Autowired private DesignerRepository repository;
	@Autowired private MemberRepository memberRepository;
	@Autowired private MemberInitializer memberInitializer;

	private DesignerEntity data;

	private MemberEntity member;

	public void initialize() {
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
