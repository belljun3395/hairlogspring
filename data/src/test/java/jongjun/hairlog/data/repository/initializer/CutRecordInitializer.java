package jongjun.hairlog.data.repository.initializer;

import java.util.Date;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.entity.record.CutEntity;
import jongjun.hairlog.data.enums.SatisfactionRate;
import jongjun.hairlog.data.repository.DesignerRepository;
import jongjun.hairlog.data.repository.MemberRepository;
import jongjun.hairlog.data.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CutRecordInitializer {

	@Autowired private RecordRepository repository;
	@Autowired private MemberRepository memberRepository;
	@Autowired private DesignerRepository designerRepository;
	@Autowired private MemberInitializer memberInitializer;
	@Autowired private DesignerInitializer designerInitializer;

	private CutEntity data;
	private DesignerEntity designer;
	private MemberEntity member;

	public void initialize() {
		repository.deleteAll();
		designerRepository.deleteAll();
		memberRepository.deleteAll();
		this.save();
	}

	public void initializePage() {
		repository.deleteAll();
		for (int i = 0; i < 5; i++) this.save();
	}

	public CutEntity getData() {
		return this.data;
	}

	public MemberEntity getMember() {
		return this.member;
	}

	public DesignerEntity getDesigner() {
		return this.designer;
	}

	private void save() {
		designerInitializer.initialize();
		designer = designerInitializer.getData();
		memberInitializer.initialize();
		member = memberInitializer.getData();
		this.data =
				repository.save(
						CutEntity.builder()
								.id(1L)
								.recordDate(new Date())
								.recordCost(1L)
								.recordGrade(SatisfactionRate.H)
								.cutName("cutN")
								.cutLength(1L)
								.member(member)
								.designer(designer)
								.build());
	}
}
