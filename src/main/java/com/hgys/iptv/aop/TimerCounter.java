package com.hgys.iptv.aop;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class TimerCounter {
	
	private long time1 = 0L;

	@Pointcut("execution(* com.hgys.iptv.controller..*.*(..))")
	public void timeCut(){}
	

	

	@Before("timeCut()")
	public void before () {
		time1 = System.currentTimeMillis();
	}
	
	@After("timeCut()")
	public void after () {
		long time2 = System.currentTimeMillis();
		System.err.println("访问接口所用时间为：" + (time2-time1));
	}
	
}
