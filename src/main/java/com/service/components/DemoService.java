package com.service.components;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.RestMethodConstants;
import com.constants.components.EnquiryConstants;
import com.constants.components.SelectLookupConstants;
import com.dao.ApplicationDao;
import com.model.User;
import com.model.components.Enquiries;
import com.model.components.RegisteredTutor;
import com.model.components.SubscribedCustomer;
import com.model.components.TutorMapper;
import com.model.components.commons.SelectLookup;
import com.model.rowmappers.EnquiryObjectRowMapper;
import com.model.rowmappers.RegisteredTutorRowMapper;
import com.model.rowmappers.SubscribedCustomerRowMapper;
import com.model.rowmappers.TutorMapperRowMapper;
import com.service.JNDIandControlConfigurationLoadService;
import com.utils.MailUtils;
import com.utils.ValidationUtils;
import com.utils.VelocityUtils;

@Service(BeanConstants.BEAN_NAME_DEMO_SERVICE)
public class DemoService implements EnquiryConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@Autowired
	private transient CustomerService customerService;
	
	@Autowired
	private transient CommonsService commonsService;
	
	@Autowired
	private transient TutorService tutorService;
	
	@Autowired
	private JNDIandControlConfigurationLoadService jndiAndControlConfigurationLoadService;
	
	@PostConstruct
	public void init() {}
	
	@Transactional
	public List<SubscribedCustomer> displayDemos(final String grid, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		final StringBuilder query = new StringBuilder("SELECT * FROM DEMO_TRACKER D WHERE D.DEMO_STATUS = :demoStatus)");
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_PENDING_ENQUIRIES : {
				paramsMap.put("matchStatus", MATCH_STATUS_PENDING);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_MAPPED_ENQUIRIES : {
				paramsMap.put("matchStatus", MATCH_STATUS_MAPPED);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_CUSTOMER_WITH_ABANDONED_ENQUIRIES : {
				paramsMap.put("matchStatus", MATCH_STATUS_ABANDONED);
				break;
			}
		}
		final List<SubscribedCustomer> customersEnquiryList = applicationDao.findAll(query.toString(), paramsMap, new SubscribedCustomerRowMapper());
		for (final SubscribedCustomer customersEnquiryObject : customersEnquiryList) {
			// Get all lookup data and user ids back to original label and values
			customerService.removePasswordFromSubscribedCustomerObject(customersEnquiryObject);
		}
		return customersEnquiryList;
	}
}
