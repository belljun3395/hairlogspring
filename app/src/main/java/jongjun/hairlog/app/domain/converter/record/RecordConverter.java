package jongjun.hairlog.app.domain.converter.record;

import java.util.ArrayList;
import java.util.List;
import jongjun.hairlog.app.config.security.AuditorHolder;
import jongjun.hairlog.app.domain.model.record.CutRecord;
import jongjun.hairlog.app.domain.model.record.DyeingRecord;
import jongjun.hairlog.app.domain.model.record.PermRecord;
import jongjun.hairlog.app.domain.model.record.Record;
import jongjun.hairlog.app.domain.model.record.RecordIndex;
import jongjun.hairlog.app.support.Page;
import jongjun.hairlog.app.web.controller.request.record.CutRecordRequest;
import jongjun.hairlog.app.web.controller.request.record.DyeingRecordRequest;
import jongjun.hairlog.app.web.controller.request.record.PermRecordRequest;
import jongjun.hairlog.app.web.controller.request.record.RecordRequest;
import jongjun.hairlog.data.dto.record.CutWitRecordView;
import jongjun.hairlog.data.dto.record.DyeingWithRecordView;
import jongjun.hairlog.data.dto.record.PermWithRecordView;
import jongjun.hairlog.data.dto.record.RecordIdView;
import jongjun.hairlog.data.dto.record.RecordView;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.entity.record.CommonRecordInfo;
import jongjun.hairlog.data.entity.record.CutEntity;
import jongjun.hairlog.data.entity.record.DyeingEntity;
import jongjun.hairlog.data.entity.record.PermEntity;
import jongjun.hairlog.data.entity.record.RecordEntity;
import jongjun.hairlog.data.enums.RecordCategory;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

@Component
public class RecordConverter {

	public Page<RecordIndex> from(org.springframework.data.domain.Page<RecordIdView> source) {
		List<RecordIndex> domain = convertToDomain(source);
		return new Page(convertToPageSource(source, domain));
	}

	private PageImpl<RecordIndex> convertToPageSource(
			org.springframework.data.domain.Page<RecordIdView> source, List<RecordIndex> domain) {
		PageImpl<RecordIndex> convertedPage =
				new PageImpl<>(domain, source.getPageable(), source.getTotalElements());
		return convertedPage;
	}

	private List<RecordIndex> convertToDomain(
			org.springframework.data.domain.Page<RecordIdView> source) {
		List<RecordIdView> contents = source.getContent();
		List<RecordIndex> list = new ArrayList<>();
		for (RecordIdView rid : contents) {
			list.add(convertToSource(rid));
		}
		return list;
	}

	private RecordIndex convertToSource(RecordIdView rid) {
		RecordIndex ri =
				RecordIndex.builder()
						.recordId(rid.getId())
						.recordDate(rid.getRecordInfo().getRecordDate())
						.recordCategory(rid.getRecordCategory())
						.build();
		return ri;
	}

	public Record from(RecordView source, RecordCategory category) {
		return convertRecord(source, category);
	}

	private Record convertRecord(RecordView source, RecordCategory category) {
		switch (category) {
			case CUT:
				CutWitRecordView cut = (CutWitRecordView) source;
				return CutRecord.builder()
						.recordDate(cut.getRecordInfo().getRecordDate())
						.recordCost(cut.getRecordInfo().getRecordCost())
						.recordGrade(cut.getRecordInfo().getRecordGrade())
						.recordEtc(cut.getRecordInfo().getRecordEtc())
						.recordGrade(cut.getRecordInfo().getRecordGrade())
						.designer(cut.getDesigner().getId())
						.cutName(cut.getCutName())
						.cutLength(cut.getCutLength())
						.build();
			case PERM:
				PermWithRecordView perm = (PermWithRecordView) source;
				return PermRecord.builder()
						.recordDate(perm.getRecordInfo().getRecordDate())
						.recordCost(perm.getRecordInfo().getRecordCost())
						.recordGrade(perm.getRecordInfo().getRecordGrade())
						.recordEtc(perm.getRecordInfo().getRecordEtc())
						.recordGrade(perm.getRecordInfo().getRecordGrade())
						.designer(perm.getDesigner().getId())
						.permName(perm.getPermName())
						.permTime(perm.getPermTime())
						.permHurt(perm.getPermHurt())
						.build();
			case DYEING:
				DyeingWithRecordView dyeing = (DyeingWithRecordView) source;
				return DyeingRecord.builder()
						.recordDate(dyeing.getRecordInfo().getRecordDate())
						.recordCost(dyeing.getRecordInfo().getRecordCost())
						.recordGrade(dyeing.getRecordInfo().getRecordGrade())
						.recordEtc(dyeing.getRecordInfo().getRecordEtc())
						.recordGrade(dyeing.getRecordInfo().getRecordGrade())
						.designer(dyeing.getDesigner().getId())
						.dyeingColor(dyeing.getDyeingColor())
						.dyeingDecolorization(dyeing.getDyeingDecolorization())
						.dyeingTime(dyeing.getDyeingTime())
						.dyeingHurt(dyeing.getDyeingHurt())
						.build();
			default:
				throw new IllegalStateException("select right category");
		}
	}

	public RecordEntity toRecordEntity(RecordRequest request) {
		Long memberId = AuditorHolder.get();
		return RecordEntity.builder()
				.recordInfo(
						CommonRecordInfo.builder()
								.recordDate(request.getRecordDate())
								.recordCost(request.getRecordCost())
								.recordEtc(request.getRecordEtc())
								.recordGrade(request.getRecordGrade())
								.build())
				.member(MemberEntity.builder().id(memberId).build())
				.designer(DesignerEntity.builder().id(request.getDesignerId()).build())
				.build();
	}

	public CutEntity toCutEntity(CutRecordRequest request) {
		return CutEntity.builder()
				.cutName(request.getCutName())
				.cutLength(request.getCutLength())
				.build();
	}

	public PermEntity toPermEntity(PermRecordRequest request) {
		return PermEntity.builder()
				.permName(request.getPermName())
				.permTime(request.getPermTime())
				.permHurt(request.getPermHurt())
				.build();
	}

	public DyeingEntity toDyeingEntity(DyeingRecordRequest request) {
		return DyeingEntity.builder()
				.dyeingColor(request.getDyeingColor())
				.dyeingDecolorization(request.getDyeingDecolorization())
				.dyeingTime(request.getDyeingTime())
				.dyeingHurt(request.getDyeingHurt())
				.build();
	}
}
