package jongjun.hairlog.data.repository.initializer;

import java.util.Date;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.entity.record.DyeingEntity;
import jongjun.hairlog.data.enums.HurtRate;
import jongjun.hairlog.data.enums.SatisfactionRate;
import jongjun.hairlog.data.repository.DesignerRepository;
import jongjun.hairlog.data.repository.MemberRepository;
import jongjun.hairlog.data.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DyeingRecordInitializer {
	@Autowired private RecordRepository repository;
	@Autowired private MemberRepository memberRepository;
	@Autowired private DesignerRepository designerRepository;
	@Autowired private MemberInitializer memberInitializer;
	@Autowired private DesignerInitializer designerInitializer;

	private DyeingEntity data;
	private MemberEntity member;
	private DesignerEntity designer;

	public void initialize() {
		repository.deleteAll();
		this.save();
	}

	public void initializePage() {
		repository.deleteAll();
		designerRepository.deleteAll();
		memberRepository.deleteAll();
		for (int i = 0; i < 5; i++) this.save();
	}

	public DyeingEntity getData() {
		return this.data;
	}

	public MemberEntity getMember() {
		return this.member;
	}

	public DesignerEntity getDesigner() {
		return this.designer;
	}

	private void save() {
		memberInitializer.initialize();
		member = memberInitializer.getData();

		designerInitializer.initialize();
		designer = designerInitializer.getData();
		this.data =
				repository.save(
						DyeingEntity.builder()
								.id(1L)
								.recordDate(new Date())
								.recordCost(1L)
								.recordGrade(SatisfactionRate.H)
								.dyeingColor("dyeingColor")
								.dyeingDecolorization("decolorization")
								.dyeingTime(1L)
								.dyeingHurt(HurtRate.H)
								.member(member)
								.designer(designer)
								.build());
	}
}
