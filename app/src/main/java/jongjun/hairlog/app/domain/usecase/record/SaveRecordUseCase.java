package jongjun.hairlog.app.domain.usecase.record;

import jongjun.hairlog.app.domain.converter.record.RecordConverter;
import jongjun.hairlog.app.domain.converter.record.RecordUpdateConverter;
import jongjun.hairlog.app.exception.ResourceNotFoundException;
import jongjun.hairlog.app.web.controller.request.record.CutRecordEditRequest;
import jongjun.hairlog.app.web.controller.request.record.CutRecordRequest;
import jongjun.hairlog.app.web.controller.request.record.DyeingRecordEditRequest;
import jongjun.hairlog.app.web.controller.request.record.DyeingRecordRequest;
import jongjun.hairlog.app.web.controller.request.record.PermRecordEditRequest;
import jongjun.hairlog.app.web.controller.request.record.PermRecordRequest;
import jongjun.hairlog.data.entity.record.CutEntity;
import jongjun.hairlog.data.entity.record.DyeingEntity;
import jongjun.hairlog.data.entity.record.PermEntity;
import jongjun.hairlog.data.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SaveRecordUseCase {

	private final RecordRepository repository;
	private final RecordConverter converter;
	private final RecordUpdateConverter updateConverter;

	public Long execute(CutRecordRequest request) {
		CutEntity data = converter.to(request);
		repository.save(data);
		return data.getId();
	}

	public Long execute(PermRecordRequest request) {
		PermEntity data = converter.to(request);
		repository.save(data);
		return data.getId();
	}

	public Long execute(DyeingRecordRequest request) {
		DyeingEntity data = converter.to(request);
		repository.save(data);
		return data.getId();
	}

	public Long execute(CutRecordEditRequest request) {
		CutEntity source =
				(CutEntity)
						repository
								.findById(request.getId())
								.orElseThrow(() -> new ResourceNotFoundException("not found record"));

		updateConverter.to(source, request);

		return source.getId();
	}

	public Long execute(PermRecordEditRequest request) {
		PermEntity source =
				(PermEntity)
						repository
								.findById(request.getId())
								.orElseThrow(() -> new ResourceNotFoundException("not found record"));

		updateConverter.to(source, request);

		return source.getId();
	}

	public Long execute(DyeingRecordEditRequest request) {
		DyeingEntity source =
				(DyeingEntity)
						repository
								.findById(request.getId())
								.orElseThrow(() -> new ResourceNotFoundException("not found record"));

		updateConverter.to(source, request);

		return source.getId();
	}
}
