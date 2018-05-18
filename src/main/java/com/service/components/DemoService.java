package com.service.components;

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
import com.constants.components.DemoTrackerConstants;
import com.dao.ApplicationDao;
import com.model.components.DemoTracker;
import com.model.rowmappers.DemoTrackerRowMapper;
import com.service.JNDIandControlConfigurationLoadService;

@Service(BeanConstants.BEAN_NAME_DEMO_SERVICE)
public class DemoService implements DemoTrackerConstants {
	
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
	public List<DemoTracker> displayDemos(final String grid, final String delimiter) throws DataAccessException, InstantiationException, IllegalAccessException {
		final StringBuilder query = new StringBuilder("SELECT * FROM DEMO_TRACKER D WHERE D.DEMO_STATUS = :demoStatus)");
		final Map<String, Object> paramsMap = new HashMap<String, Object>();
		switch(grid) {
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_PENDING_DEMOS : {
				paramsMap.put("demoStatus", DEMO_STATUS_PENDING);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_SUCCESSFULL_DEMOS : {
				paramsMap.put("demoStatus", DEMO_STATUS_SUCCESS);
				break;
			}
			case RestMethodConstants.REST_METHOD_NAME_DISPLAY_FAILED_DEMOS : {
				paramsMap.put("demoStatus", DEMO_STATUS_FAILED);
				break;
			}
		}
		final List<DemoTracker> demoTrackerList = applicationDao.findAll(query.toString(), paramsMap, new DemoTrackerRowMapper());
		for (final DemoTracker demoTrackerObject : demoTrackerList) {
		}
		return demoTrackerList;
	}
}
