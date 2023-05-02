package jongjun.hairlog.app.domain.converter.record;

import jongjun.hairlog.app.support.Page;
import jongjun.hairlog.app.web.controller.request.record.CutRecordRequest;
import jongjun.hairlog.app.web.controller.request.record.DyeingRecordRequest;
import jongjun.hairlog.app.web.controller.request.record.PermRecordRequest;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.entity.record.CutEntity;
import jongjun.hairlog.data.entity.record.DyeingEntity;
import jongjun.hairlog.data.entity.record.PermEntity;
import org.springframework.stereotype.Component;

@Component
public class RecordConverter {

	/** fixme domain 객체를 통해 받도록 수정 */
	public Page from(org.springframework.data.domain.Page source) {
		return new Page(source);
	}

	public CutEntity to(CutRecordRequest request) {
		return CutEntity.builder()
				.recordDate(request.getRecordDate())
				.recordCost(request.getRecordCost())
				.recordEtc(request.getRecordEtc())
				.member(MemberEntity.builder().id(request.getMemberId()).build())
				.designer(DesignerEntity.builder().id(request.getDesignerId()).build())
				.cutName(request.getCutName())
				.cutLength(request.getCutLength())
				.build();
	}

	public PermEntity to(PermRecordRequest request) {
		return PermEntity.builder()
				.recordDate(request.getRecordDate())
				.recordCost(request.getRecordCost())
				.recordEtc(request.getRecordEtc())
				.member(MemberEntity.builder().id(request.getMemberId()).build())
				.designer(DesignerEntity.builder().id(request.getDesignerId()).build())
				.permName(request.getPermName())
				.permTime(request.getPermTime())
				.permHurt(request.getPermHurt())
				.build();
	}

	public DyeingEntity to(DyeingRecordRequest request) {
		return DyeingEntity.builder()
				.recordDate(request.getRecordDate())
				.recordCost(request.getRecordCost())
				.recordEtc(request.getRecordEtc())
				.member(MemberEntity.builder().id(request.getMemberId()).build())
				.designer(DesignerEntity.builder().id(request.getDesignerId()).build())
				.dyeingColor(request.getDyeingColor())
				.dyeingDecolorization(request.getDyeingDecolorization())
				.dyeingTime(request.getDyeingTime())
				.dyeingHurt(request.getDyeingHurt())
				.build();
	}
}
