package com.wmp.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class SeriesList implements Serializable {

		List<Series> listSeries;
		String name;

		public SeriesList(List<Series> listSeries, String name) {
			this.listSeries = listSeries;
			this.name = name;
		}

		public SeriesList() {
			this.listSeries = new ArrayList<Series>();
			this.name = null;
		}

		public List<Series> getListSeries() {
			return this.listSeries;
		}

		public void setListSeries(List<Series> listSeries) {
			this.listSeries = listSeries;
		}
		
		public String getName() {
			return this.name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
	}