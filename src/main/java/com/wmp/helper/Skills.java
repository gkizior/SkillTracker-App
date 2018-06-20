package com.wmp.helper;

import java.io.Serializable;

/**
 * Created by Garrett Kizior on 5/30/2018.
 */

@SuppressWarnings("serial")
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
