package com.wmp.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * Created by Garrett Kizior on 5/22/2018.
 */

@SolrDocument(solrCoreName = "wmpskilltracker")
public class Skill {

	@Id
	@Field
	private String empId;

	@Field
	private String firstName;

	@Field
	private String lastName;

	@Field
	private String careerLevel;

	@Field
	private String[] skills;

	public Skill() {
	}

	public Skill(String empId, String firstName, String lastName, String careerLevel, String[] skills) {
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.careerLevel = careerLevel;
		this.skills = skills;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpId() {
		return this.empId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setCareerLevel(String careerLevel) {
		this.careerLevel = careerLevel;
	}

	public String getCareerLevel() {
		return this.careerLevel;
	}

	public void setSkills(String[] skills) {
		this.skills = skills;
	}

	public String[] getSkills() {
		return this.skills;
	}

	@Override
	public String toString() {
		return "Skill{" + "empId='" + empId + '\'' + ", firstName='" + firstName + '\'' + ", lastName='"
				+ lastName + '\'' + ", careerLevel='" + careerLevel + '\'' + ", skill='" + skills + '\'' + '}';
	}
	
	@Override
	public boolean equals(Object o) {
		Skill skill = (Skill)o;
		return this.empId.equals(skill.empId) ? true : false;
	}
}
