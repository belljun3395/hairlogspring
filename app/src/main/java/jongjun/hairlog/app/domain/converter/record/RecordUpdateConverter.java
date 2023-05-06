package jongjun.hairlog.app.domain.converter.record;

import jongjun.hairlog.app.web.controller.request.record.CutRecordEditRequest;
import jongjun.hairlog.app.web.controller.request.record.DyeingRecordEditRequest;
import jongjun.hairlog.app.web.controller.request.record.PermRecordEditRequest;
import jongjun.hairlog.app.web.controller.request.record.RecordEditRequest;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.record.CutEntity;
import jongjun.hairlog.data.entity.record.DyeingEntity;
import jongjun.hairlog.data.entity.record.PermEntity;
import jongjun.hairlog.data.entity.record.RecordEntity;
import org.springframework.stereotype.Component;

@Component
public class RecordUpdateConverter {

	public void to(CutEntity source, CutRecordEditRequest request) {
		changeSource(source, request);
		if (request.getCutName() != null) {
			source.changeCutName(request.getCutName());
		}
		if (request.getCutLength() != null) {
			source.changeCutLength(request.getCutLength());
		}
	}

	public void to(PermEntity source, PermRecordEditRequest request) {
		changeSource(source, request);
		if (request.getPermName() != null) {
			source.changePermName(request.getPermName());
		}
		if (request.getPermTime() != null) {
			source.changePermTime(request.getPermTime());
		}
		if (request.getPermHurt() != null) {
			source.changePermHurt(request.getPermHurt());
		}
	}

	public void to(DyeingEntity source, DyeingRecordEditRequest request) {
		changeSource(source, request);
		if (request.getDyeingColor() != null) {
			source.changeDyeingColor(request.getDyeingColor());
		}
		if (request.getDyeingDecolorization() != null) {
			source.changeDyeingDecolorization(request.getDyeingDecolorization());
		}
		if (request.getDyeingTime() != null) {
			source.changeDyeingTime(request.getDyeingTime());
		}
		if (request.getDyeingHurt() != null) {
			source.changeDyeingHurt(request.getDyeingHurt());
		}
	}

	private void changeSource(RecordEntity source, RecordEditRequest request) {
		if (request.getRecordDate() != null) {
			source.changeRecordDate(request.getRecordDate());
		}
		if (request.getRecordCost() != null) {
			source.changeRecordCost(request.getRecordCost());
		}
		if (request.getRecordEtc() != null) {
			source.changeRecordEtc(request.getRecordEtc());
		}
		if (request.getRecordGrade() != null) {
			source.changeRecordGrade(request.getRecordGrade());
		}
		if (request.getDesignerId() != null) {
			source.changeDesigner(DesignerEntity.builder().id(request.getDesignerId()).build());
		}
	}
}
