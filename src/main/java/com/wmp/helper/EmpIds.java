package com.wmp.helper;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class EmpIds implements Serializable {
		List<Integer> empIds;

		public EmpIds(List<Integer> empIds) {
			this.empIds = empIds;
		}

		public EmpIds() {
			this.empIds = new ArrayList<Integer>();
		}

		public List<Integer> getEmpIds() {
			return this.empIds;
		}

		public void setEmpIds(List<Integer> empIds) {
			this.empIds = empIds;
		}
	}