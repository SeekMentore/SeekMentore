package com.model.query;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="queryNamespace")
public class QueryNamespace  implements Serializable {
	
	private static final long serialVersionUID = 5992835243262722575L;
	
	private ArrayList<Namespace> namespace;

	public ArrayList<Namespace> getNamespace() {
		return namespace;
	}

	@XmlElement
	public void setNamespace(ArrayList<Namespace> namespace) {
		this.namespace = namespace;
	}
}
