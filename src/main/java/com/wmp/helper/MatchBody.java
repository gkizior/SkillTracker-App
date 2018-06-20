package com.wmp.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Garrett Kizior on 6/6/2018.
 */

@SuppressWarnings("serial")
public class MatchBody implements Serializable {
		int number;
		List<String> skills;

		public MatchBody(int number, List<String> skills) {
			this.number = number;
			this.skills = skills;
		}

		public MatchBody() {
			this.number = 0;
			this.skills = new ArrayList<>();
		}

		public int getNumber() {
			return this.number;
		}

		public List<String> getSkills() {
			return this.skills;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public void setSkills(List<String> skills) {
			this.skills = skills;
		}
	}