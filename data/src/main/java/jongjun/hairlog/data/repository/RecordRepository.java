package jongjun.hairlog.data.repository;

import jongjun.hairlog.data.entity.record.RecordEntity;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends CrudRepository<RecordEntity, Long> {}
