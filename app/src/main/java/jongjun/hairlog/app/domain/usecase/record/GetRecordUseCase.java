package jongjun.hairlog.app.domain.usecase.record;

import jongjun.hairlog.app.config.security.AuditorHolder;
import jongjun.hairlog.app.domain.converter.record.RecordConverter;
import jongjun.hairlog.app.domain.model.record.Record;
import jongjun.hairlog.app.domain.model.record.RecordIndex;
import jongjun.hairlog.app.support.Page;
import jongjun.hairlog.data.dto.record.RecordIdView;
import jongjun.hairlog.data.entity.MemberEntity;
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
		org.springframework.data.domain.Page<RecordIdView> source =
				repository.findAllByMemberAndDeletedFalse(
						MemberEntity.builder().id(memberId).build(), pageable);
		return converter.from(source);
	}

	public Page<RecordIndex> execute(RecordCategory category, Pageable pageable) {
		Long memberId = AuditorHolder.get();
		org.springframework.data.domain.Page<RecordIdView> source =
				repository.findAllByMemberAndRecordCategoryAndDeletedFalse(
						MemberEntity.builder().id(memberId).build(), category, pageable);
		return converter.from(source);
	}

	public Record execute(Long recordId, RecordCategory category) {
		Long memberId = AuditorHolder.get();

		return null;
	}
}
