package com.wmp.helper;

import java.io.Serializable;

/**
 * Created by Garrett Kizior on 6/6/2018.
 */

@SuppressWarnings("serial")
public class Series implements Serializable {

		String name;
		int value;

		public Series(String careerLevel, int i) {
			this.name = careerLevel;
			this.value = i;
		}

		public Series() {
			this.name = null;
			this.value = 0;
		}

		public String getName() {
			return this.name;
		}

		public int getValue() {
			return this.value;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}