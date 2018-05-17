package com.model.menu;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

public class Webservice implements Serializable {
	
	private static final long serialVersionUID = -5994253536462810268L;
	private String id;
	private String url;
	private String additionalAccessFunction;
	private String pageAccessType;
	
	public String getId() {
		return id;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	@XmlAttribute
	public void setUrl(String url) {
		this.url = url;
	}

	public String getAdditionalAccessFunction() {
		return additionalAccessFunction;
	}

	@XmlAttribute
	public void setAdditionalAccessFunction(String additionalAccessFunction) {
		this.additionalAccessFunction = additionalAccessFunction;
	}
	
	public String getPageAccessType() {
		return pageAccessType;
	}

	@XmlAttribute
	public void setPageAccessType(String pageAccessType) {
		this.pageAccessType = pageAccessType;
	}
}
