package jongjun.hairlog.data.repository.jpa;

import java.util.List;
import jongjun.hairlog.data.entity.DesignerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DesignerJpaRepository extends JpaRepository<DesignerEntity, Long> {

	List<DesignerEntity> findAllByMemberIdAndDeletedFalse(Long memberId);
}
