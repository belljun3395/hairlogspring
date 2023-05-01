package jongjun.hairlog.data.repository.query;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import jongjun.hairlog.data.entity.DesignerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DesignerCustomQueryImpl implements DesignerCustomQuery {

	private final EntityManager em;

	/** 일반 fk로 조회하는 경우랑 성능 비교해보기 */
	private static final String DESIGNER_FINDBY_MEMBERID =
			"select d from DesignerEntity d where d.member.id = "
					+ "(select m.id from MemberEntity m where m.id = :memberId)";

	public List<DesignerEntity> findByMemberIdQuery(Long memberId) {
		return em.createQuery(DESIGNER_FINDBY_MEMBERID, DesignerEntity.class)
				.setParameter("memberId", memberId)
				.getResultStream()
				.collect(Collectors.toList());
	}
}
