package jongjun.hairlog.data.repository.query;

import java.util.Optional;
import jongjun.hairlog.data.dto.record.CutWitRecordView;

public interface CutCustomQuery {

	Optional<CutWitRecordView> findCutWithRecordByRecordIdAndDeletedFalse(Long recordId);
}
