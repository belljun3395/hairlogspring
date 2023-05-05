package jongjun.hairlog.app.web.controller.response;

import jongjun.hairlog.app.domain.model.member.Token;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenResponse {
	private final String accessToken;
	private final String refreshToken;

	public static TokenResponse from(Token token) {
		if (token == null) {
			return null;
		}
		return new TokenResponse(token.getAccessToken(), token.getRefreshToken());
	}
}
