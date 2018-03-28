package com.service.components;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.constants.BeanConstants;
import com.utils.WorkbookUtils;

@Service(BeanConstants.BEAN_NAME_ADMIN_SERVICE)
public class AdminService {
	
	//@Autowired
	//private transient ApplicationDao applicationDao;
	
	@PostConstruct
	public void init() {}
	
	public byte[] downloadReport(final String empId) throws Exception {
		Map <String, Object[]> workbookData = new HashMap < String, Object[] >();
		workbookData.put( "1", new Object[] { "EMP ID", "EMP NAME", "DESIGNATION" });
		workbookData.put( "2", new Object[] { "tp01", "Gopal", "Technical Manager" });
		workbookData.put( "3", new Object[] { "tp02", "Manisha", "Proof Reader" });
		workbookData.put( "4", new Object[] { "tp03", "Masthan", "Technical Writer" });
		workbookData.put( "5", new Object[] { "tp04", "Satish", "Technical Writer" });
		workbookData.put( "6", new Object[] { "tp05", "Krishna", "Technical Writer" });
        return WorkbookUtils.createWorkbook(" Employee Info ", workbookData);
	}

}
