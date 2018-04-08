package com.service.components;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.components.CommonsConstants;
import com.dao.ApplicationDao;
import com.model.components.commons.SelectLookup;

@Service(BeanConstants.BEAN_NAME_COMMONS_SERVICE)
public class CommonsService implements CommonsConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@PostConstruct
	public void init() {}
	
	@Transactional
	public void feedErrorRecord(final Timestamp occurredTimestamp, final String requestUri, final String errorText) {
		applicationDao.updateWithPreparedQueryAndIndividualOrderedParams(
				"INSERT INTO APP_ERROR_REPORT(OCCURED_AT, REQUEST_URI, ERROR_TRACE) VALUES(?, ?, ?)", new Object[] {occurredTimestamp, requestUri, errorText});
	}
	
	@Transactional
	public List<SelectLookup> getSelectLookupList(final String selectLookUpTable) {
		return applicationDao.findAllWithoutParams("SELECT * FROM SELECT_LOOKUP_TABLE_NAME ORDER BY ORDER_OF_CATEGORY, ORDER_IN_CATEGORY".replaceAll("SELECT_LOOKUP_TABLE_NAME", selectLookUpTable), SelectLookup.class);
	}
}
