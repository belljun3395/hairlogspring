package jongjun.hairlog.app.domain.usecase.member;

import jongjun.hairlog.app.domain.request.SignMemberRequest;
import jongjun.hairlog.app.exception.MemberNotFoundException;
import jongjun.hairlog.app.support.token.TokenGenerator;
import jongjun.hairlog.app.web.controller.response.SaveMemberResponse;
import jongjun.hairlog.app.web.controller.response.TokenResponse;
import jongjun.hairlog.data.entity.MemberEntity;
import jongjun.hairlog.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SignMemberUseCase {

	private final MemberRepository memberRepository;

	private final TokenGenerator tokenGenerator;

	public SaveMemberResponse execute(final SignMemberRequest request) {

		MemberEntity member =
				memberRepository
						.findTopByEmailAndDeletedFalseOrderById(request.getEmail())
						.orElseThrow(() -> new MemberNotFoundException(request.getEmail()));

		if (!member.getPassword().equals(request.getPassword())) {
			throw new AssertionError("not match password");
		}

		return SaveMemberResponse.builder()
				.id(member.getId())
				.name(member.getName())
				.tokenResponse(TokenResponse.from(tokenGenerator.generateToken(member.getId())))
				.build();
	}
}
