package jongjun.hairlog.data.repository.query;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jongjun.hairlog.data.dto.designer.DesignerDTO;
import jongjun.hairlog.data.dto.designer.DesignerDeletedDTO;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DesignerCustomQueryImpl implements DesignerCustomQuery {

	private static final String MEMBERID_PARAMETER = "memberId";
	private static final Integer MEMBERID_NATIVE_PARAMETER = 1;
	private static final Integer DESIGNERNAME_NATIVE_PARAMETER = 2;
	private static final String DELETED_DESIGNERS_FINDBYMEMBERID_NAMEDQUERY =
			"DesignerEntity.findDeletedDesignersEntity";

	private static final String DESIGNER_DTO_PACKAGE = "jongjun.hairlog.data.dto.designer";

	/** 일반 fk로 조회하는 경우랑 성능 비교해보기 */
	private static final String DESIGNER_FINDBY_MEMBERID =
			"select new "
					+ DESIGNER_DTO_PACKAGE
					+ ".DesignerDTO(d.id, d.designerName, d.designerSalon, d.designerFav) "
					+ "from DesignerEntity d "
					+ "where d.member.id "
					+ "= (select m.id from MemberEntity m where m.id = :memberId)";

	private static final String DESIGNER_SEARCHBY_NAME_MEMBERID =
			"select d.designer_id, d.designer_name, d.designer_salon, d.designer_fav "
					+ "from designer_entity d where d.member_fk = ?1 "
					+ "and match(designer_name) against(?2 in natural language mode)";

	private final EntityManager em;

	public List<DesignerDTO> findAllByMemberIdQuery(Long memberId) {
		return em.createQuery(DESIGNER_FINDBY_MEMBERID, DesignerDTO.class)
				.setParameter(MEMBERID_PARAMETER, memberId)
				.getResultList();
	}

	public List<DesignerDTO> searchByNameAndMemberIdQuery(String designerName, Long memberId) {
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		Query searchByNameAndMemberIdQuery =
				em.createNativeQuery(DESIGNER_SEARCHBY_NAME_MEMBERID)
						.setParameter(MEMBERID_NATIVE_PARAMETER, memberId)
						.setParameter(DESIGNERNAME_NATIVE_PARAMETER, designerName);
		return jpaResultMapper.list(searchByNameAndMemberIdQuery, DesignerDTO.class);
	}

	public List<DesignerDeletedDTO> findAllDeletedByMemberIdQuery(Long memberId) {
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		Query deletedMemberQuery =
				em.createNamedQuery(DELETED_DESIGNERS_FINDBYMEMBERID_NAMEDQUERY)
						.setParameter(MEMBERID_NATIVE_PARAMETER, memberId);
		return jpaResultMapper.list(deletedMemberQuery, DesignerDeletedDTO.class);
	}
}
