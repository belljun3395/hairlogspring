package jongjun.hairlog.data.repository;

import jongjun.hairlog.data.entity.record.RecordEntity;
import jongjun.hairlog.data.repository.jpa.RecordJpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = RecordEntity.class, idClass = Long.class)
public interface RecordRepository extends RecordJpaRepository {}
