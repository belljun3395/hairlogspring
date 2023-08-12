package jongjun.hairlog.data.repository.query;

import com.querydsl.jpa.JPQLQuery;
import java.util.Optional;
import jongjun.hairlog.data.dto.member.MemberAuthInfoView;
import jongjun.hairlog.data.dto.member.MemberInfoView;
import jongjun.hairlog.data.dto.member.QMemberAuthInfoView;
import jongjun.hairlog.data.dto.member.QMemberInfoView;
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
	public Optional<MemberInfoView> findViewById(Long id) {
		QMemberEntity memberEntity = QMemberEntity.memberEntity;
		JPQLQuery<MemberEntity> query = from(memberEntity);

		return Optional.ofNullable(
				query
						.select(new QMemberInfoView(memberEntity.email, memberEntity.name))
						.where(memberEntity.id.eq(id), memberEntity.deleted.isFalse())
						.fetchOne());
	}

	public Optional<MemberInfoView> findTopInfoViewByEmailAndDeletedFalse(String email) {
		QMemberEntity memberEntity = QMemberEntity.memberEntity;
		JPQLQuery<MemberEntity> query = from(memberEntity);

		return query
				.select(new QMemberInfoView(memberEntity.email, memberEntity.name))
				.where(memberEntity.email.eq(email), memberEntity.deleted.isFalse())
				.orderBy(memberEntity.id.desc())
				.stream()
				.findFirst();
	}

	public Optional<MemberAuthInfoView> findTopAuthInfoViewByEmailAndDeletedFalse(String email) {
		QMemberEntity memberEntity = QMemberEntity.memberEntity;
		JPQLQuery<MemberEntity> query = from(memberEntity);

		return query
				.select(
						new QMemberAuthInfoView(memberEntity.name, memberEntity.email, memberEntity.password))
				.where(memberEntity.email.eq(email), memberEntity.deleted.isFalse())
				.orderBy(memberEntity.id.desc())
				.stream()
				.findFirst();
	}
}
