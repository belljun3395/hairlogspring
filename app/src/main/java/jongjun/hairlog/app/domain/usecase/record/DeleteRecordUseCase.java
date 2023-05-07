package jongjun.hairlog.app.domain.usecase.record;

import jongjun.hairlog.app.exception.ResourceNotFoundException;
import jongjun.hairlog.app.support.aop.ValidateRequestMemberId;
import jongjun.hairlog.app.support.validate.SourceValidator;
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

	@ValidateRequestMemberId
	public Long execute(Long memberId, Long recordId) {
		RecordEntity source =
				repository
						.findById(recordId)
						.orElseThrow(() -> new ResourceNotFoundException("not found resource"));

		SourceValidator.validate(memberId, source);

		repository.delete(source);
		return source.getId();
	}
}
