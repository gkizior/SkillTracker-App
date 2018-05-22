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
	private String emp_id;

	@Field
	private String first_name;

	@Field
	private String last_name;

	@Field
	private String career_level;

	@Field
	private String skill;

	public Skill() {
	}

	public Skill(String emp_id, String first_name, String last_name, String career_level, String skill) {
		this.emp_id = emp_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.career_level = career_level;
		this.skill = skill;
	}

	public void setEmpId(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmpId() {
		return this.emp_id;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getFirstName() {
		return this.first_name;
	}
	
	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getLastName() {
		return this.last_name;
	}
	
	public void setCareerLevel(String career_level) {
		this.career_level = career_level;
	}

	public String getCareerLevel() {
		return this.career_level;
	}
	
	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getSkill() {
		return this.skill;
	}

	@Override
	public String toString() {
		return "Skill{" + 
				"emp_id='" + emp_id + '\'' + 
				", first_name='" + first_name + '\'' + 
				", last_name='" + last_name + '\'' +
				", career_level='" + career_level + '\'' +
				", skill='" + skill + '\'' + '}';
	}
}
