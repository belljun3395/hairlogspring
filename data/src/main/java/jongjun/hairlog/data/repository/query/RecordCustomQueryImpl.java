package jongjun.hairlog.data.repository.query;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jongjun.hairlog.data.dto.record.CutDTO;
import jongjun.hairlog.data.dto.record.DyeingDTO;
import jongjun.hairlog.data.dto.record.PermDTO;
import jongjun.hairlog.data.dto.record.RecordDTO;
import jongjun.hairlog.data.dto.record.RecordDeletedDTO;
import jongjun.hairlog.data.dto.record.RecordIndexDTO;
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
	private static final String ID_PARAMETER = "id";
	private static final Integer MEMBERID_NATIVE_PARAMETER = 1;
	private static final Integer CATEGORY_NATIVE_PARAMETER = 2;
	private static final Integer RECORDID_NATIVE_PARAMETER_FOR_CATEGORY_QUERY = 2;
	private static final String DELETED_RECORDS_FINDBYMEMBERID_NAMEDQUERY =
			"RecordEntity.findDeletedRecordsEntity";

	private static final String RECORD_FINDALLBY_MEMBERID =
			"select r.record_id, r.record_date, r.record_category from record_entity r where r.member_fk = ?1 order by r.record_id desc";

	/** fixme DTO 활용으로 수정 */
	private static final String RECORD_FINDBY_CATEGORY_MEMBERID =
			"select r.record_id, r.record_date, r.record_category from record_entity r where r.member_fk = ?1 and r.record_category = ?2 order by r.record_id desc";

	private static final String RECORD_CUT_FINDBY_MEMBERID =
			"select r.record_id, r.record_date, r.record_cost, r.record_etc, r.record_grade, r.member_fk, r.designer_fk, rc.cut_name, rc.cut_length "
					+ "from record_entity r "
					+ "join record_cut_entity rc on (r.record_id = ?2 and r.record_id = rc.record_id) "
					+ "where r.member_fk = ?1";
	private static final String RECORD_PERM_FINDBY_MEMBERID =
			"select r.record_id, r.record_date, r.record_cost, r.record_etc, r.record_grade, r.member_fk, r.designer_fk, rp.perm_name, rp.perm_time, rp.perm_hurt "
					+ "from record_entity r "
					+ "join record_perm_entity rp on (r.record_id = ?2 and r.record_id = rp.record_id) "
					+ "where r.member_fk = ?1";
	private static final String RECORD_DYEING_FINDBY_MEMBERID =
			"select r.record_id, r.record_date, r.record_cost, r.record_etc, r.record_grade, r.member_fk, r.designer_fk, rd.dyeing_color, rd.dyeing_decolorization, rd.dyeing_time, rd.dyeing_hurt "
					+ "from record_entity r join record_dyeing_entity rd on (r.record_id = ?2 and r.record_id = rd.record_id) "
					+ "where r.member_fk = ?1";
	private static final String RECORD_COUNTS =
			"select count(r.id) from RecordEntity r where r.member.id = :memberId";
	private static final String RECORD_CATEGORY_COUNTS =
			"select count(r.record_id) from record_entity r where r.member_fk = ?1 and r.record_category = ?2";
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

	public Page<RecordIndexDTO> findAllByCategoryAndMemberIdQuery(
			Pageable pageable, RecordCategory category, Long memberId) {
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());

		BigInteger bTotal =
				(BigInteger)
						em.createNativeQuery(RECORD_CATEGORY_COUNTS)
								.setParameter(MEMBERID_NATIVE_PARAMETER, memberId)
								.setParameter(CATEGORY_NATIVE_PARAMETER, category.toString())
								.getSingleResult();

		Long total = Long.parseLong(bTotal.toString());

		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		Query categoryMemberAllQuery =
				em.createNativeQuery(RECORD_FINDBY_CATEGORY_MEMBERID)
						.setParameter(MEMBERID_NATIVE_PARAMETER, memberId)
						.setParameter(CATEGORY_NATIVE_PARAMETER, category.toString())
						.setFirstResult((int) pageable.getOffset())
						.setMaxResults(pageable.getPageSize());
		List<RecordIndexDTO> recordIdxsList =
				jpaResultMapper.list(categoryMemberAllQuery, RecordIndexDTO.class);

		return new PageImpl<>(recordIdxsList, pageRequest, total);
	}

	public Optional<RecordDTO> findByIdAndCategoryAndMemberId(
			Long id, RecordCategory category, Long memberId) {

		Query query = createQuery(id, category, memberId);

		return Optional.ofNullable(creatReturn(query, category));
	}

	private Query createQuery(Long id, RecordCategory category, Long memberId) {
		switch (category) {
			case CUT:
				return em.createNativeQuery(RECORD_CUT_FINDBY_MEMBERID)
						.setParameter(RECORDID_NATIVE_PARAMETER_FOR_CATEGORY_QUERY, id)
						.setParameter(MEMBERID_NATIVE_PARAMETER, memberId);
			case PERM:
				return em.createNativeQuery(RECORD_PERM_FINDBY_MEMBERID)
						.setParameter(RECORDID_NATIVE_PARAMETER_FOR_CATEGORY_QUERY, id)
						.setParameter(MEMBERID_NATIVE_PARAMETER, memberId);
			case DYEING:
				return em.createNativeQuery(RECORD_DYEING_FINDBY_MEMBERID)
						.setParameter(RECORDID_NATIVE_PARAMETER_FOR_CATEGORY_QUERY, id)
						.setParameter(MEMBERID_NATIVE_PARAMETER, memberId);
			default:
				throw new IllegalStateException("select right category");
		}
	}

	private RecordDTO creatReturn(Query query, RecordCategory category) {
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		switch (category) {
			case CUT:
				return jpaResultMapper.uniqueResult(query, CutDTO.class);
			case PERM:
				return jpaResultMapper.uniqueResult(query, PermDTO.class);
			case DYEING:
				return jpaResultMapper.uniqueResult(query, DyeingDTO.class);
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
