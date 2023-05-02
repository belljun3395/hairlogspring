package jongjun.hairlog.data.repository.query;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jongjun.hairlog.data.dto.record.RecordDeletedDTO;
import jongjun.hairlog.data.dto.record.RecordIndexDTO;
import jongjun.hairlog.data.entity.record.RecordEntity;
import jongjun.hairlog.data.enums.RecordCategory;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecordCustomQueryImpl implements RecordCustomQuery {

	private static final String MEMBERID_PARAMETER = "memberId";
	private static final Integer MEMBERID_NATIVE_PARAMETER = 1;
	private static final String DELETED_RECORDS_FINDBYMEMBERID_NAMEDQUERY =
			"RecordEntity.findDeletedRecordsEntity";

	private static final String RECORD_FINDALLBY_MEMBERID =
			"select r.record_id, r.record_date, r.record_category from record_entity r where r.member_fk = ?1 order by r.record_id desc";

	/** fixme DTO 활용으로 수정 */
	private static final String RECORD_CUT_FINDBY_MEMBERID =
			"select r, c from RecordEntity r left join CutEntity c where r.member.id = :memberId order by r.id desc";

	private static final String RECORD_PERM_FINDBY_MEMBERID =
			"select r, p from RecordEntity r left join PermEntity p where r.member.id = :memberId order by r.id desc";
	private static final String RECORD_DYEING_FINDBY_MEMBERID =
			"select r, d from RecordEntity r left join DyeingEntity d where r.member.id = :memberId order by r.id desc";
	private static final String RECORD_COUNTS =
			"select count(r.id) from RecordEntity r where r.member.id = :memberId";

	private final EntityManager em;

	public Page<RecordIndexDTO> findAllByMemberIdQuery(Pageable pageable, Long memberId) {
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

		Long total =
				em.createQuery(RECORD_COUNTS, Long.class)
						.setParameter(MEMBERID_PARAMETER, memberId)
						.getSingleResult();

		JpaResultMapper jpaResultMapper = new JpaResultMapper();

		Query memberAllRecordQuery =
				em.createNativeQuery(RECORD_FINDALLBY_MEMBERID)
						.setParameter(MEMBERID_NATIVE_PARAMETER, memberId)
						.setFirstResult((int) pageable.getOffset())
						.setMaxResults(pageable.getPageSize());

		List<RecordIndexDTO> recordIdxs =
				jpaResultMapper.list(memberAllRecordQuery, RecordIndexDTO.class);

		return new PageImpl<>(recordIdxs, pageRequest, total);
	}

	public Page<RecordEntity> findAllByCategoryAndMemberIdQuery(
			Pageable pageable, RecordCategory category, Long memberId) {
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

		Long total =
				em.createQuery(RECORD_COUNTS, Long.class)
						.setParameter(MEMBERID_PARAMETER, memberId)
						.getSingleResult();

		String FINDBY_CATEGORY_MEMBERID = getQuery(category);
		List<RecordEntity> recordEntityList =
				em.createQuery(FINDBY_CATEGORY_MEMBERID, RecordEntity.class)
						.setParameter(MEMBERID_PARAMETER, memberId)
						.setFirstResult((int) pageable.getOffset())
						.setMaxResults(pageable.getPageSize())
						.getResultList();

		return new PageImpl<>(recordEntityList, pageRequest, total);
	}

	private static String getQuery(RecordCategory category) {
		switch (category) {
			case CUT:
				return RECORD_CUT_FINDBY_MEMBERID;
			case PERM:
				return RECORD_PERM_FINDBY_MEMBERID;
			case DYEING:
				return RECORD_DYEING_FINDBY_MEMBERID;
			default:
				throw new IllegalStateException("select right category");
		}
	}

	public List<RecordDeletedDTO> findAllDeletedByMemberIdQuery(Long memberId) {
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		Query deletedMemberQuery =
				em.createNamedQuery(DELETED_RECORDS_FINDBYMEMBERID_NAMEDQUERY)
						.setParameter(MEMBERID_NATIVE_PARAMETER, memberId);
		return jpaResultMapper.list(deletedMemberQuery, RecordDeletedDTO.class);
	}
}
