package jongjun.hairlog.app.domain.usecase.record;

import java.util.NoSuchElementException;
import jongjun.hairlog.app.config.security.AuditorHolder;
import jongjun.hairlog.app.domain.converter.record.RecordConverter;
import jongjun.hairlog.app.domain.model.record.Record;
import jongjun.hairlog.app.domain.model.record.RecordIndex;
import jongjun.hairlog.app.support.Page;
import jongjun.hairlog.data.dto.record.RecordIndexDTO;
import jongjun.hairlog.data.enums.RecordCategory;
import jongjun.hairlog.data.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetRecordUseCase {

	private final RecordRepository repository;
	private final RecordConverter converter;

	// todo Page<T> 확인해보기
	public Page<RecordIndex> execute(Pageable pageable) {
		Long memberId = AuditorHolder.get();
		org.springframework.data.domain.Page<RecordIndexDTO> source =
				repository.findAllByMemberIdQuery(pageable, memberId);
		return converter.from(source);
	}

	public Page<RecordIndex> execute(RecordCategory category, Pageable pageable) {
		Long memberId = AuditorHolder.get();
		org.springframework.data.domain.Page<RecordIndexDTO> source =
				repository.findAllByCategoryAndMemberIdQuery(pageable, category, memberId);
		return converter.from(source);
	}

	public Record execute(Long recordId, RecordCategory category) {
		Long memberId = AuditorHolder.get();
		return repository
				.findByIdAndCategoryAndMemberId(recordId, category, memberId)
				.map(source -> converter.from(source, category))
				.orElseThrow(() -> new NoSuchElementException("not found any record"));
	}
}
