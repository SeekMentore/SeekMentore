package com.model.query;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

public class Namespace  implements Serializable {
	
	private static final long serialVersionUID = 3511351046667431853L;
	
	private String name;
	private String mapperFilePath;
	
	public String getName() {
		return name;
	}
	
	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}

	public String getMapperFilePath() {
		return mapperFilePath;
	}

	@XmlAttribute
	public void setMapperFilePath(String mapperFilePath) {
		this.mapperFilePath = mapperFilePath;
	}

}
