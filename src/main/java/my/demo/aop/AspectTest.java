package my.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectTest implements Ordered{
	
	@Pointcut("@within(com.alibaba.dubbo.config.annotation.Service)")
	public void testInterceptor() {

	}
	
	
	
	@Around("testInterceptor()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("before "+pjp.getTarget().getClass().toString()+" "+pjp.getSignature().getName());
		Object obj = pjp.proceed();
		System.out.println("after "+pjp.getTarget().getClass().toString()+" "+pjp.getSignature().getName());
		
		return obj;
	}
	
	

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}

}
