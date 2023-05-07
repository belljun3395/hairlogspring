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
import jongjun.hairlog.data.dto.record.CutDTO;
import jongjun.hairlog.data.dto.record.DyeingDTO;
import jongjun.hairlog.data.dto.record.PermDTO;
import jongjun.hairlog.data.dto.record.RecordDTO;
import jongjun.hairlog.data.dto.record.RecordIndexDTO;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.entity.record.CutEntity;
import jongjun.hairlog.data.entity.record.DyeingEntity;
import jongjun.hairlog.data.entity.record.PermEntity;
import jongjun.hairlog.data.enums.RecordCategory;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

@Component
public class RecordConverter {

	public Page<RecordIndex> from(org.springframework.data.domain.Page<RecordIndexDTO> source) {
		List<RecordIndex> domain = convertToDomain(source);
		return new Page(convertToPageSource(source, domain));
	}

	private PageImpl<RecordIndex> convertToPageSource(
			org.springframework.data.domain.Page<RecordIndexDTO> source, List<RecordIndex> domain) {
		PageImpl<RecordIndex> convertedPage =
				new PageImpl<>(domain, source.getPageable(), source.getTotalElements());
		return convertedPage;
	}

	private List<RecordIndex> convertToDomain(
			org.springframework.data.domain.Page<RecordIndexDTO> source) {
		List<RecordIndexDTO> contents = source.getContent();
		List<RecordIndex> list = new ArrayList<>();
		for (RecordIndexDTO rid : contents) {
			list.add(convertToSource(rid));
		}
		return list;
	}

	private RecordIndex convertToSource(RecordIndexDTO rid) {
		RecordIndex ri =
				RecordIndex.builder()
						.recordId(Long.valueOf(rid.getRecordId().toString()))
						.recordDate(rid.getRecordDate())
						.recordCategory(rid.getRecordCategory())
						.build();
		return ri;
	}

	public Record from(RecordDTO source, RecordCategory category) {
		return convertRecord(source, category);
	}

	private Record convertRecord(RecordDTO source, RecordCategory category) {
		switch (category) {
			case CUT:
				CutDTO cut = (CutDTO) source;
				return CutRecord.builder()
						.recordCost(cut.getRecordCost())
						.recordGrade(cut.getRecordGrade())
						.recordEtc(cut.getRecordEtc())
						.recordGrade(cut.getRecordGrade())
						.designer(cut.getDesigner().getId())
						.cutName(cut.getCutName())
						.cutLength(cut.getCutLength())
						.build();
			case PERM:
				PermDTO perm = (PermDTO) source;
				return PermRecord.builder()
						.recordCost(perm.getRecordCost())
						.recordGrade(perm.getRecordGrade())
						.recordEtc(perm.getRecordEtc())
						.recordGrade(perm.getRecordGrade())
						.designer(perm.getDesigner().getId())
						.permName(perm.getPermName())
						.permTime(perm.getPermTime())
						.permHurt(perm.getPermHurt())
						.build();
			case DYEING:
				DyeingDTO dyeing = (DyeingDTO) source;
				return DyeingRecord.builder()
						.recordCost(dyeing.getRecordCost())
						.recordGrade(dyeing.getRecordGrade())
						.recordEtc(dyeing.getRecordEtc())
						.recordGrade(dyeing.getRecordGrade())
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

	public CutEntity to(CutRecordRequest request) {
		Long memberId = AuditorHolder.get();
		return CutEntity.builder()
				.recordDate(request.getRecordDate())
				.recordCost(request.getRecordCost())
				.recordGrade(request.getRecordGrade())
				.recordEtc(request.getRecordEtc())
				.member(MemberEntity.builder().id(memberId).build())
				.designer(DesignerEntity.builder().id(request.getDesignerId()).build())
				.cutName(request.getCutName())
				.cutLength(request.getCutLength())
				.build();
	}

	public PermEntity to(PermRecordRequest request) {
		Long memberId = AuditorHolder.get();
		return PermEntity.builder()
				.recordDate(request.getRecordDate())
				.recordCost(request.getRecordCost())
				.recordGrade(request.getRecordGrade())
				.recordEtc(request.getRecordEtc())
				.member(MemberEntity.builder().id(memberId).build())
				.designer(DesignerEntity.builder().id(request.getDesignerId()).build())
				.permName(request.getPermName())
				.permTime(request.getPermTime())
				.permHurt(request.getPermHurt())
				.build();
	}

	public DyeingEntity to(DyeingRecordRequest request) {
		Long memberId = AuditorHolder.get();
		return DyeingEntity.builder()
				.recordDate(request.getRecordDate())
				.recordCost(request.getRecordCost())
				.recordGrade(request.getRecordGrade())
				.recordEtc(request.getRecordEtc())
				.member(MemberEntity.builder().id(memberId).build())
				.designer(DesignerEntity.builder().id(request.getDesignerId()).build())
				.dyeingColor(request.getDyeingColor())
				.dyeingDecolorization(request.getDyeingDecolorization())
				.dyeingTime(request.getDyeingTime())
				.dyeingHurt(request.getDyeingHurt())
				.build();
	}
}
