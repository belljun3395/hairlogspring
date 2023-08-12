package jongjun.hairlog.app.domain.usecase.member;

import jongjun.hairlog.app.domain.converter.member.MemberConverter;
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

	public Long execute(final MemberRequest request) {
		MemberEntity data = converter.to(request);

		validateExist(request);

		return repository.save(data).getId();
	}

	private void validateExist(MemberRequest request) {
		Boolean isExist = repository.existsMemberEntitiesByEmailAndDeletedFalse(request.getEmail());
		if (isExist.equals(Boolean.TRUE)) {
			throw new SecurityException("이미 존재하는 이메일입니다.");
		}
	}
}
