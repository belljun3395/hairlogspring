package jongjun.hairlog.data.repository.query;

import java.util.Optional;
import jongjun.hairlog.data.dto.record.PermWithRecordView;

public interface PermCustomQuery {
	Optional<PermWithRecordView> findCutWithRecordByRecordIdAndDeletedFalse(Long recordId);
}
