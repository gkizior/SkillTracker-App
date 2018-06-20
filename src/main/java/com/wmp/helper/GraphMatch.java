package com.wmp.helper;

import java.io.Serializable;

/**
 * Created by Garrett Kizior on 6/6/2018.
 */

@SuppressWarnings("serial")
public class GraphMatch implements Serializable {
		String name;
		int value;

		public GraphMatch(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public GraphMatch() {
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

		@Override
		public boolean equals(Object o) {
			if (o instanceof GraphMatch) {
				GraphMatch newO = (GraphMatch) o;
				return this.name.equals(newO.getName());
			}
			return false;
		}
	}