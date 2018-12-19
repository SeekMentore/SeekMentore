package com.model.query;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="queryMapper")
public class QueryMapper  implements Serializable {
	
	private static final long serialVersionUID = 5992835243262722575L;
	
	private ArrayList<Query> query;
	private String namespace;

	public ArrayList<Query> getQuery() {
		return query;
	}

	@XmlElement
	public void setQuery(ArrayList<Query> query) {
		this.query = query;
	}

	public String getNamespace() {
		return namespace;
	}

	@XmlAttribute
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
}
