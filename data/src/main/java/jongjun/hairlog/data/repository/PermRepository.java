package jongjun.hairlog.data.repository;

import jongjun.hairlog.data.entity.record.PermEntity;
import jongjun.hairlog.data.repository.jpa.PermJpaRepository;
import jongjun.hairlog.data.repository.query.PermCustomQuery;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = PermEntity.class, idClass = Long.class)
public interface PermRepository extends PermJpaRepository, PermCustomQuery {}
