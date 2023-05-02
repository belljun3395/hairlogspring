package jongjun.hairlog.data.repository.query;

import java.util.List;
import jongjun.hairlog.data.dto.record.RecordDeletedDTO;
import jongjun.hairlog.data.dto.record.RecordIndexDTO;
import jongjun.hairlog.data.entity.record.RecordEntity;
import jongjun.hairlog.data.enums.RecordCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecordCustomQuery {

	Page<RecordIndexDTO> findAllByMemberIdQuery(Pageable pageable, Long memberId);

	Page<RecordEntity> findAllByCategoryAndMemberIdQuery(
			Pageable pageable, RecordCategory category, Long memberId);

	List<RecordDeletedDTO> findAllDeletedByMemberIdQuery(Long memberId);
}
