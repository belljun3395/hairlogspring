package jongjun.hairlog.app.domain.usecase.record;

import jongjun.hairlog.app.exception.ResourceNotFoundException;
import jongjun.hairlog.data.entity.record.RecordEntity;
import jongjun.hairlog.data.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteRecordUseCase {

	private final RecordRepository repository;

	public Long execute(Long recordId) {
		RecordEntity source =
				repository
						.findById(recordId)
						.orElseThrow(() -> new ResourceNotFoundException("not found resource"));

		repository.delete(source);
		return source.getId();
	}
}
