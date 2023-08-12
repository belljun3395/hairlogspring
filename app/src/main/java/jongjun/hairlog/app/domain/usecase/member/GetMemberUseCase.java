package jongjun.hairlog.app.domain.usecase.member;

import jongjun.hairlog.app.config.security.AuditorHolder;
import jongjun.hairlog.app.domain.converter.member.MemberConverter;
import jongjun.hairlog.app.domain.model.member.Member;
import jongjun.hairlog.app.domain.model.member.MemberAuthInfo;
import jongjun.hairlog.app.domain.model.member.MemberInfo;
import jongjun.hairlog.app.domain.query.MemberAuthQuery;
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

	public Member execute() {
		Long memberId = AuditorHolder.get();
		return repository
				.findById(memberId)
				.map(converter::from)
				.orElseThrow(() -> new MemberNotFoundException(memberId));
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
