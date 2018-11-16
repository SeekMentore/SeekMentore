package com.model.bouipath;

import java.io.Serializable;

public class UISubMenu implements Serializable {
	
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
	
	public UISubMenu getACopyForSendingToBOUI() {
		final UISubMenu newInstance = new UISubMenu();
		newInstance.name = this.name;
		newInstance.url = this.url;
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
}
