package com.wmp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import com.wmp.model.Employee;
import com.wmp.model.Skill;
import com.wmp.service.SolrService;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

/**
 * Created by Garrett Kizior on 5/29/2018.
 */

@Aspect
@Configuration
public class AfterAOPAspect {

	final int CREATED = 32;
	final int UPDATED = 36;
	final int DELETED = 31;
	final int GET = 39;

	int numMessages = 0;

	@Resource
	SolrService sService;

	@AfterReturning(value = "execution(* com.wmp.controller.EmployeeController.createEmployee(..))", returning = "returnValue")
	public void createEmployee(JoinPoint joinpoint, Object returnValue) {
		this.sService.updateIndex(((Employee) returnValue).getId());
		this.printMessage("EmployeeController", joinpoint.getSignature().getName(), CREATED);
	}

	@AfterReturning(value = "execution(* com.wmp.controller.SkillController.createSkills(..))", returning = "returnValue")
	public void createSkills(JoinPoint joinpoint, Object returnValue) {
		this.sService.updateIndex((long) returnValue);
		this.printMessage("SkillController", joinpoint.getSignature().getName(), CREATED);
	}

	@AfterReturning(value = "execution(* com.wmp.controller.EmployeeController.updateEmployee(..))", returning = "returnValue")
	public void updateEmployee(JoinPoint joinpoint, Object returnValue) {
		this.sService.updateIndex(((Employee) returnValue).getId());
		this.printMessage("EmployeeController", joinpoint.getSignature().getName(), UPDATED);
	}

	@AfterReturning(value = "execution(* com.wmp.controller.SkillController.deleteSkill(..))", returning = "returnValue")
	public void deleteSkill(JoinPoint joinpoint, Object returnValue) {
		this.sService.updateIndex((long) returnValue);
		this.printMessage("SkillController", joinpoint.getSignature().getName(), DELETED);
	}

	@AfterReturning(value = "execution(* com.wmp.controller.EmployeeController.deleteEmployee(..))", returning = "returnValue")
	public void deleteEmployee(JoinPoint joinpoint, Object returnValue) {
		this.sService.deleteIndex((long) returnValue);
		this.printMessage("EmployeeController", joinpoint.getSignature().getName(), DELETED);
	}

	@AfterReturning(value = "execution(* com.wmp.controller.SkillController.createSkillForEmps(..))", returning = "returnValue")
	public void createSkillForEmps(JoinPoint joinpoint, Object returnValue) {
		@SuppressWarnings("unchecked")
		List<Skill> skills = (List<Skill>) returnValue;
		for (Skill s : skills) {
			this.sService.updateIndex(s.getId());
		}
		this.printMessage("SkillController", joinpoint.getSignature().getName(), CREATED);
	}

	@AfterReturning(value = "execution(* com.wmp.controller.SkillController.createSkill(..))", returning = "returnValue")
	public void createSkill(JoinPoint joinpoint, Object returnValue) {
		this.printMessage("SkillController", joinpoint.getSignature().getName(), CREATED);
	}

	@AfterReturning(value = "execution(* com.wmp.controller.SkillController.updateSkillForEmps(..))", returning = "returnValue")
	public void updateSkillForEmps(JoinPoint joinpoint, Object returnValue) {
		@SuppressWarnings("unchecked")
		List<Skill> skills = (List<Skill>) returnValue;
		for (Skill s : skills) {
			this.sService.updateIndex(s.getId());
		}
		this.printMessage("SkillController", joinpoint.getSignature().getName(), UPDATED);
	}

	@AfterReturning(value = "execution(* com.wmp.controller.SkillController.removeSkillBySkill(..))", returning = "returnValue")
	public void removeSkillBySkill(JoinPoint joinpoint, Object returnValue) {
		@SuppressWarnings("unchecked")
		List<Skill> skills = (List<Skill>) returnValue;
		for (Skill s : skills) {
			this.sService.updateIndex(s.getId());
		}
		this.printMessage("SkillController", joinpoint.getSignature().getName(), DELETED);
	}

	@After("execution(* com.wmp.controller.SolrController.*(..))")
	public void beforeSolr(JoinPoint joinpoint) throws IOException {
		this.printMessage("SolrController", joinpoint.getSignature().getName(), GET);
	}

	@After("execution(* com.wmp.controller.EmployeeController.get*(..))")
	public void beforeGetEmployee(JoinPoint joinpoint) throws IOException {
		this.printMessage("EmployeeController", joinpoint.getSignature().getName(), GET);
	}

	@After("execution(* com.wmp.controller.SkillController.get*(..))")
	public void beforeGetSkill(JoinPoint joinpoint) throws IOException {
		this.printMessage("SkillController", joinpoint.getSignature().getName(), GET);
	}

	public void printMessage(String controller, String function, int color) {
		this.numMessages++;
		System.out.println((char) 27 + "[" + color + "m" + this.numMessages + "  " + controller + " - " + function
				+ (char) 27 + "[0m");
	}

}