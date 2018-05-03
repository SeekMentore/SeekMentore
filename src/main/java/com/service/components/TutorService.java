package com.service.components;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.constants.BeanConstants;
import com.constants.components.TutorConstants;
import com.dao.ApplicationDao;

@Service(BeanConstants.BEAN_NAME_TUTOR_SERVICE)
public class TutorService implements TutorConstants {
	
	@Autowired
	private transient ApplicationDao applicationDao;
	
	@PostConstruct
	public void init() {}
	
	@Transactional
	public void feedDocumentsRecord(final String tutorId, final Map<String, String> uploadedFiles) {
		for (Map.Entry<String, String> entry : uploadedFiles.entrySet()) {
			final Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("tutorId", tutorId);
			paramsMap.put("fsKey", entry.getValue());
			paramsMap.put("filename", entry.getKey());
			applicationDao.executeUpdate("INSERT INTO TUTOR_DOCUMENTS(TUTOR_ID, FS_KEY, FILENAME) VALUES(:tutorId, :fsKey, :filename)", paramsMap);
		}
	}
	
	public String getFolderPathToUploadTutorDocuments(final String tutorId) {
		return "secured/tutor/documents/" + tutorId;
	}
}
