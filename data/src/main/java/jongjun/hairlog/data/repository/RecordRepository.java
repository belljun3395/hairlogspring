package jongjun.hairlog.data.repository;

import jongjun.hairlog.data.entity.record.RecordEntity;
import jongjun.hairlog.data.repository.query.RecordCustomQuery;
import org.springframework.data.repository.CrudRepository;

public interface RecordRepository extends CrudRepository<RecordEntity, Long>, RecordCustomQuery {}
