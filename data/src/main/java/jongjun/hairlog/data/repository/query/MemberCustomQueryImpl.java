package jongjun.hairlog.data.repository.query;

import com.querydsl.jpa.JPQLQuery;
import java.util.Optional;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.entity.QMemberEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class MemberCustomQueryImpl extends QuerydslRepositorySupport implements MemberCustomQuery {
	public MemberCustomQueryImpl() {
		super(MemberEntity.class);
	}

	@Override
	public Optional<MemberEntity> findViewById(Long id) {
		QMemberEntity memberEntity = QMemberEntity.memberEntity;
		JPQLQuery<MemberEntity> query = from(memberEntity);

		return Optional.ofNullable(
				query.where(memberEntity.id.eq(id), memberEntity.deleted.isFalse()).fetchOne());
	}

	public Optional<MemberEntity> findInfoViewByEmailAndDeletedFalse(String email) {
		QMemberEntity memberEntity = QMemberEntity.memberEntity;
		JPQLQuery<MemberEntity> query = from(memberEntity);

		return Optional.ofNullable(
				query
						.where(memberEntity.email.eq(email), memberEntity.deleted.isFalse())
						.orderBy(memberEntity.id.desc())
						.fetchOne());
	}

	public Optional<MemberEntity> findAuthInfoViewByEmailAndDeletedFalse(String email) {
		QMemberEntity memberEntity = QMemberEntity.memberEntity;
		JPQLQuery<MemberEntity> query = from(memberEntity);

		return Optional.ofNullable(
				query
						.where(memberEntity.email.eq(email), memberEntity.deleted.isFalse())
						.orderBy(memberEntity.id.desc())
						.fetchOne());
	}
}
