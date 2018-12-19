package com.model.bouipath;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="bouipath")
public class BOUIPath implements Serializable {
	
	private static final long serialVersionUID = -3895008639944505989L;
	private ArrayList<ViewType> viewtype;

	public ArrayList<ViewType> getViewtype() {
		return viewtype;
	}

	@XmlElement
	public void setViewtype(ArrayList<ViewType> viewtype) {
		this.viewtype = viewtype;
	}
}
