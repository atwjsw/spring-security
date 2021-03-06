package com.imooc.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class TimeAspect {

	@Around("execution(* com.imooc.web.controller.UserController.*(..))") 
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("time aspect start");
		
		Object[] args = pjp.getArgs();
		for (Object obj: args) {
			System.out.println("arg is: " + obj);
		}
		long start = new Date().getTime();
		Object object = pjp.proceed();
		System.out.println("time aspect 耗时" + (new Date().getTime() - start));
		System.out.println("time aspect endt");
		
		return object;
		
	}
	
}
