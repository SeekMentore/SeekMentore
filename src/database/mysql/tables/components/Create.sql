---------------------------------------------------------------------------------
--                                     DATABASE
									-------------
---------------------------------------------------------------------------------
CREATE DATABASE IF NOT EXISTS SEEK_MENTORE;
USE SEEK_MENTORE;

---------------------------------------------------------------------------------
--                                     TABLES
                                    -------------
---------------------------------------------------------------------------------
--                                     FORMS
								    -------------
---------------------------------------------------------------------------------
-- Public Pages Tables
---------------------------------------------------------------------------------
CREATE TABLE BECOME_TUTOR(
TENT_TUTOR_ID INT NOT NULL AUTO_INCREMENT,
APPLICATION_DATE DATE NOT NULL,
DATE_OF_BIRTH DATE NOT NULL,
CONTACT_NUMBER VARCHAR(15) NOT NULL UNIQUE,
EMAIL_ID VARCHAR(100) NOT NULL UNIQUE,
FIRST_NAME VARCHAR(100) NOT NULL,
LAST_NAME VARCHAR(100) NOT NULL,
GENDER VARCHAR(1) NOT NULL,
QUALIFICATION VARCHAR(100) NOT NULL,
PRIMARY_PROFESSION VARCHAR(100),
TRANSPORT_MODE VARCHAR(15) NOT NULL,
TEACHING_EXP INT,
SUBJECTS VARCHAR(500) NOT NULL,
LOCATIONS VARCHAR(500) NOT NULL,
PRFRD_TIME_CALL VARCHAR(50) NOT NULL,
ADDL_DETAILS VARCHAR(2000),
IS_CONTACTED VARCHAR(2) NOT NULL,
IS_AUTH_VERIFIED VARCHAR(2),
IS_TO_BE_RECONTACTED VARCHAR(2),
IS_SELECTED VARCHAR(2),
REJECTION_COUNT INT,
WAS_REJECTED VARCHAR(2),
REJECTION_REASON VARCHAR(2000),
PRIMARY KEY (TENT_TUTOR_ID));

CREATE TABLE FIND_TUTOR(
ENQUIRY_ID INT NOT NULL AUTO_INCREMENT,
ENQUIRY_DATE DATE NOT NULL,
CONTACT_NUMBER VARCHAR(15) NOT NULL,
EMAIL_ID VARCHAR(100) NOT NULL,
STUDENT_GRADE VARCHAR(10) NOT NULL,
SUBJECTS VARCHAR(500) NOT NULL,
PRFRD_TIME_CALL VARCHAR(50) NOT NULL,
ADDL_DETAILS VARCHAR(2000),