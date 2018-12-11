package com.model.query;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import com.constants.ApplicationConstants;
import com.constants.QueryMapperConstants;
import com.exception.ApplicationException;

public class Query  implements Serializable, ApplicationConstants {
	
	private static final long serialVersionUID = 3511351046667431853L;
	
	private String id;
	private String type;
	private String description;
	private String paramClass;
	
	public String getId() {
		return id;
	}

	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	@XmlAttribute
	public void setType(String type) {
		this.type = type;
	}
	
	public String getParamClass() {
		return paramClass;
	}

	@XmlAttribute
	public void setParamClass(String paramClass) {
		this.paramClass = paramClass;
	}

	public String getDescription() {
		return description;
	}

	@XmlValue
	public void setDescription(String description) {
		this.description = WHITESPACE + getFormattedSQL(description) + WHITESPACE;
	}
	
	public String getSQL(final String engineName) {
		switch(engineName) {
			case QueryMapperConstants.ENGINE_NAME_MYSQL : return this.description;
			default : {
				throw new ApplicationException("No SQL found for Engine : " + engineName);
			}
		}
	}
	
	private String getFormattedSQL(final String querySQL) {
		return querySQL.trim()
					.replaceAll("\\n", " ")
					.replaceAll("\\r", " ")
					.replaceAll("\\t", " ")
					.replaceAll("^ +| +$|( )+", "$1");
	}
}
