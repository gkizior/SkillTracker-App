package com.wmp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;


@Aspect
@Configuration
public class AfterAOPAspect {

	@After("execution(* com.wmp.controller.EmployeeController.*(..))")
	public void afte(JoinPoint joinpoint) throws IOException {
		System.out.print("Before ");
		System.out.println(joinpoint.getSignature().getName());
		
		URL url = new URL("http://localhost:8983/solr/skilltracker/dataimport?command=full-import");
		URLConnection conn = url.openConnection();
		conn.connect();
		System.out.print("- AFTER ");
	}

}