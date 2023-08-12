package jongjun.hairlog.data.repository.jpa;

import java.util.List;
import jongjun.hairlog.data.dto.designer.DeletedDesignerView;
import jongjun.hairlog.data.dto.designer.DesignerView;
import jongjun.hairlog.data.entity.DesignerEntity;
import jongjun.hairlog.data.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DesignerJpaRepository extends JpaRepository<DesignerEntity, Long> {

	List<DesignerView> findAllByMemberIdAndDeletedFalse(Long memberId);

	List<DesignerView> findAllByDesignerNameAndMemberAndDeletedFalse(
			String designerName, MemberEntity member);

	List<DeletedDesignerView> findAllByMemberAndDeletedTrue(MemberEntity member);
}
