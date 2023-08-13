package jongjun.hairlog.data.repository.query;

import java.util.Optional;
import jongjun.hairlog.data.dto.member.MemberAuthInfoView;
import jongjun.hairlog.data.dto.member.MemberInfoView;

public interface MemberCustomQuery {
	Optional<MemberInfoView> findViewById(Long id);

	Optional<MemberInfoView> findTopInfoViewByEmailAndDeletedFalse(String email);

	Optional<MemberAuthInfoView> findTopAuthInfoViewByEmailAndDeletedFalse(String email);

	Optional<Long> findTopIdById(Long id);
}
