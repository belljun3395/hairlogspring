package jongjun.hairlog.app.support.aop;

import jongjun.hairlog.app.config.security.AuditorHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ValidateRequestMemberIdAspect {

	@Pointcut("@annotation(jongjun.hairlog.app.support.aop.ValidateRequestMemberId)")
	public void validateRequestMemberIdAnnotationPointCut() {}

	@Before("validateRequestMemberIdAnnotationPointCut()")
	public void validateAccountAdvice(JoinPoint joinPoint) {
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		ValidateRequestMemberId validateRequestMemberId =
				methodSignature.getMethod().getAnnotation(ValidateRequestMemberId.class);

		Integer requestMemberIdIdx = Integer.valueOf(validateRequestMemberId.requestMemberIdIdx());
		Object[] args = joinPoint.getArgs();

		Long requestMemberId = (Long) args[requestMemberIdIdx];
		Long auditId = AuditorHolder.get();

		if (!requestMemberId.equals(auditId)) {
			log.warn("not match request id : {}, audit id : {}", requestMemberId, auditId);
			throw new RuntimeException("request id and token id is not match");
		}
	}
}
