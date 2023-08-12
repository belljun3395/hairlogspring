package jongjun.hairlog.data.config.initializer;

import java.util.Date;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.entity.record.CommonRecordInfo;
import jongjun.hairlog.data.entity.record.CutEntity;
import jongjun.hairlog.data.entity.record.RecordEntity;
import jongjun.hairlog.data.enums.RecordCategory;
import jongjun.hairlog.data.enums.SatisfactionRate;
import jongjun.hairlog.data.repository.CutRepository;
import jongjun.hairlog.data.repository.DesignerRepository;
import jongjun.hairlog.data.repository.MemberRepository;
import jongjun.hairlog.data.repository.RecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@TestComponent
public class CutRecordInitializer {

	@Autowired private CutRepository repository;
	@Autowired private RecordRepository recordRepository;
	@Autowired private MemberRepository memberRepository;
	@Autowired private DesignerRepository designerRepository;
	@Autowired private MemberInitializer memberInitializer;
	@Autowired private DesignerInitializer designerInitializer;

	private CutEntity data;
	private RecordEntity record;
	private DesignerEntity designer;
	private MemberEntity member;

	@Transactional
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

	public CutEntity getData() {
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
		designerInitializer.initialize();
		designer = designerInitializer.getData();
		memberInitializer.initialize();
		member = memberInitializer.getData();
		this.data = repository.save(CutEntity.builder().id(1L).cutName("cutN").cutLength(1L).build());
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
								.recordCategory(RecordCategory.CUT)
								.subId(data.getId())
								.member(member)
								.designer(designer)
								.build());
	}
}
