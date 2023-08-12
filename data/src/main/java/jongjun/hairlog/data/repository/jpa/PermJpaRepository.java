package jongjun.hairlog.data.repository.jpa;

import jongjun.hairlog.data.entity.record.PermEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PermJpaRepository extends JpaRepository<PermEntity, Long> {}
