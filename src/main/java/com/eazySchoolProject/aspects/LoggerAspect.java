package com.eazySchoolProject.aspects;

import java.time.Duration;
import java.time.Instant;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Aspect

@Slf4j
@Component
public class LoggerAspect {
	
	@Around("execution(* com.eazySchoolProject..*.*(..))")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
		
		log.info(joinPoint.getSignature().toString()+" method execution start");
		Instant start = Instant.now();
		Object returnObject = joinPoint.proceed();
		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toMillis();
		log.info("Time took to execute "+joinPoint.getSignature().toString()+ " method was "+timeElapsed);
		log.info(joinPoint.getSignature().toString()+" method execution end");
		return returnObject;
		
	}
	
	@AfterThrowing(value = "execution(* com.eazySchoolProject..*.*(..))",throwing = "ex")
	public void logException(JoinPoint joinPoint, Exception ex) {
		log.error("An exception has occurred in "+joinPoint.getSignature()+" due to"+ex.getMessage());
	}
	
	
}
