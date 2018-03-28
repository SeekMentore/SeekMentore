package com.service.components;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.components.CommonsConstants;
import com.dao.ApplicationDao;

@Service(BeanConstants.BEAN_NAME_COMMONS_SERVICE)
public class CommonsService implements CommonsConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@PostConstruct
	public void init() {}
	
	@Transactional
	public void testJDBCConnection() {
		applicationDao.updateWithPreparedQueryAndIndividualOrderedParams("INSERT INTO country (COUNTRY_CODE, COUNTRY_NAME, COUNTRY_DESC) VALUES (?, ?, ?)", "IND1","India","My Country");
	}
}
