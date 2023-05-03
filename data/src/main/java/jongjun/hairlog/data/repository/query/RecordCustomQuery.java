package jongjun.hairlog.data.repository.query;

import java.util.List;
import java.util.Optional;
import jongjun.hairlog.data.dto.record.RecordDTO;
import jongjun.hairlog.data.dto.record.RecordDeletedDTO;
import jongjun.hairlog.data.dto.record.RecordIndexDTO;
import jongjun.hairlog.data.enums.RecordCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecordCustomQuery {

	Page<RecordIndexDTO> findAllByMemberIdQuery(Pageable pageable, Long memberId);

	Page<RecordIndexDTO> findAllByCategoryAndMemberIdQuery(
			Pageable pageable, RecordCategory category, Long memberId);

	public Optional<RecordDTO> findByIdAndCategoryAndMemberId(
			Long id, RecordCategory category, Long memberId);

	List<RecordDeletedDTO> findAllDeletedByMemberIdQuery(Long memberId);
}
