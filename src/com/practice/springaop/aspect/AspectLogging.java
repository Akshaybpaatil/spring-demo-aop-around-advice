package com.practice.springaop.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.practice.springaop.dao.Account;

@Aspect
@Component
@Order(1)
public class AspectLogging {

	@Pointcut("execution(* com.practice.springaop.dao.*.*(..))")
	public void forDAOPackages() {

	}

	// @Before("execution(public void addAccount())")
	// @Before("execution(public void
	// com.practice.springaop.dao.AccountDAO.addAccount())")
	// @Before("execution(public void add*())")
	// @Before("execution(* add*())")
	// @Before("execution(* add*(com.practice.springaop.dao.Account))")
	// @Before("execution(* add*(com.practice.springaop.dao.Account,..))")
	// @Before("execution(* add*(..))")
	@Before("forDAOPackages()")
	public void beforeAddAccountAdvice() {
		System.out.println("\n====>>> Executing @Before advice on addAccount()");
	}

	@Before("forDAOPackagesNotForGetterSetter()")
	public void afterAddAccountAdvice() {
		System.out.println("\n====>>> Executing Api Analystics");
	}

	@Pointcut("execution(* com.practice.springaop.dao.*.get*(..))")
	public void getter() {
	}

	@Pointcut("execution(* com.practice.springaop.dao.*.set*(..))")
	public void setter() {
	}

	@Pointcut("forDAOPackages() && !(getter()||setter())")
	public void forDAOPackagesNotForGetterSetter() {

	}

	@AfterReturning(pointcut = "execution(* com.practice.springaop.dao.AccountDAO.findAllAccounts(..))", returning = "account")
	public void afterReturningtheOutput(JoinPoint joinPoint, List<Account> account) {

		String string = joinPoint.getSignature().toShortString();

		System.out.println("--->>>>Executing the Joint Cut:" + string);

		System.out.println("Output of return Value:" + account);
	}

	@Around("execution(* com.practice.springaop.service.*.getFortune(..))")
	public Object aroundForFortune(ProceedingJoinPoint joinPoint) throws Throwable {

		String string = joinPoint.getSignature().toShortString();

		System.out.println("--->>>>Executing @Around Joint Cut:" + string);

		long begin = System.currentTimeMillis();

		Object reObject = joinPoint.proceed();

		long end = System.currentTimeMillis();

		long duration = end - begin;

		System.out.println("\n====>>> Duration:" + duration / 1000.0 + "seconds");

		return reObject;
	}
}
