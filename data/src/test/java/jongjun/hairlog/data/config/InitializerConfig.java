package jongjun.hairlog.data.config;

import jongjun.hairlog.data.config.initializer.CutRecordInitializer;
import jongjun.hairlog.data.config.initializer.DesignerInitializer;
import jongjun.hairlog.data.config.initializer.DyeingRecordInitializer;
import jongjun.hairlog.data.config.initializer.MemberInitializer;
import jongjun.hairlog.data.config.initializer.PermRecordInitializer;
import jongjun.hairlog.data.repository.CutRepository;
import jongjun.hairlog.data.repository.DesignerRepository;
import jongjun.hairlog.data.repository.DyeingRepository;
import jongjun.hairlog.data.repository.MemberRepository;
import jongjun.hairlog.data.repository.PermRepository;
import jongjun.hairlog.data.repository.RecordRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class InitializerConfig {

	@Bean
	public MemberInitializer memberInitializer(MemberRepository memberRepository) {
		return new MemberInitializer(memberRepository);
	}

	@Bean
	public DesignerInitializer designerInitializer(
			DesignerRepository designerRepository, MemberInitializer memberInitializer) {
		return new DesignerInitializer(designerRepository, memberInitializer);
	}

	@Bean
	public CutRecordInitializer cutRecordInitializer(
			CutRepository cutRepository,
			RecordRepository recordRepository,
			MemberInitializer memberInitializer,
			DesignerInitializer designerInitializer) {
		return new CutRecordInitializer(
				cutRepository, recordRepository, memberInitializer, designerInitializer);
	}

	@Bean
	public DyeingRecordInitializer dyeingRecordInitializer(
			DyeingRepository dyeingRepository,
			RecordRepository recordRepository,
			MemberInitializer memberInitializer,
			DesignerInitializer designerInitializer) {
		return new DyeingRecordInitializer(
				dyeingRepository, recordRepository, memberInitializer, designerInitializer);
	}

	@Bean
	public PermRecordInitializer permRecordInitializer(
			PermRepository permRepository,
			RecordRepository recordRepository,
			MemberInitializer memberInitializer,
			DesignerInitializer designerInitializer) {
		return new PermRecordInitializer(
				permRepository, recordRepository, memberInitializer, designerInitializer);
	}
}
