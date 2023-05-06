package jongjun.hairlog.app.domain.usecase.member;

import jongjun.hairlog.app.domain.converter.member.MemberConverter;
import jongjun.hairlog.app.domain.converter.member.MemberUpdateConverter;
import jongjun.hairlog.app.exception.MemberNotFoundException;
import jongjun.hairlog.app.support.aop.ValidateRequestMemberId;
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
		repository.save(data);
		return data.getId();
	}

	@ValidateRequestMemberId
	public Long execute(Long memberId, MemberEditRequest request) {
		MemberEntity memberEntity =
				repository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));
		updateConverter.to(memberEntity, request);
		return memberEntity.getId();
	}
}
