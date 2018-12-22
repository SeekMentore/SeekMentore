package com.model.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.publicaccess.RegisteredTutorConstants;
import com.model.components.RegisteredTutor;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;

public class RegisteredTutorRowMapper implements RowMapper<RegisteredTutor>, RegisteredTutorConstants {

	@Override
	public RegisteredTutor mapRow(ResultSet row, int rowNum) throws SQLException {
		final RegisteredTutor registeredTutor = new RegisteredTutor();
		registeredTutor.setTutorId(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("tutorId"), Long.class));
		registeredTutor.setName(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("name"), String.class));
		registeredTutor.setContactNumber(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("contactNumber"), String.class));
		registeredTutor.setEmailId(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("emailId"), String.class));
		registeredTutor.setTentativeTutorId(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("tentativeTutorId"), Long.class));
		registeredTutor.setDateOfBirth(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("dateOfBirth"), Timestamp.class));
		registeredTutor.setGender(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("gender"), String.class));
		registeredTutor.setQualification(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("qualification"), String.class));
		registeredTutor.setPrimaryProfession(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("primaryProfession"), String.class));
		registeredTutor.setTransportMode(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("transportMode"), String.class));
		registeredTutor.setTeachingExp(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("teachingExp"), Integer.class));
		registeredTutor.setInterestedStudentGrades(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("interestedStudentGrades"), String.class));
		registeredTutor.setInterestedSubjects(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("interestedSubjects"), String.class));
		registeredTutor.setPreferredTeachingType(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("preferredTeachingType"), String.class));
		registeredTutor.setComfortableLocations(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("comfortableLocations"), String.class));
		registeredTutor.setAdditionalDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("additionalDetails"), String.class));
		registeredTutor.setEncryptedPassword(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("encryptedPassword"), String.class));
		registeredTutor.setRecordLastUpdatedMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("recordLastUpdatedMillis"), Long.class));
		registeredTutor.setUserId(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("userId"), String.class));
		registeredTutor.setUpdatedBy(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("updatedBy"), String.class));
		registeredTutor.setUpdatedByName(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("updatedByName"), String.class));
		registeredTutor.setAddressDetails(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("addressDetails"), String.class));
		registeredTutor.setBlacklistedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("blacklistedDateMillis"), Long.class));
		registeredTutor.setIsBlacklisted(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("isBlacklisted"), String.class));
		registeredTutor.setBlacklistedRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("blacklistedRemarks"), String.class));
		registeredTutor.setWhoBlacklisted(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("whoBlacklisted"), String.class));
		registeredTutor.setWhoBlacklistedName(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("whoBlacklistedName"), String.class));
		registeredTutor.setUnblacklistedDateMillis(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("unblacklistedDateMillis"), Long.class));
		registeredTutor.setUnblacklistedRemarks(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("unblacklistedRemarks"), String.class));
		registeredTutor.setWhoUnBlacklisted(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("whoUnBlacklisted"), String.class));
		registeredTutor.setWhoUnBlacklistedName(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("whoUnBlacklistedName"), String.class));
		GridComponentUtils.mapGridPseudoColumnsForRecords(registeredTutor, row, rowNum);
		return registeredTutor;
	}

}
