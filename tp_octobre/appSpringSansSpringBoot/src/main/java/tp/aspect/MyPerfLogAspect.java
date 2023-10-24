package tp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/*
 NB: configuration nécessaire :
 placer @EnableAspectJAutoProxy à coté d'une des classes de configuration 
 (celle qui est utilisée au démarrage)
 et
 @ComponentScan(basePackages = { "tp.aspect" , "..." })
 avec dépendances spring-aspects et speing-context dans pom.xml
 */


@Aspect
@Component
@Profile("perf")  //l'application de cet aspct ne sera effectif
//que si le profil "perf" est activé au démarrage de l'application 
//via System.setProperty("spring.profiles.active", "V2,perf");
public class MyPerfLogAspect {
	
	@Pointcut("execution(* tp.appliSpring.core.service.*.*(..))")
	public void servicePointcut(){ 
	}
	
	@Pointcut("execution(* tp.appliSpring.core.dao.*.*(..))")
	public void daoPointcut(){ 
	}
	
	@Pointcut("execution(* tp.appliSpring.exemplev2.*.*(..))")
	public void exemplePointcut(){ 
	}
	
	//@within pour annotation @LogExecutionTime (avec @Target(ElementType.TYPE) )
	//placée sur l'ensemble d'une classe 
	@Pointcut("@within(tp.annot.LogExecutionTime)")
	public void annotLogExecutionTimePointcut(){ 
	} 
	
	@AfterThrowing (pointcut = "exemplePointcut()", throwing = "ex" )
	public void logAfterThrowingAllServiceMethodsAdvice(JoinPoint jp , Exception ex ) throws Throwable 
	{
	 //System.out.println("**** MyPerfLogAspect" + ex + " in " +jp.getSignature().toLongString());
	 throw new RuntimeException("message perso " + ex.getMessage());
	}


	
	//@Around("servicePointcut() || daoPointcut() || exemplePointcut() ")
	@Around("(servicePointcut() || daoPointcut() || exemplePointcut() ) && annotLogExecutionTimePointcut()")
	public Object doXxxLog(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("<< trace == debut == " + pjp.getSignature().toLongString() + " <<");
		long td = System.nanoTime();
		Object objRes = pjp.proceed();
		long tf = System.nanoTime();
		System.out.println(
				">> trace == fin == " + pjp.getSignature().toShortString() + " [" + (tf - td) / 1000000.0 + " ms] >>");
		return objRes;
	}
}
