package com.wmp.helper;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class EmpIds implements Serializable {
		List<Integer> empIds;
		String skill;

		public EmpIds(List<Integer> empIds, String skill) {
			this.empIds = empIds;
			this.skill = skill;
		}

		public EmpIds() {
			this.empIds = new ArrayList<Integer>();
			this.skill = "";
		}

		public List<Integer> getEmpIds() {
			return this.empIds;
		}

		public void setEmpIds(List<Integer> empIds) {
			this.empIds = empIds;
		}
		
		public String getSkill() {
			return this.skill;
		}
		
		public void setSkill(String skill) {
			this.skill = skill;
		}
	}