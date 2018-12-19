package com.model;

import java.io.Serializable;

public class SelectModel implements Serializable {

	private static final long serialVersionUID = 2967707363641587556L;
	
	private String label;
	private String value;
	
	public SelectModel() {}
	
	public SelectModel(final String label, final String value) {
		this.setLabel(label);
		this.setValue(value);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
