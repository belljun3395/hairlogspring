package jongjun.hairlog.data.repository.query;

import java.util.List;
import javax.persistence.EntityManager;
import jongjun.hairlog.data.entity.record.RecordEntity;
import jongjun.hairlog.data.enums.RecordCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecordCustomQueryImpl implements RecordCustomQuery {

	private final EntityManager em;
	private static final String MEMBERID_PARAMETER = "memberId";

	/** 해당 쿼리로 조회하는 경우 컨버터가 record_category로 분기를 나누어 변환해줄 필요가 있다. */
	private static final String RECORD_FINDALLBY_MEMBERID =
			"select r from RecordEntity r where r.member.id = :memberId order by r.id desc";

	private static final String RECORD_CUT_FINDBY_MEMBERID =
			"select r, c from RecordEntity r left join CutEntity c where r.member.id = :memberId order by r.id desc";
	private static final String RECORD_PERM_FINDBY_MEMBERID =
			"select r, p from RecordEntity r left join PermEntity p where r.member.id = :memberId order by r.id desc";
	private static final String RECORD_DYEING_FINDBY_MEMBERID =
			"select r, d from RecordEntity r left join DyeingEntity d where r.member.id = :memberId order by r.id desc";
	private static final String RECORD_COUNTS =
			"select count(r.id) from RecordEntity r where r.member.id = :memberId";

	public Page<RecordEntity> findAllByMemberIdQuery(Pageable pageable, Long memberId) {
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

		Long total =
				em.createQuery(RECORD_COUNTS, Long.class)
						.setParameter(MEMBERID_PARAMETER, memberId)
						.getSingleResult();

		List<RecordEntity> recordEntityList =
				em.createQuery(RECORD_FINDALLBY_MEMBERID, RecordEntity.class)
						.setParameter(MEMBERID_PARAMETER, memberId)
						.setFirstResult((int) pageable.getOffset())
						.setMaxResults(pageable.getPageSize())
						.getResultList();

		return new PageImpl<>(recordEntityList, pageRequest, total);
	}

	public Page<RecordEntity> findByCategoryAndMemberIdQuery(
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
			case C:
				return RECORD_CUT_FINDBY_MEMBERID;
			case P:
				return RECORD_PERM_FINDBY_MEMBERID;
			case D:
				return RECORD_DYEING_FINDBY_MEMBERID;
			default:
				throw new IllegalStateException("select right category");
		}
	}
}
