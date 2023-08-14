package jongjun.hairlog.data.config.initializer;

import java.util.Date;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.entity.record.CommonRecordInfo;
import jongjun.hairlog.data.entity.record.DyeingEntity;
import jongjun.hairlog.data.entity.record.RecordEntity;
import jongjun.hairlog.data.enums.HurtRate;
import jongjun.hairlog.data.enums.RecordCategory;
import jongjun.hairlog.data.enums.SatisfactionRate;
import jongjun.hairlog.data.repository.DyeingRepository;
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
public class DyeingRecordInitializer implements ApplicationRunner {

	private final DyeingRepository repository;
	private final RecordRepository recordRepository;
	private final MemberInitializer memberInitializer;
	private final DesignerInitializer designerInitializer;

	private DyeingEntity dyeing;
	private RecordEntity record;
	private MemberEntity member;
	private DesignerEntity designer;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("=== initialize dyeing ===");
		this.initialize();
		log.info("*** dyeing id : {}, record id : {}", dyeing.getId(), record.getId());
		log.info("=== end initialize dyeing ===");
	}

	@Transactional
	public void initialize() {
		repository.deleteAll();
		this.save();
	}

	private void save() {
		designer = designerInitializer.getDesigner();
		member = memberInitializer.getMember();
		this.dyeing =
				repository.save(
						DyeingEntity.builder()
								.dyeingColor("dyeingColor")
								.dyeingDecolorization("decolorization")
								.dyeingTime(1L)
								.dyeingHurt(HurtRate.H)
								.build());
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
								.recordCategory(RecordCategory.DYEING)
								.subId(dyeing.getId())
								.member(member)
								.designer(designer)
								.build());
	}
}
