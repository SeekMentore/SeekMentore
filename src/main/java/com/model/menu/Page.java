package com.model.menu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Page implements Serializable {
	
	private static final long serialVersionUID = -3015877697556180160L;
	private String id;
	private String url;
	private String pageAccessType;
	private ArrayList<Webservice> webservice;

	public String getId() {
		return id;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	public String getPageAccessType() {
		return pageAccessType;
	}

	@XmlAttribute
	public void setPageAccessType(String pageAccessType) {
		this.pageAccessType = pageAccessType;
	}
	
	public String getUrl() {
		return url;
	}

	@XmlAttribute
	public void setUrl(String url) {
		this.url = url;
	}
	
	public ArrayList<Webservice> getWebservice() {
		return webservice;
	}

	@XmlElement
	public void setWebservice(ArrayList<Webservice> webservice) {
		this.webservice = webservice;
	}
}
