package com.wmp.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * Created by Garrett Kizior on 5/22/2018.
 */

@SolrDocument(solrCoreName = "skilltracker")
public class Solr {

	@Id
	@Field()
	private String Id;

	@Field
	private String firstName;

	@Field
	private String lastName;

	@Field
	private String dateOfBirth;

	@Field
	private String dateOfJoin;

	@Field
	private String address;

	@Field
	private String careerLevel;

	@Field
	private String city;

	@Field
	private String state;

	@Field
	private String zipcode;

	@Field
	private String created;

	@Field
	private String updated;

	@Field
	private String[] skills;

	@Field
	private String cfname;

	@Field
	private String[] skillsNoSpaces;

	public Solr() {
	}

	public Solr(String Id, String firstName, String lastName, String careerLevel, String address, String city,
			String state, String zipcode, String created, String updated, String[] skills, String cfname,
			String[] skillsNoSpaces) {
		this.Id = Id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.careerLevel = careerLevel;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.created = created;
		this.updated = updated;
		this.skills = skills;
		this.cfname = cfname;
		this.skillsNoSpaces = skillsNoSpaces;
	}

	public String getEmpId() {
		return this.Id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getCareerLevel() {
		return this.careerLevel;
	}

	public String getAddress() {
		return this.address;
	}

	public String getCity() {
		return this.city;
	}

	public String getState() {
		return this.state;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public String getCreatedAt() {
		return this.created;
	}

	public String getUpdatedAt() {
		return this.updated;
	}

	public String getDateOfBirth() {
		return this.dateOfBirth;
	}

	public String getDateOfJoin() {
		return this.dateOfJoin;
	}

	public String[] getSkills() {
		return this.skills;
	}

	public String getCfname() {
		return this.cfname;
	}

	public String[] getSkillsNoSpaces() {
		return this.skillsNoSpaces;
	}

	@Override
	public String toString() {
		return "Skill{" + "empId='" + Id + '\'' + ", FirstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
				+ ", careerLevel='" + careerLevel + '\'' + ", skills='" + skills + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		Solr Skill = (Solr) o;
		return this.Id.equals(Skill.Id) ? true : false;
	}
}
