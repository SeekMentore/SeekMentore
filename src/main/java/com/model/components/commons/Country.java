package com.model.components.commons;

import java.io.Serializable;

public class Country implements Serializable {

	private static final long serialVersionUID = -2232244327975645054L;
	
	private String code;
	private String name;
	private String description;
	
	public Country() {}
	
	public Country (
		String code,
		String name,
		String description
	) {
		this.code = code;
		this.name = name;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
