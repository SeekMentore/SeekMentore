package com.model.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import org.apache.commons.lang.StringUtils;

import com.constants.ApplicationConstants;
import com.constants.QueryMapperConstants;
import com.exception.ApplicationException;
import com.utils.ValidationUtils;

public class Query  implements Serializable, ApplicationConstants {
	
	private static final long serialVersionUID = 3511351046667431853L;
	
	private String id;
	private String type;
	private String description;
	private String paramClass;
	private List<String> dynamicReplacementPlaceHolderList;
	private Boolean hasDynamicPlaceHolders = false;
	
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
	
	public List<String> getDynamicReplacementPlaceHolderList() {
		return dynamicReplacementPlaceHolderList;
	}
	
	public Boolean getHasDynamicPlaceHolders() {
		return hasDynamicPlaceHolders;
	}

	public String getSQL(final String engineName) {
		switch(engineName) {
			case QueryMapperConstants.ENGINE_NAME_MYSQL : return this.description;
			default : {
				throw new ApplicationException("No SQL found for Engine : " + engineName);
			}
		}
	}
	
	private String getFormattedSQL(String querySQL) {
		querySQL = querySQL.trim()
					.replaceAll("\\n", " ")
					.replaceAll("\\r", " ")
					.replaceAll("\\t", " ")
					.replaceAll("^ +| +$|( )+", "$1");
		if (querySQL.indexOf("$") != -1) {
			final String dynamicReplacementPlaceHolderArray[] = StringUtils.substringsBetween(querySQL, "$", "$");
			if (ValidationUtils.checkNonEmptyArray(dynamicReplacementPlaceHolderArray)) {
				final Set<String> dynamicReplacementPlaceHolderSet = new HashSet<String>(Arrays.asList(dynamicReplacementPlaceHolderArray));
				this.dynamicReplacementPlaceHolderList = new ArrayList<String>(dynamicReplacementPlaceHolderSet);
				this.hasDynamicPlaceHolders = true;
			}
		}
		return querySQL;
	}
}
