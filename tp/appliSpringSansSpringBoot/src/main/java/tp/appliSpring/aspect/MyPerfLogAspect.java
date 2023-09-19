package tp.appliSpring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Profile("perf")
public class MyPerfLogAspect {

	@Pointcut("execution(* tp.appliSpring.exemple.*.*(..))")
	public void surPackageExemple() {}
	
	@Pointcut("execution(* tp.appliSpring.service.*.*(..))")
	public void surPackageService() {}
	
	
	@Around("surPackageExemple() || surPackageService()")
	public Object doPerfLog(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("<< trace == debut == " + pjp.getSignature().toLongString() + " <<");
		long td = System.nanoTime();
		Object objRes = pjp.proceed();
		long tf = System.nanoTime();
		System.out.println(
				">> trace == fin == " + pjp.getSignature().toShortString() + " [" + (tf - td) / 1000000.0 + " ms] >>");
		return objRes;
	}
}
