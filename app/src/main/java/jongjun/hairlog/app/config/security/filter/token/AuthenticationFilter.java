package jongjun.hairlog.app.config.security.filter.token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class AuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

	private static final Pattern PATTERN_AUTHORIZATION_HEADER = Pattern.compile("^[Bb]earer (.*)$");

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		return resolveAccessToken(request);
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return resolveAccessToken(request);
	}

	private String resolveAccessToken(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		if (authorization == null) {
			return null;
		}
		Matcher matcher = PATTERN_AUTHORIZATION_HEADER.matcher(authorization);
		if (!matcher.matches()) {
			return null;
		}
		return matcher.group(1);
	}
}
