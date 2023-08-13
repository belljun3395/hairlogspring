package jongjun.hairlog.app.domain.usecase.member;

import java.util.Objects;
import jongjun.hairlog.app.domain.converter.member.MemberUpdateDelegator;
import jongjun.hairlog.app.domain.request.EditMemberRequest;
import jongjun.hairlog.app.exception.AlreadyEditException;
import jongjun.hairlog.app.exception.MemberNotFoundException;
import jongjun.hairlog.app.support.token.TokenGenerator;
import jongjun.hairlog.app.web.controller.response.SaveMemberResponse;
import jongjun.hairlog.app.web.controller.response.TokenResponse;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EditMemberUseCase {

	private final MemberRepository repository;
	private final MemberUpdateDelegator updateDelegator;
	private final TokenGenerator tokenGenerator;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public SaveMemberResponse execute(final EditMemberRequest request) {
		Long topId = getTopId(request);

		MemberEntity memberEntity =
				repository.findById(topId).orElseThrow(() -> new MemberNotFoundException(topId));

		MemberEntity updateMemberEntity = deleteAndUpdate(request, memberEntity);

		return SaveMemberResponse.builder()
				.id(updateMemberEntity.getId())
				.name(updateMemberEntity.getName())
				.tokenResponse(TokenResponse.from(tokenGenerator.generateToken(updateMemberEntity.getId())))
				.build();
	}

	private Long getTopId(EditMemberRequest request) {
		Long topId =
				repository
						.findTopIdById(request.getId())
						.orElseThrow(() -> new MemberNotFoundException(request.getId()));

		if (!Objects.equals(topId, request.getId())) {
			throw new AlreadyEditException(request.getId());
		}
		return topId;
	}

	private MemberEntity deleteAndUpdate(EditMemberRequest request, MemberEntity memberEntity) {
		repository.deleteById(request.getId());

		return getUpdateMemberEntity(request, memberEntity);
	}

	private MemberEntity getUpdateMemberEntity(EditMemberRequest request, MemberEntity memberEntity) {
		MemberEntity updateMember = updateDelegator.update(memberEntity, request);
		return repository.save(updateMember);
	}
}
