package com.wmp.helper;

import java.io.Serializable;

/**
 * Created by Garrett Kizior on 6/7/2018.
 */

@SuppressWarnings("serial")
public class StringBody implements Serializable {

		String name;

		public StringBody(String careerLevel) {
			this.name = careerLevel;
		}

		public StringBody() {
			this.name = null;
		}

		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}