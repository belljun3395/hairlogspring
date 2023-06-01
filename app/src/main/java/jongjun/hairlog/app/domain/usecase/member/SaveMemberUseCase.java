package jongjun.hairlog.app.domain.usecase.member;

import jongjun.hairlog.app.config.security.AuditorHolder;
import jongjun.hairlog.app.domain.converter.member.MemberConverter;
import jongjun.hairlog.app.domain.converter.member.MemberUpdateConverter;
import jongjun.hairlog.app.exception.MemberNotFoundException;
import jongjun.hairlog.app.web.controller.request.member.MemberEditRequest;
import jongjun.hairlog.app.web.controller.request.member.MemberRequest;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SaveMemberUseCase {

	private final MemberRepository repository;
	private final MemberConverter converter;
	private final MemberUpdateConverter updateConverter;

	public Long execute(MemberRequest request) {
		MemberEntity data = converter.to(request);
		if (repository.isExistEmail(request.getEmail())) {
			throw new SecurityException("이미 존재하는 이메일입니다.");
		}
		repository.save(data);
		return data.getId();
	}

	public Long execute(MemberEditRequest request) {
		Long memberId = AuditorHolder.get();
		MemberEntity memberEntity =
				repository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));
		updateConverter.to(memberEntity, request);
		return memberEntity.getId();
	}
}
