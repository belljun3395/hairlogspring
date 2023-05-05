package jongjun.hairlog.app.config.security;

import jongjun.hairlog.app.config.security.filter.token.AuthProvider;
import jongjun.hairlog.app.config.security.filter.token.AuthenticationFilter;
import jongjun.hairlog.app.config.security.handler.DelegatedAccessDeniedHandler;
import jongjun.hairlog.app.config.security.handler.DelegatedAuthenticationEntryPoint;
import jongjun.hairlog.app.support.token.TokenResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final DelegatedAuthenticationEntryPoint authenticationEntryPoint;
	private final DelegatedAccessDeniedHandler accessDeniedHandler;
	private final AuthProvider authProvider;
	private final TokenResolver tokenResolver;

	@Bean
	public SecurityFilterChain localSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.formLogin().disable();
		http.httpBasic().disable();
		http.authorizeRequests()
				.antMatchers(
						HttpMethod.GET,
						"/swagger-ui/*",
						"/api-docs/*",
						"/openapi3.yaml",
						"/actuator/health",
						"/error")
				.permitAll()
				.antMatchers(HttpMethod.POST, "/api/v1/members/login", "/api/v1/members/tokens")
				.permitAll()
				.antMatchers("/api/v1/**")
				.authenticated()
				.anyRequest()
				.denyAll();

		http.addFilterAt(
				generateAuthenticationFilter(), AbstractPreAuthenticatedProcessingFilter.class);

		http.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint)
				.accessDeniedHandler(accessDeniedHandler);

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}

	public AuthenticationFilter generateAuthenticationFilter() {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter();
		authenticationFilter.setAuthenticationManager(new ProviderManager(authProvider));
		return authenticationFilter;
	}
}
