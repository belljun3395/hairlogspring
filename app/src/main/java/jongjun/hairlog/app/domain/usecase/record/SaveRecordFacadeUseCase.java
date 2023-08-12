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
import jongjun.hairlog.app.web.controller.request.record.RecordEditRequest;
import jongjun.hairlog.app.web.controller.request.record.RecordRequest;
import jongjun.hairlog.data.entity.record.RecordEntity;
import jongjun.hairlog.data.enums.RecordCategory;
import jongjun.hairlog.data.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SaveRecordFacadeUseCase {

	private final SaveRecordUseCase saveRecordUseCase;
	private final RecordRepository repository;
	private final RecordConverter converter;
	private final RecordUpdateDelegator updateConverter;

	public Long execute(CutRecordRequest request) {
		Long subId = saveRecordUseCase.execute(request);
		RecordEntity recordEntity = getRecordEntity(request, subId, RecordCategory.CUT);

		return repository.save(recordEntity).getId();
	}

	public Long execute(PermRecordRequest request) {
		Long subId = saveRecordUseCase.execute(request);
		RecordEntity recordEntity = getRecordEntity(request, subId, RecordCategory.PERM);

		return repository.save(recordEntity).getId();
	}

	public Long execute(DyeingRecordRequest request) {
		Long subId = saveRecordUseCase.execute(request);
		RecordEntity recordEntity = getRecordEntity(request, subId, RecordCategory.DYEING);

		return repository.save(recordEntity).getId();
	}

	private RecordEntity getRecordEntity(RecordRequest request, Long subId, RecordCategory category) {
		RecordEntity recordEntity = converter.toRecordEntity(request);
		recordEntity.associateWithSubRecord(subId, category);
		return recordEntity;
	}

	public Long execute(CutRecordEditRequest request) {
		Long id = saveRecordUseCase.execute(request);
		RecordEntity recordEntity = editRecord(request, id);

		return recordEntity.getId();
	}

	public Long execute(PermRecordEditRequest request) {
		Long id = saveRecordUseCase.execute(request);
		RecordEntity recordEntity = editRecord(request, id);

		return recordEntity.getId();
	}

	public Long execute(DyeingRecordEditRequest request) {
		Long id = saveRecordUseCase.execute(request);
		RecordEntity recordEntity = editRecord(request, id);

		return recordEntity.getId();
	}

	private RecordEntity editRecord(RecordEditRequest request, Long id) {
		RecordEntity recordEntity =
				repository
						.findById(id)
						.orElseThrow(() -> new ResourceNotFoundException("not found record"));
		updateConverter.update(recordEntity, request);
		return recordEntity;
	}
}
