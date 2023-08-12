package jongjun.hairlog.data.repository.jpa;

import java.util.Optional;
import jongjun.hairlog.data.dto.member.DeletedMemberView;
import jongjun.hairlog.data.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {

	Optional<DeletedMemberView> findTopByEmailAndDeletedTrueOrderById(String email);

	Boolean existsMemberEntitiesByEmailAndDeletedFalse(String email);
}
