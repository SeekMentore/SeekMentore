package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.publicaccess.RegisteredTutorConstants;
import com.model.components.RegisteredTutor;
import com.utils.ExceptionUtils;

public class RegisteredTutorRowMapper implements RowMapper<RegisteredTutor>, RegisteredTutorConstants {

	@Override
	public RegisteredTutor mapRow(ResultSet row, int rowNum) throws SQLException {
		final RegisteredTutor registeredTutor = new RegisteredTutor();
		registeredTutor.setTutorId(ExceptionUtils.exceptionHandlerForRowMapper(row, "TUTOR_ID", Long.class));
		registeredTutor.setName(ExceptionUtils.exceptionHandlerForRowMapper(row, "NAME", String.class));
		registeredTutor.setContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, "CONTACT_NUMBER", String.class));
		registeredTutor.setEmailId(ExceptionUtils.exceptionHandlerForRowMapper(row, "EMAIL_ID", String.class));
		registeredTutor.setTentativeTutorId(ExceptionUtils.exceptionHandlerForRowMapper(row, "TENTATIVE_TUTOR_ID", Long.class));
		registeredTutor.setDateOfBirth(ExceptionUtils.exceptionHandlerForRowMapper(row, "DATE_OF_BIRTH", Timestamp.class));
		registeredTutor.setGender(ExceptionUtils.exceptionHandlerForRowMapper(row, "GENDER", String.class));
		registeredTutor.setQualification(ExceptionUtils.exceptionHandlerForRowMapper(row, "QUALIFICATION", String.class));
		registeredTutor.setPrimaryProfession(ExceptionUtils.exceptionHandlerForRowMapper(row, "PRIMARY_PROFESSION", String.class));
		registeredTutor.setTransportMode(ExceptionUtils.exceptionHandlerForRowMapper(row, "TRANSPORT_MODE", String.class));
		registeredTutor.setTeachingExp(ExceptionUtils.exceptionHandlerForRowMapper(row, "TEACHING_EXP", Integer.class));
		registeredTutor.setInterestedStudentGrades(ExceptionUtils.exceptionHandlerForRowMapper(row, "INTERESTED_STUDENT_GRADES", String.class));
		registeredTutor.setInterestedSubjects(ExceptionUtils.exceptionHandlerForRowMapper(row, "INTERESTED_SUBJECTS", String.class));
		registeredTutor.setPreferredTeachingType(ExceptionUtils.exceptionHandlerForRowMapper(row, "PREFERRED_TEACHING_TYPE", String.class));
		registeredTutor.setComfortableLocations(ExceptionUtils.exceptionHandlerForRowMapper(row, "COMFORTABLE_LOCATIONS", String.class));
		registeredTutor.setAdditionalDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, "ADDITIONAL_DETAILS", String.class));
		registeredTutor.setEncryptedPassword(ExceptionUtils.exceptionHandlerForRowMapper(row, "ENCRYPTED_PASSWORD", String.class));
		registeredTutor.setRecordLastUpdated(ExceptionUtils.exceptionHandlerForRowMapper(row, "RECORD_LAST_UPDATED", Timestamp.class));
		registeredTutor.setUserId(ExceptionUtils.exceptionHandlerForRowMapper(row, "USER_ID", String.class));
		registeredTutor.setUpdatedBy(ExceptionUtils.exceptionHandlerForRowMapper(row, "UPDATED_BY", String.class));
		return registeredTutor;
	}

}
