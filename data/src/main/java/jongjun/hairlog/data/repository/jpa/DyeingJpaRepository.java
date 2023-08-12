package jongjun.hairlog.data.repository.jpa;

import jongjun.hairlog.data.entity.record.DyeingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DyeingJpaRepository extends JpaRepository<DyeingEntity, Long> {}
