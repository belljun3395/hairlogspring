package jongjun.hairlog.data.repository.query;

import java.util.Optional;
import jongjun.hairlog.data.dto.member.MemberAuthInfoDTO;
import jongjun.hairlog.data.dto.member.MemberDeletedDTO;
import jongjun.hairlog.data.dto.member.MemberIdDTO;
import jongjun.hairlog.data.dto.member.MemberInfoDTO;

public interface MemberCustomQuery {

	Optional<MemberIdDTO> findByIdQuery(Long id);

	Optional<MemberInfoDTO> findByEmailQuery(String email);

	Optional<MemberDeletedDTO> findDeletedMemberByEmailQuery(String email);

	Optional<MemberAuthInfoDTO> findByEmailAuthQuery(String email);

	Boolean isExistEmailQuery(String email);
}
