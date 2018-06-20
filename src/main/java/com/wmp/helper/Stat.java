package com.wmp.helper;

import com.wmp.helper.Series;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Garrett Kizior on 6/6/2018.
 */

@SuppressWarnings("serial")
public class Stat implements Serializable {
		String name;
		List<Series> series;

		public Stat(String name, List<Series> series) {
			this.name = name;
			this.series = series;
		}

		public Stat() {
			this.name = null;
			this.series = new ArrayList<Series>();
		}

		public String getName() {
			return this.name;
		}

		public List<Series> getSeries() {
			return this.series;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setSeries(List<Series> series) {
			this.series = series;
		}
	}