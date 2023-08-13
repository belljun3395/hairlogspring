package jongjun.hairlog.app.domain.usecase.member;

import java.util.Objects;
import jongjun.hairlog.app.domain.converter.member.MemberConverter;
import jongjun.hairlog.app.domain.model.member.Member;
import jongjun.hairlog.app.domain.model.member.MemberAuthInfo;
import jongjun.hairlog.app.domain.model.member.MemberInfo;
import jongjun.hairlog.app.domain.query.MemberAuthQuery;
import jongjun.hairlog.app.exception.AlreadyEditException;
import jongjun.hairlog.app.exception.MemberNotFoundException;
import jongjun.hairlog.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetMemberUseCase {

	private final MemberRepository repository;
	private final MemberConverter converter;

	public Member execute(Long memberId) {
		Long topId = getTopId(memberId);

		return repository
				.findById(topId)
				.map(converter::from)
				.orElseThrow(() -> new MemberNotFoundException(memberId));
	}

	private Long getTopId(Long memberId) {
		Long topId =
				repository.findTopIdById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));

		if (!Objects.equals(memberId, topId)) {
			throw new AlreadyEditException("다시 로그인 해주세요");
		}
		return topId;
	}

	public MemberInfo execute(final String email) {
		return repository
				.findTopInfoViewByEmailAndDeletedFalse(email)
				.map(converter::from)
				.orElseThrow(() -> new MemberNotFoundException(email));
	}

	public MemberAuthInfo execute(final MemberAuthQuery query) {
		return repository
				.findTopAuthInfoViewByEmailAndDeletedFalse(query.getEmail())
				.map(converter::from)
				.orElseThrow(() -> new MemberNotFoundException(query.getEmail()));
	}
}
