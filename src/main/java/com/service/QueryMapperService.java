package com.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.constants.QueryMapperConstants;

@Service(BeanConstants.BEAN_NAME_QUERY_MAPPER_SERVICE)
public class QueryMapperService implements QueryMapperConstants {
	
	//private Map<String, Map<String, String>> queryMap;
	
	@PostConstruct
	public void init() {
		//queryMap = new HashMap<String, Map<String, String>>();
	}
}
