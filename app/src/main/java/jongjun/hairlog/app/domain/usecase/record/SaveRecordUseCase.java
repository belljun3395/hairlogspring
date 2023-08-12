package jongjun.hairlog.app.domain.usecase.record;

import jongjun.hairlog.app.domain.converter.record.RecordConverter;
import jongjun.hairlog.app.domain.converter.record.RecordUpdateDelegator;
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
import jongjun.hairlog.data.repository.CutRepository;
import jongjun.hairlog.data.repository.DyeingRepository;
import jongjun.hairlog.data.repository.PermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SaveRecordUseCase {

	private final CutRepository cutRepository;
	private final PermRepository permRepository;
	private final DyeingRepository dyeingRepository;
	private final RecordConverter converter;
	private final RecordUpdateDelegator recordUpdateDelegator;

	public Long execute(CutRecordRequest request) {
		CutEntity data = converter.toCutEntity(request);
		cutRepository.save(data);

		return data.getId();
	}

	public Long execute(PermRecordRequest request) {
		PermEntity data = converter.toPermEntity(request);
		permRepository.save(data);

		return data.getId();
	}

	public Long execute(DyeingRecordRequest request) {
		DyeingEntity data = converter.toDyeingEntity(request);
		dyeingRepository.save(data);

		return data.getId();
	}

	public Long execute(CutRecordEditRequest request) {
		CutEntity source =
				cutRepository
						.findById(request.getId())
						.orElseThrow(() -> new ResourceNotFoundException("not found record"));

		recordUpdateDelegator.update(source, request);

		return source.getId();
	}

	public Long execute(PermRecordEditRequest request) {
		PermEntity source =
				permRepository
						.findById(request.getId())
						.orElseThrow(() -> new ResourceNotFoundException("not found record"));

		recordUpdateDelegator.update(source, request);

		return source.getId();
	}

	public Long execute(DyeingRecordEditRequest request) {
		DyeingEntity source =
				dyeingRepository
						.findById(request.getId())
						.orElseThrow(() -> new ResourceNotFoundException("not found record"));

		recordUpdateDelegator.update(source, request);

		return source.getId();
	}
}
