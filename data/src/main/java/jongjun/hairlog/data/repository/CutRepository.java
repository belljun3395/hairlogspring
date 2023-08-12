package jongjun.hairlog.data.repository;

import jongjun.hairlog.data.entity.record.CutEntity;
import jongjun.hairlog.data.repository.jpa.CutJpaRepository;
import jongjun.hairlog.data.repository.query.CutCustomQuery;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = CutEntity.class, idClass = Long.class)
public interface CutRepository extends CutJpaRepository, CutCustomQuery {}
