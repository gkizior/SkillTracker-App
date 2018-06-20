package com.wmp.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.wmp.model.Solr;

/**
 * Created by Garrett Kizior on 6/7/2018.
 */

@SuppressWarnings("serial")
public class SeriesList implements Serializable {

		List<Series> listSeries;
		String name;
		List<Solr> emps;

		public SeriesList(List<Series> listSeries, String name, List<Solr> emps) {
			this.listSeries = listSeries;
			this.name = name;
			this.emps = emps;
		}

		public SeriesList() {
			this.listSeries = new ArrayList<Series>();
			this.name = null;
			this.emps = new ArrayList<Solr>();
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
		
		public List<Solr> getEmps() {
			return this.emps;
		}
		
		public void setEmps(List<Solr> emps) {
			this.emps = emps;
		}
	}