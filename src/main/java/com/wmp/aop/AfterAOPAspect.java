package com.wmp.aop;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
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

	@SuppressWarnings("deprecation")
	@After("execution(* com.wmp.controller.EmployeeController.*(..))")
	public void afterEmployee(JoinPoint joinpoint) throws IOException {
		System.out.println("EmployeeController - " + joinpoint.getSignature().getName());
		DefaultHttpClient client = new DefaultHttpClient();
		client.execute(new HttpGet("http://localhost:8983/solr/skilltracker/dataimport?command=full-import"));
		client.getConnectionManager().shutdown();
		client = null;
	}
	
	@SuppressWarnings("deprecation")
	@After("execution(* com.wmp.controller.SkillController.*(..))")
	public void afterSkill(JoinPoint joinpoint) throws IOException {
		System.out.println("SKillController - " + joinpoint.getSignature().getName());
		DefaultHttpClient client = new DefaultHttpClient();
		client.execute(new HttpGet("http://localhost:8983/solr/skilltracker/dataimport?command=full-import"));
		client.getConnectionManager().shutdown();
		client = null;
	}

}