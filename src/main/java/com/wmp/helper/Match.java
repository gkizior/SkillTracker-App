package com.wmp.helper;

import java.io.Serializable;
import com.wmp.model.Solr;

/**
 * Created by Garrett Kizior on 6/6/2018.
 */

@SuppressWarnings("serial")
public class Match implements Serializable, Comparable<Match> {
		Solr solr;
		double count;

		public Match(Solr solr, double count) {
			this.solr = solr;
			this.count = count;
		}

		public Match() {
			this.solr = null;
			this.count = 0;
		}

		public Solr getSolr() {
			return this.solr;
		}

		public double getCount() {
			return this.count;
		}

		public void setSolr(Solr solr) {
			this.solr = solr;
		}

		public void setCount(double count) {
			this.count = count;
		}

		@Override
		public int compareTo(Match o) {
			return (int) ((this.count * 100) - (o.count * 100));
		}
	}