package jongjun.hairlog.data.repository.query;

import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import jongjun.hairlog.data.dto.member.MemberDeletedDTO;
import jongjun.hairlog.data.dto.member.MemberIdDTO;
import jongjun.hairlog.data.dto.member.MemberInfoDTO;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberCustomQueryImpl implements MemberCustomQuery {

	private static final String ID_PARAMETER = "id";
	private static final String EMAIL_PARAMETER = "email";
	private static final Integer EMAIL_NATIVE_PARAMETER = 1;
	private static final String DELETED_MEMBER_FINDBYEMAIL_NAMEDQUERY =
			"MemberEntity.findDeletedMemberEntity";
	private static final String MEMBER_DTO_PACKAGE = "jongjun.hairlog.data.dto.member";
	private static final String MEMBER_FINDBYID_QUERY =
			"select new "
					+ MEMBER_DTO_PACKAGE
					+ ".MemberIdDTO(m.id) from MemberEntity m where m.id = :id";
	private static final String MEMBER_FINDBYEMAIL_QUERY =
			"select new "
					+ MEMBER_DTO_PACKAGE
					+ ".MemberInfoDTO(m.email, m.name) from MemberEntity m where m.email = :email";

	private final EntityManager em;

	public Optional<MemberIdDTO> findByIdQuery(Long id) {
		return em.createQuery(MEMBER_FINDBYID_QUERY, MemberIdDTO.class)
				.setParameter(ID_PARAMETER, id)
				.getResultStream()
				.findFirst();
	}

	public Optional<MemberInfoDTO> findByEmailQuery(String email) {
		return em.createQuery(MEMBER_FINDBYEMAIL_QUERY, MemberInfoDTO.class)
				.setParameter(EMAIL_PARAMETER, email)
				.getResultStream()
				.findFirst();
	}

	public Optional<MemberDeletedDTO> findDeletedMemberByEmailQuery(String email) {
		JpaResultMapper jpaResultMapper = new JpaResultMapper();
		Query deletedMemberQuery =
				em.createNamedQuery(DELETED_MEMBER_FINDBYEMAIL_NAMEDQUERY)
						.setParameter(EMAIL_NATIVE_PARAMETER, email);
		return Optional.ofNullable(
				jpaResultMapper.uniqueResult(deletedMemberQuery, MemberDeletedDTO.class));
	}
}