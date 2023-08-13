package jongjun.hairlog.data.repository.jpa;

import java.util.Optional;
import jongjun.hairlog.data.dto.member.DeletedMemberView;
import jongjun.hairlog.data.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {

	@Query(value = "select get_lock(:key, 3000)", nativeQuery = true)
	void getLock(String key);

	@Query(value = "select release_lock(:key)", nativeQuery = true)
	void releaseLock(String key);

	Optional<DeletedMemberView> findTopByEmailAndDeletedTrueOrderById(String email);

	Boolean existsMemberEntitiesByEmailAndDeletedFalse(String email);
}
