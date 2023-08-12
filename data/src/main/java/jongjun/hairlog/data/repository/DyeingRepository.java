package jongjun.hairlog.data.repository;

import jongjun.hairlog.data.entity.record.DyeingEntity;
import jongjun.hairlog.data.repository.jpa.DyeingJpaRepository;
import jongjun.hairlog.data.repository.query.DyeingCustomQuery;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = DyeingEntity.class, idClass = Long.class)
public interface DyeingRepository extends DyeingJpaRepository, DyeingCustomQuery {}
