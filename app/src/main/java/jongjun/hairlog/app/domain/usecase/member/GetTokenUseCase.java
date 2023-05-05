package jongjun.hairlog.app.domain.usecase.member;

import jongjun.hairlog.app.domain.model.member.Token;
import jongjun.hairlog.app.exception.RefreshTokenInvalidException;
import jongjun.hairlog.app.support.token.TokenGenerator;
import jongjun.hairlog.app.support.token.TokenResolver;
import jongjun.hairlog.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class GetTokenUseCase {

	private final TokenResolver tokenResolver;
	private final TokenGenerator tokenGenerator;
	private final MemberRepository memberRepository;

	public Token execute(String refreshToken) {
		Long memberId =
				tokenResolver.resolveToken(refreshToken).orElseThrow(RefreshTokenInvalidException::new);

		if (!memberRepository.existsById(memberId)) {
			throw new RefreshTokenInvalidException();
		}
		return tokenGenerator.generateToken(memberId);
	}
}
