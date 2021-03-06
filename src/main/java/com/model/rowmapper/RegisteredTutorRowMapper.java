package com.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import com.constants.components.publicaccess.RegisteredTutorConstants;
import com.model.components.RegisteredTutor;
import com.utils.ExceptionUtils;
import com.utils.GridComponentUtils;
import com.utils.RowMapperUtils;
import com.utils.UserUtils;

public class RegisteredTutorRowMapper implements RowMapper<RegisteredTutor>, RegisteredTutorConstants {

	@Override
	public RegisteredTutor mapRow(ResultSet row, int rowNum) throws SQLException {
		RowMapperUtils.showQueryFetch(row, rowNum, this);
		final RegisteredTutor registeredTutor = new RegisteredTutor();
		registeredTutor.setTutorSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("tutorSerialId"), String.class));
		registeredTutor.setBecomeTutorSerialId(ExceptionUtils.exceptionHandlerForRowMapper(row, registeredTutor.resolveColumnNameForMapping("becomeTutorSerialId"), String.class));
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
		UserUtils.mapUserPseudoColumnsForRecords(registeredTutor, row, rowNum);
		GridComponentUtils.mapGridPseudoColumnsForRecords(registeredTutor, row, rowNum);
		return registeredTutor;
	}

}
