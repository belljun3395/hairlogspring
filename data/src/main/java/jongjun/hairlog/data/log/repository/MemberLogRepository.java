package jongjun.hairlog.data.log.repository;

import jongjun.hairlog.data.log.entity.MemberInfoLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLogRepository extends JpaRepository<MemberInfoLog, Long> {}
