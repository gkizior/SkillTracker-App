package com.wmp.helper;

import java.io.Serializable;

/**
 * Created by Garrett Kizior on 6/7/2018.
 */

@SuppressWarnings("serial")
public class CareerLevelAndSkill implements Serializable {
		String careerLevel;
		String skill;

		public CareerLevelAndSkill(String careerLevel, String skill) {
			this.careerLevel = careerLevel;
			this.skill = skill;
		}

		public CareerLevelAndSkill() {
			this.careerLevel = null;
			this.skill = null;
		}

		public String getCareerLevel() {
			return this.careerLevel;
		}

		public String getSkill() {
			return this.skill;
		}

		public void setCareerLevel(String careerLevel) {
			this.careerLevel = careerLevel;
		}

		public void setSkill(String skill) {
			this.skill = skill;
		}
	}