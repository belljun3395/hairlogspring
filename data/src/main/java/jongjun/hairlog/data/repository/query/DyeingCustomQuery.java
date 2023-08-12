package jongjun.hairlog.data.repository.query;

import java.util.Optional;
import jongjun.hairlog.data.dto.record.DyeingWithRecordView;

public interface DyeingCustomQuery {
	Optional<DyeingWithRecordView> findCutWithRecordByRecordIdAndDeletedFalse(Long recordId);
}
