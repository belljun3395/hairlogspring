package jongjun.hairlog.app.support.token;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import jongjun.hairlog.app.domain.model.member.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenGenerator {
	@Value("${security.jwt.token.secretkey}")
	private String secretKey;

	@Value("${security.jwt.token.validtime.access}")
	private Long accessTokenValidTime;

	@Value("${security.jwt.token.validtime.refresh}")
	private Long refreshTokenValidTime;

	@Value("${security.jwt.token.validtime.clientSecret}")
	private Long clientSecretValidTime;

	private static final String MEMBER_ID_CLAIM_KEY = "memberId";
	private static final String HEADER_KID = "kid";

	public String generateAccessToken(Long memberId) {
		Date now = new Date();

		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.claim(MEMBER_ID_CLAIM_KEY, memberId)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + accessTokenValidTime))
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
				.compact();
	}

	String generateRefreshToken(Long memberId) {
		Date now = new Date();

		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.claim(MEMBER_ID_CLAIM_KEY, memberId)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + refreshTokenValidTime))
				.signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
				.compact();
	}

	public Token generateToken(Long memberId) {
		return Token.builder()
				.accessToken(generateAccessToken(memberId))
				.refreshToken(generateRefreshToken(memberId))
				.build();
	}
}
