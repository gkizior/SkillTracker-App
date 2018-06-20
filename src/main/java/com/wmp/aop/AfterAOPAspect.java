package com.wmp.aop;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.solr.client.solrj.SolrServer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import com.wmp.model.Employee;
import com.wmp.service.SolrService;

import java.io.IOException;

import javax.annotation.Resource;

/**
 * Created by Garrett Kizior on 5/29/2018.
 */

@SuppressWarnings("deprecation")
@Aspect
@Configuration
public class AfterAOPAspect {
	
	int beforeMessages = 0;
	
	@Resource
	SolrService sService;

	@After("execution(* com.wmp.controller.EmployeeController.*(..))")
	public void afterEmployee(JoinPoint joinpoint) throws IOException {
//		if(joinpoint.getSignature().getName().contains("get")) return;
//		System.out.println("EmployeeController - " + joinpoint.getSignature().getName());
//		DefaultHttpClient client = new DefaultHttpClient();
//		client.execute(new HttpGet("http://localhost:8983/solr/skilltracker/dataimport?command=full-import"));
//		client.getConnectionManager().shutdown();
//		client.close();
	}

	@After("execution(* com.wmp.controller.SkillController.*(..))")
	public void afterSkill(JoinPoint joinpoint) throws IOException {
//		if(joinpoint.getSignature().getName().contains("get")) return;
//		System.out.println("SKillController - " + joinpoint.getSignature().getName());
//		DefaultHttpClient client = new DefaultHttpClient();
//		client.execute(new HttpGet("http://localhost:8983/solr/skilltracker/dataimport?command=full-import"));
//		client.getConnectionManager().shutdown();
//		client.close();
	}
	
	@AfterReturning(value = "execution(* com.wmp.controller.EmployeeController.createEmployee(..))", returning = "returnValue")
	public void createEmployee(JoinPoint joinpoint, Object returnValue) {
		this.sService.addToIndex(((Employee) returnValue).getId());
	}
	
	@AfterReturning(value = "execution(* com.wmp.controller.SkillController.createSkills(..))", returning = "returnValue")
	public void createSkills(JoinPoint joinpoint, Object returnValue) {
		System.out.println(joinpoint.getArgs());
		System.out.println("");
		this.sService.addToIndex((long) returnValue);
	}
	
	@Before("execution(* com.wmp.controller.SkillController.*(..))")
	public void beforeSkill(JoinPoint joinpoint) throws IOException {
		this.printMessage("SkillController  ----", joinpoint.getSignature().getName());
	}
	
	@Before("execution(* com.wmp.controller.EmployeeController.*(..))")
	public void beforeEmployee(JoinPoint joinpoint) throws IOException {
		this.printMessage("EmployeeController  ====", joinpoint.getSignature().getName());
	}
	
	@Before("execution(* com.wmp.controller.SolrController.*(..))")
	public void beforeSolr(JoinPoint joinpoint) throws IOException {
		this.printMessage("SolrController", joinpoint.getSignature().getName());
	}
	
	public void printMessage(String controller, String function) {
		this.beforeMessages++;
		System.out.println(this.beforeMessages + "  " + controller + " - " + function);
	}

}