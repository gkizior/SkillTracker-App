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
import com.wmp.model.Skill;
import com.wmp.service.SolrService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

	@AfterReturning(value = "execution(* com.wmp.controller.EmployeeController.createEmployee(..))", returning = "returnValue")
	public void createEmployee(JoinPoint joinpoint, Object returnValue) {
		this.sService.updateIndex(((Employee) returnValue).getId());
	}

	@AfterReturning(value = "execution(* com.wmp.controller.SkillController.createSkills(..))", returning = "returnValue")
	public void createSkills(JoinPoint joinpoint, Object returnValue) {
		this.sService.updateIndex((long) returnValue);
	}

	@AfterReturning(value = "execution(* com.wmp.controller.EmployeeController.updateEmployee(..))", returning = "returnValue")
	public void updateEmployee(JoinPoint joinpoint, Object returnValue) {
		this.sService.updateIndex(((Employee) returnValue).getId());
	}

	@AfterReturning(value = "execution(* com.wmp.controller.SkillController.deleteSkill(..))", returning = "returnValue")
	public void deleteSkill(JoinPoint joinpoint, Object returnValue) {
		this.sService.updateIndex((long) returnValue);
	}

	@AfterReturning(value = "execution(* com.wmp.controller.EmployeeController.deleteEmployee(..))", returning = "returnValue")
	public void deleteEmployee(JoinPoint joinpoint, Object returnValue) {
		this.sService.updateIndex((long) returnValue);
	}

	@AfterReturning(value = "execution(* com.wmp.controller.SkillController.createSkillForEmps(..))", returning = "returnValue")
	public void createSkillForEmps(JoinPoint joinpoint, Object returnValue) {
		@SuppressWarnings("unchecked")
		List<Skill> skills = (List<Skill>) returnValue;
		for (Skill s : skills) {
			this.sService.updateIndex(s.getId());
		}
	}

	@AfterReturning(value = "execution(* com.wmp.controller.SkillController.createSkill(..))", returning = "returnValue")
	public void createSkill(JoinPoint joinpoint, Object returnValue) {
		Skill skill = (Skill) returnValue;
		this.sService.updateIndex(skill.getId());
	}

	@AfterReturning(value = "execution(* com.wmp.controller.SkillController.updateSkillForEmps(..))", returning = "returnValue")
	public void updateSkillForEmps(JoinPoint joinpoint, Object returnValue) {
		@SuppressWarnings("unchecked")
		List<Skill> skills = (List<Skill>) returnValue;
		for (Skill s : skills) {
			this.sService.updateIndex(s.getId());
		}
	}

	@AfterReturning(value = "execution(* com.wmp.controller.SkillController.removeSkillBySkill(..))", returning = "returnValue")
	public void removeSkillBySkill(JoinPoint joinpoint, Object returnValue) {
		@SuppressWarnings("unchecked")
		List<Skill> skills = (List<Skill>) returnValue;
		for (Skill s : skills) {
			this.sService.updateIndex(s.getId());
		}
	}

	@Before("execution(* com.wmp.controller.SkillController.*(..))")
	public void beforeSkill(JoinPoint joinpoint) throws IOException {
		this.printMessage("SkillController", joinpoint.getSignature().getName());
	}

	@Before("execution(* com.wmp.controller.EmployeeController.*(..))")
	public void beforeEmployee(JoinPoint joinpoint) throws IOException {
		this.printMessage("EmployeeController", joinpoint.getSignature().getName());
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