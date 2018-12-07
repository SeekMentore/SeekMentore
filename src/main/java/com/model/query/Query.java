package com.model.query;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.constants.QueryMapperConstants;
import com.exception.ApplicationException;

public class Query  implements Serializable {
	
	private static final long serialVersionUID = 3511351046667431853L;
	
	private String id;
	private String type;
	private String mysqlSql;
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

	public String getMysqlSql() {
		return mysqlSql;
	}

	@XmlElement
	public void setMysqlSql(String mysqlSql) {
		this.mysqlSql = getFormattedSQL(mysqlSql);
	}
	
	public String getSQL(final String engineName) {
		switch(engineName) {
			case QueryMapperConstants.ENGINE_NAME_MYSQL : return this.mysqlSql;
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
