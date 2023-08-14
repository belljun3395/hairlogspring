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
import jongjun.hairlog.data.repository.RecordRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Getter
@RequiredArgsConstructor
public class CutRecordInitializer implements ApplicationRunner {

	private final CutRepository repository;
	private final RecordRepository recordRepository;
	private final MemberInitializer memberInitializer;
	private final DesignerInitializer designerInitializer;

	private CutEntity cut;
	private RecordEntity record;
	private DesignerEntity designer;
	private MemberEntity member;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("=== initialize cut ===");
		this.initialize();
		log.info("*** cut id : {}, record id : {}", cut.getId(), record.getId());
		log.info("=== end initialize cut ===");
	}

	@Transactional
	public void initialize() {
		repository.deleteAll();
		this.save();
	}

	private void save() {
		designer = designerInitializer.getDesigner();
		member = memberInitializer.getMember();
		this.cut = repository.save(CutEntity.builder().cutName("cutN").cutLength(1L).build());
		this.record =
				recordRepository.save(
						RecordEntity.builder()
								.recordInfo(
										CommonRecordInfo.builder()
												.recordDate(new Date())
												.recordCost(100L)
												.recordEtc("etc")
												.recordGrade(SatisfactionRate.H)
												.build())
								.recordCategory(RecordCategory.CUT)
								.subId(cut.getId())
								.member(member)
								.designer(designer)
								.build());
	}
}
