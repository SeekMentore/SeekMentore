package com.model.bouipath;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class UserType implements Serializable {
	
	private static final long serialVersionUID = -3015877697556180160L;
	private String id;
	private String url;
	private String pageAccessType;
	private Boolean urlActive;
	private String additionalAccessFunction;
	private ArrayList<MenuItem> menuitem;
	private ArrayList<UIPage> uipage;

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
	
	public ArrayList<MenuItem> getMenuitem() {
		return menuitem;
	}

	@XmlElement
	public void setMenuitem(ArrayList<MenuItem> menuitem) {
		this.menuitem = menuitem;
	}
	
	public Boolean getUrlActive() {
		return urlActive;
	}

	@XmlAttribute
	public void setUrlActive(Boolean urlActive) {
		this.urlActive = urlActive;
	}
	
	public ArrayList<UIPage> getUipage() {
		return uipage;
	}

	@XmlElement
	public void setUipage(ArrayList<UIPage> uipage) {
		this.uipage = uipage;
	}
	
	public String getAdditionalAccessFunction() {
		return additionalAccessFunction;
	}

	@XmlAttribute
	public void setAdditionalAccessFunction(String additionalAccessFunction) {
		this.additionalAccessFunction = additionalAccessFunction;
	}
}
