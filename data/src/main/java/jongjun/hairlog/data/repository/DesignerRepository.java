package jongjun.hairlog.data.repository;

import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.repository.jpa.DesignerJpaRepository;
import jongjun.hairlog.data.repository.query.DesignerCustomQuery;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = DesignerEntity.class, idClass = Long.class)
public interface DesignerRepository extends DesignerJpaRepository, DesignerCustomQuery {}
