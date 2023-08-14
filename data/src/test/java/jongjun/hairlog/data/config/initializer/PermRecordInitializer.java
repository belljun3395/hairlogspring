package jongjun.hairlog.data.config.initializer;

import java.util.Date;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.entity.record.CommonRecordInfo;
import jongjun.hairlog.data.entity.record.PermEntity;
import jongjun.hairlog.data.entity.record.RecordEntity;
import jongjun.hairlog.data.enums.HurtRate;
import jongjun.hairlog.data.enums.RecordCategory;
import jongjun.hairlog.data.enums.SatisfactionRate;
import jongjun.hairlog.data.repository.PermRepository;
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
public class PermRecordInitializer implements ApplicationRunner {
	private final PermRepository repository;
	private final RecordRepository recordRepository;
	private final MemberInitializer memberInitializer;
	private final DesignerInitializer designerInitializer;

	private PermEntity perm;
	private RecordEntity record;
	private MemberEntity member;
	private DesignerEntity designer;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("=== initialize perm ===");
		this.initialize();
		log.info("*** perm id : {}, record id : {}", perm.getId(), record.getId());
		log.info("=== end initialize perm ===");
	}

	@Transactional
	public void initialize() {
		repository.deleteAll();
		this.save();
	}

	public void initializePage() {
		repository.deleteAll();
		for (int i = 0; i < 5; i++) this.save();
	}

	private void save() {
		designer = designerInitializer.getDesigner();
		member = memberInitializer.getMember();
		this.perm =
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
								.subId(perm.getId())
								.member(member)
								.designer(designer)
								.build());
	}
}
