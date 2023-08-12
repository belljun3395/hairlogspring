package jongjun.hairlog.data.repository;

import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.repository.jpa.MemberJpaRepository;
import jongjun.hairlog.data.repository.query.MemberCustomQuery;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = MemberEntity.class, idClass = Long.class)
public interface MemberRepository extends MemberJpaRepository, MemberCustomQuery {}
