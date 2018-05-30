package com.wmp.model;

import java.io.Serializable;

public class Skills implements Serializable {

	private String[] skills;

	public Skills() {
	}

	public Skills(String[] skills) {
		this.skills = skills;
	}

	public String[] getSkills() {
		return this.skills;
	}
	
	public void setSkills(String[] skills) {
		this.skills = skills;
	}
}
