package jongjun.hairlog.app.domain.usecase.member;

import jongjun.hairlog.app.config.security.AuditorHolder;
import jongjun.hairlog.app.domain.converter.member.MemberUpdateDelegator;
import jongjun.hairlog.app.exception.MemberNotFoundException;
import jongjun.hairlog.app.support.token.TokenGenerator;
import jongjun.hairlog.app.web.controller.request.member.MemberEditRequest;
import jongjun.hairlog.app.web.controller.response.SaveMemberResponse;
import jongjun.hairlog.app.web.controller.response.TokenResponse;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class EditMemberUseCase {

	private final MemberRepository repository;
	private final MemberUpdateDelegator updateConverter;
	private final TokenGenerator tokenGenerator;

	public SaveMemberResponse execute(final MemberEditRequest request) {
		Long memberId = AuditorHolder.get();
		MemberEntity memberEntity =
				repository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));

		MemberEntity updateMemberEntity = deleteAndUpdate(request, memberEntity);

		return SaveMemberResponse.builder()
				.id(updateMemberEntity.getId())
				.name(updateMemberEntity.getName())
				.tokenResponse(TokenResponse.from(tokenGenerator.generateToken(updateMemberEntity.getId())))
				.build();
	}

	private MemberEntity deleteAndUpdate(MemberEditRequest request, MemberEntity memberEntity) {
		repository.delete(memberEntity);

		return getUpdateMemberEntity(request, memberEntity);
	}

	private MemberEntity getUpdateMemberEntity(MemberEditRequest request, MemberEntity memberEntity) {
		updateConverter.update(memberEntity, request);
		return repository.save(memberEntity.toBuilder().id(null).build());
	}
}
