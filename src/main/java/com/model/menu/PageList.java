package com.model.menu;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class PageList implements Serializable {
	
	private static final long serialVersionUID = 6450248060325745505L;
	private String id;
	private ArrayList<Page> page;

    public ArrayList<Page> getPage() {
       return page;
    }

    @XmlElement
    public void setPage(ArrayList<Page> page) {
        this.page = page;
    }

	public String getId() {
		return id;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}
}
