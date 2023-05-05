package jongjun.hairlog.app.support.token;

import io.jsonwebtoken.Jwts;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenResolver {

	private static final int PAYLOAD_INDEX = 1;
	private static final String MEMBER_ID_CLAIM_KEY = "memberId";
	private static final String SUB_CLAIM_KEY = "sub";

	@Value("${security.jwt.token.secretkey}")
	private String secretKey;

	public Optional<Long> resolveToken(String token) {
		try {
			return Optional.ofNullable(
					Jwts.parserBuilder()
							.setSigningKey(secretKey.getBytes())
							.build()
							.parseClaimsJws(token)
							.getBody()
							.get(MEMBER_ID_CLAIM_KEY, Long.class));
		} catch (Exception e) {
			log.warn("Failed to get memberId. token: {}", token);
			return Optional.empty();
		}
	}
}
