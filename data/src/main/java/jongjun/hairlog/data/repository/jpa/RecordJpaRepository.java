package jongjun.hairlog.data.repository.jpa;

import java.util.List;
import jongjun.hairlog.data.dto.record.RecordIdView;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.entity.record.RecordEntity;
import jongjun.hairlog.data.enums.RecordCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RecordJpaRepository extends JpaRepository<RecordEntity, Long> {

	Page<RecordIdView> findAllByMemberAndDeletedFalse(MemberEntity member, Pageable pageable);

	List<RecordIdView> findAllByMemberAndDeletedFalse(MemberEntity member);

	Page<RecordIdView> findAllByMemberAndRecordCategoryAndDeletedFalse(
			MemberEntity member, RecordCategory category, Pageable pageable);

	List<RecordIdView> findAllByMemberAndRecordCategoryAndDeletedFalse(
			MemberEntity member, RecordCategory category);
}
