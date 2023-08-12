package jongjun.hairlog.data.repository.initializer;

import java.util.Date;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.entity.record.CommonRecordInfo;
import jongjun.hairlog.data.entity.record.PermEntity;
import jongjun.hairlog.data.entity.record.RecordEntity;
import jongjun.hairlog.data.enums.HurtRate;
import jongjun.hairlog.data.enums.RecordCategory;
import jongjun.hairlog.data.enums.SatisfactionRate;
import jongjun.hairlog.data.repository.DesignerRepository;
import jongjun.hairlog.data.repository.MemberRepository;
import jongjun.hairlog.data.repository.PermRepository;
import jongjun.hairlog.data.repository.RecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PermRecordInitializer {
	@Autowired private PermRepository repository;
	@Autowired private RecordRepository recordRepository;
	@Autowired private MemberRepository memberRepository;
	@Autowired private DesignerRepository designerRepository;
	@Autowired private MemberInitializer memberInitializer;
	@Autowired private DesignerInitializer designerInitializer;

	private PermEntity data;
	private RecordEntity record;
	private MemberEntity member;
	private DesignerEntity designer;

	public void initialize() {
		log.info("=== initialize ===");
		repository.deleteAll();
		designerRepository.deleteAll();
		memberRepository.deleteAll();
		this.save();
	}

	public void initializePage() {
		repository.deleteAll();
		for (int i = 0; i < 5; i++) this.save();
	}

	public PermEntity getData() {
		return this.data;
	}

	public RecordEntity getRecord() {
		return this.record;
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
						PermEntity.builder()
								.id(1L)
								.permName("permName")
								.permTime(1L)
								.permHurt(HurtRate.H)
								.build());
		this.record =
				recordRepository.save(
						RecordEntity.builder()
								.id(1L)
								.recordInfo(
										CommonRecordInfo.builder()
												.recordDate(new Date())
												.recordCost(100L)
												.recordEtc("etc")
												.recordGrade(SatisfactionRate.H)
												.build())
								.recordCategory(RecordCategory.PERM)
								.subId(data.getId())
								.member(member)
								.designer(designer)
								.build());
	}
}
