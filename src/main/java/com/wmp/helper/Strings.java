package com.wmp.helper;

import java.io.Serializable;

/**
 * Created by Garrett Kizior on 6/15/2018.
 */

@SuppressWarnings("serial")
public class Strings implements Serializable {

	private String[] strings;

	public Strings() {
	}

	public Strings(String[] strings) {
		this.strings = strings;
	}

	public String[] getStrings() {
		return this.strings;
	}

	public void setStrings(String[] strings) {
		this.strings = strings;
	}
}
