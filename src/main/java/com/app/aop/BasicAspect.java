/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Aspect
public class BasicAspect {

	@Pointcut("execution(* com.app..services.*.*(..))")
	public void pointcut1() {}
	
	@Pointcut("within(* com.app.controllers.*.*(..))")
	//@Around("@annotation(EnableAspectLogger)")
	public void pointcut2() {}

	//Advices
	@Before("execution(* com.app..services.*.*(..))")
	public void beforeAdvice1(JoinPoint jp) {
//		System.out.println("Basic - Before Advice");
	}
	
	@After("execution(* com.app..services.*.*(..))")
	public void afterAdvice1() {
//		System.out.println("Basic - After Advice");
	}

	@AfterReturning(pointcut = "execution(* com.app..services.*.*(..))", returning = "retVal")
	public void afterReturningAdvice1(Object retVal) {
//		System.out.println("Basic - AfterReturning Advice");
	}

	@AfterThrowing(pointcut = "execution(* com.app..services.*.*(..))", throwing = "ex")
	public void afterThrowingAdvice1(Exception ex) {
//		System.out.println("Basic - AfterThrowing Advice");
	}

	
	@Around("execution(* com.app..services.*.*(..))")
	public Object aroundAdvice1(ProceedingJoinPoint pjp) throws Throwable {
//		System.out.println("Basic - Around Advice");
		Object retVal = pjp.proceed();
		return(retVal);
	}
	
	@Before("execution(* com.app.controllers.*.*(..))")
	public void beforeAdvice2(JoinPoint jp) {
//		Object ob1 = jp.getTarget();
//		Object ob2 = jp.getThis();
//		System.out.println("Basic - Controllers Before Advice");
	}
	
}
