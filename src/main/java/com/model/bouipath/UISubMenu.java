package com.model.bouipath;

import java.io.Serializable;

public class UISubMenu implements Serializable, Cloneable {
	
	private static final long serialVersionUID = -8666630836973456529L;
	
	private String name;
	private String url;
	private String pageAccessType;
	private Integer order;
	
	public UISubMenu() {}
	
	public UISubMenu(final String name, final String url, final String pageAccessType, final Integer order) {
		this.name = name;
		this.url = url;
		this.pageAccessType = pageAccessType;
		this.order = order;
	}
	
	public UISubMenu cloneForSendingToBOUI() throws CloneNotSupportedException {
		final UISubMenu newInstance = clone();
		newInstance.pageAccessType = null;
		newInstance.order = null;
		return newInstance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPageAccessType() {
		return pageAccessType;
	}

	public void setPageAccessType(String pageAccessType) {
		this.pageAccessType = pageAccessType;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	@Override
	public UISubMenu clone() throws CloneNotSupportedException {  
		return (UISubMenu)super.clone();
	}
}
