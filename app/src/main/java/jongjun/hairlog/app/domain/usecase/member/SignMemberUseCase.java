package jongjun.hairlog.app.domain.usecase.member;

import jongjun.hairlog.app.domain.model.member.MemberAuthInfo;
import jongjun.hairlog.app.domain.query.MemberAuthQuery;
import jongjun.hairlog.app.domain.request.SignMemberRequest;
import jongjun.hairlog.app.support.token.TokenGenerator;
import jongjun.hairlog.app.web.controller.response.SaveMemberResponse;
import jongjun.hairlog.app.web.controller.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SignMemberUseCase {

	private final GetMemberUseCase getMemberUseCase;

	private final TokenGenerator tokenGenerator;

	public SaveMemberResponse execute(final SignMemberRequest request) {
		MemberAuthInfo member =
				getMemberUseCase.execute(MemberAuthQuery.builder().email(request.getEmail()).build());

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
