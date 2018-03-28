package com.model.menu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="menu")
public class Menu implements Serializable {
	
	private static final long serialVersionUID = -3895008639944505989L;
	private ArrayList<PageList> pageList;

	public ArrayList<PageList> getPageList() {
		return pageList;
	}

	@XmlElement
	public void setPageList(ArrayList<PageList> pageList) {
		this.pageList = pageList;
	}
}
