package jongjun.hairlog.data.repository;

import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.repository.query.MemberCustomQuery;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<MemberEntity, Long>, MemberCustomQuery {}
