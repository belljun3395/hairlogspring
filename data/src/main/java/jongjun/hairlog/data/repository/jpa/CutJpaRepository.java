package jongjun.hairlog.data.repository.jpa;

import jongjun.hairlog.data.entity.record.CutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CutJpaRepository extends JpaRepository<CutEntity, Long> {}
