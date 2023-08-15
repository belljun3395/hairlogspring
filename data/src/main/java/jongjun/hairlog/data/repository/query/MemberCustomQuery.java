package jongjun.hairlog.data.repository.query;

import java.util.Optional;
import jongjun.hairlog.data.entity.MemberEntity;

public interface MemberCustomQuery {
	Optional<MemberEntity> findViewById(Long id);

	Optional<MemberEntity> findInfoViewByEmailAndDeletedFalse(String email);

	Optional<MemberEntity> findAuthInfoViewByEmailAndDeletedFalse(String email);
}
