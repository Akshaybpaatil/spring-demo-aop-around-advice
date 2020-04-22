package com.practice.springaop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.practice.springaop.service.TrafficFortuneService;

public class AroundAOPMain {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AOPConfig.class,
				MyLoggerConfig.class);

		TrafficFortuneService traffic = context.getBean("trafficFortuneService", TrafficFortuneService.class);

		String data = traffic.getFortune();

		System.out.println("My fortune:" + data);

		context.close();
	}

}
