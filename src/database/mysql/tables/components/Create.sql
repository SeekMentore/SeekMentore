---------------------------------------------------------------------------------
--                                     DATABASE
									-------------
---------------------------------------------------------------------------------
CREATE DATABASE IF NOT EXISTS SEEK_MENTORE;
USE SEEK_MENTORE;
---------------------------------------------------------------------------------
--                                     TABLES
---------------------------------------------------------------------------------
-- Public Pages Tables
---------------------------------------------------------------------------------
-- Become a Tutor
CREATE TABLE BECOME_TUTOR(
TENTATIVE_TUTOR_ID INT NOT NULL AUTO_INCREMENT,
APPLICATION_DATE TIMESTAMP NOT NULL,
APPLICATION_STATUS VARCHAR(100) NOT NULL,
DATE_OF_BIRTH DATE NOT NULL,
CONTACT_NUMBER VARCHAR(15) NOT NULL UNIQUE,
EMAIL_ID VARCHAR(100) NOT NULL UNIQUE,
FIRST_NAME VARCHAR(100) NOT NULL,
LAST_NAME VARCHAR(100) NOT NULL,
GENDER VARCHAR(10) NOT NULL,
QUALIFICATION VARCHAR(10) NOT NULL,
PRIMARY_PROFESSION VARCHAR(10) NOT NULL,
TRANSPORT_MODE VARCHAR(10) NOT NULL,
TEACHING_EXP INT,
STUDENT_GRADE VARCHAR(1000) NOT NULL,
SUBJECTS VARCHAR(1000) NOT NULL,
LOCATIONS VARCHAR(1000) NOT NULL,
PREFERRED_TIME_TO_CALL VARCHAR(1000) NOT NULL,
ADDITIONAL_DETAILS VARCHAR(2000),
IS_CONTACTED VARCHAR(2) NOT NULL,
WHO_CONTACTED VARCHAR(100),
CONTACTED_DATE TIMESTAMP,
CONTACTED_REMARKS VARCHAR(2000),
IS_AUTHENTICATION_VERIFIED VARCHAR(2),
WHO_VERIFIED VARCHAR(100),
VERIFICATION_DATE TIMESTAMP,
VERIFICATION_REMARKS VARCHAR(2000),
IS_TO_BE_RECONTACTED VARCHAR(2),
WHO_SUGGESTED_FOR_RECONTACT VARCHAR(100),
SUGGESTION_DATE TIMESTAMP,
SUGGESTION_REMARKS VARCHAR(2000),
WHO_RECONTACTED VARCHAR(100),
RECONTACTED_DATE TIMESTAMP,
RECONTACTED_REMARKS VARCHAR(2000),
IS_SELECTED VARCHAR(2),
WHO_SELECTED VARCHAR(100),
SELECTION_DATE TIMESTAMP,
SELECTION_REMARKS VARCHAR(2000),
IS_REJECTED VARCHAR(2),
WHO_REJECTED VARCHAR(100),
REJECTION_DATE TIMESTAMP,
REJECTION_REMARKS VARCHAR(2000),
REJECTION_COUNT INT,
RE_APPLIED VARCHAR(2),
PREVIOUS_APPLICATION_DATE TIMESTAMP,
RECORD_LAST_UPDATED TIMESTAMP NOT NULL,
PRIMARY KEY (TENTATIVE_TUTOR_ID));
-- Find Your Tutor
CREATE TABLE FIND_TUTOR(
ENQUIRY_ID INT NOT NULL AUTO_INCREMENT,
ENQUIRY_DATE TIMESTAMP NOT NULL,
ENQUIRY_STATUS VARCHAR(100) NOT NULL,
NAME VARCHAR(100) NOT NULL,
CONTACT_NUMBER VARCHAR(15) NOT NULL,
EMAIL_ID VARCHAR(100) NOT NULL,
STUDENT_GRADE VARCHAR(10) NOT NULL,
SUBJECTS VARCHAR(10) NOT NULL,
PREFERRED_TIME_TO_CALL VARCHAR(1000) NOT NULL,
ADDITIONAL_DETAILS VARCHAR(2000),
SUBSCRIBED_CUSTOMER VARCHAR(2) NOT NULL,
IS_CONTACTED VARCHAR(2) NOT NULL,
WHO_CONTACTED VARCHAR(100),
CONTACTED_DATE TIMESTAMP,
CONTACTED_REMARKS VARCHAR(2000),
IS_AUTHENTICATION_VERIFIED VARCHAR(2),
WHO_VERIFIED VARCHAR(100),
VERIFICATION_DATE TIMESTAMP,
VERIFICATION_REMARKS VARCHAR(2000),
IS_TO_BE_RECONTACTED VARCHAR(2),
WHO_SUGGESTED_FOR_RECONTACT VARCHAR(100),
SUGGESTION_DATE TIMESTAMP,
SUGGESTION_REMARKS VARCHAR(2000),
WHO_RECONTACTED VARCHAR(100),
RECONTACTED_DATE TIMESTAMP,
RECONTACTED_REMARKS VARCHAR(2000),
IS_SELECTED VARCHAR(2),
WHO_SELECTED VARCHAR(100),
SELECTION_DATE TIMESTAMP,
SELECTION_REMARKS VARCHAR(2000),
IS_REJECTED VARCHAR(2),
WHO_REJECTED VARCHAR(100),
REJECTION_DATE TIMESTAMP,
REJECTION_REMARKS VARCHAR(2000),
RECORD_LAST_UPDATED TIMESTAMP NOT NULL,
PRIMARY KEY (ENQUIRY_ID));
-- Subscribe With Us
CREATE TABLE SUBSCRIBE_WITH_US(
TENTATIVE_SUBSCRIPTION_ID INT NOT NULL AUTO_INCREMENT,
APPLICATION_DATE TIMESTAMP NOT NULL,
APPLICATION_STATUS VARCHAR(100) NOT NULL,
FIRST_NAME VARCHAR(100) NOT NULL,
LAST_NAME VARCHAR(100) NOT NULL,
CONTACT_NUMBER VARCHAR(15) NOT NULL,
EMAIL_ID VARCHAR(100) NOT NULL,
STUDENT_GRADE VARCHAR(1000) NOT NULL,
SUBJECTS VARCHAR(1000) NOT NULL,
PREFERRED_TIME_TO_CALL VARCHAR(1000) NOT NULL,
ADDITIONAL_DETAILS VARCHAR(2000),
SUBSCRIBED_CUSTOMER VARCHAR(2) NOT NULL,
IS_CONTACTED VARCHAR(2) NOT NULL,
WHO_CONTACTED VARCHAR(100),
CONTACTED_DATE TIMESTAMP,
CONTACTED_REMARKS VARCHAR(2000),
IS_AUTHENTICATION_VERIFIED VARCHAR(2),
WHO_VERIFIED VARCHAR(100),
VERIFICATION_DATE TIMESTAMP,
VERIFICATION_REMARKS VARCHAR(2000),
IS_TO_BE_RECONTACTED VARCHAR(2),
WHO_SUGGESTED_FOR_RECONTACT VARCHAR(100),
SUGGESTION_DATE TIMESTAMP,
SUGGESTION_REMARKS VARCHAR(2000),
WHO_RECONTACTED VARCHAR(100),
RECONTACTED_DATE TIMESTAMP,
RECONTACTED_REMARKS VARCHAR(2000),
IS_SELECTED VARCHAR(2),
WHO_SELECTED VARCHAR(100),
SELECTION_DATE TIMESTAMP,
SELECTION_REMARKS VARCHAR(2000),
IS_REJECTED VARCHAR(2),
WHO_REJECTED VARCHAR(100),
REJECTION_DATE TIMESTAMP,
REJECTION_REMARKS VARCHAR(2000),
RECORD_LAST_UPDATED TIMESTAMP NOT NULL,
PRIMARY KEY (TENTATIVE_SUBSCRIPTION_ID));
-- Submit Query
CREATE TABLE SUBMIT_QUERY(
QUERY_ID INT NOT NULL AUTO_INCREMENT,
QUERY_REQUESTED_DATE TIMESTAMP NOT NULL,
QUERY_STATUS VARCHAR(100) NOT NULL,
EMAIL_ID VARCHAR(100) NOT NULL,
QUERY_DETAILS VARCHAR(2000) NOT NULL,
REGISTERED_TUTOR VARCHAR(2) NOT NULL,
SUBSCRIBED_CUSTOMER VARCHAR(2) NOT NULL,
IS_CONTACTED VARCHAR(2) NOT NULL,
WHO_CONTACTED VARCHAR(100),
CONTACTED_DATE TIMESTAMP,
QUERY_RESPONSE VARCHAR(2000),
NOT_ANSWERED VARCHAR(2),
NOT_ANSWERED_REASON VARCHAR(2000),
WHO_NOT_ANSWERED VARCHAR(100),
RECORD_LAST_UPDATED TIMESTAMP NOT NULL,
PRIMARY KEY (QUERY_ID));

---------------------------------------------------------------------------------
-- Core Hold Tables
---------------------------------------------------------------------------------
CREATE TABLE SUBSCRIBED_CUSTOMER(
CUSTOMER_ID INT NOT NULL AUTO_INCREMENT,
NAME VARCHAR(100) NOT NULL,
CONTACT_NUMBER VARCHAR(15) NOT NULL UNIQUE,
EMAIL_ID VARCHAR(100) NOT NULL UNIQUE,
PRIMARY KEY (CUSTOMER_ID));

CREATE TABLE REGISTERED_TUTOR(
TUTOR_ID INT NOT NULL AUTO_INCREMENT,
NAME VARCHAR(100) NOT NULL,
CONTACT_NUMBER VARCHAR(15) NOT NULL UNIQUE,
EMAIL_ID VARCHAR(100) NOT NULL UNIQUE,
PRIMARY KEY (TUTOR_ID));

CREATE TABLE TUTOR_DOCUMENTS(
DOCUMENT_ID INT NOT NULL AUTO_INCREMENT,
TUTOR_ID INT NOT NULL,
FS_KEY VARCHAR(1000) NOT NULL,
FILENAME VARCHAR(100) NOT NULL,
PRIMARY KEY (DOCUMENT_ID));

---------------------------------------------------------------------------------
-- Core Lookup Tables
---------------------------------------------------------------------------------
CREATE TABLE GENDER_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

CREATE TABLE QUALIFICATION_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

CREATE TABLE PROFESSION_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

CREATE TABLE TRANSPORT_MODE_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

CREATE TABLE LOCATIONS_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

CREATE TABLE PREFERRED_TIME_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

CREATE TABLE STUDENT_GRADE_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

CREATE TABLE SUBJECTS_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

---------------------------------------------------------------------------------
-- Core Error Handling Tables
---------------------------------------------------------------------------------
CREATE TABLE APP_ERROR_REPORT(
ERROR_ID INT NOT NULL AUTO_INCREMENT,
OCCURED_AT TIMESTAMP NOT NULL,
REQUEST_URI VARCHAR(100) NOT NULL,
ERROR_TRACE TEXT,
PRIMARY KEY (ERROR_ID));

---------------------------------------------------------------------------------
-- Core Email Handling Tables
---------------------------------------------------------------------------------
CREATE TABLE MAIL_QUEUE(
MAIL_ID INT NOT NULL AUTO_INCREMENT,
MAIL_TYPE VARCHAR(50) NOT NULL,
ENTRY_DATE TIMESTAMP NOT NULL,
FROM_ADDRESS VARCHAR(1000),
TO_ADDRESS VARCHAR(1000) NOT NULL,
CC_ADDRESS VARCHAR(1000),
BCC_ADDRESS VARCHAR(1000),
SUBJECT_CONTENT TEXT NOT NULL,
MESSAGE_CONTENT TEXT NOT NULL,
MAIL_SENT VARCHAR(2),
SEND_DATE TIMESTAMP,
ERROR_OCCURED_WHILE_SENDING VARCHAR(2),
ERROR_DATE TIMESTAMP,
ERROR_TRACE TEXT,
PRIMARY KEY (MAIL_ID));
-- Attachment table
CREATE TABLE MAIL_ATTACHMENTS(
ATTACHMENT_ID INT NOT NULL AUTO_INCREMENT,
MAIL_ID INT NOT NULL,
CONTENT LONGBLOB,
FILENAME VARCHAR(500),
APPLICATION_TYPE VARCHAR(500),
PRIMARY KEY (ATTACHMENT_ID));
---------------------------------------------------------------------------------
-- Core User Tables
---------------------------------------------------------------------------------
CREATE TABLE EMPLOYEE(
EMPLOYEE_ID VARCHAR(50) NOT NULL,
NAME VARCHAR(100) NOT NULL,
USER_ID VARCHAR(100) NOT NULL UNIQUE,
EMAIL_DOMAIN VARCHAR(500) NOT NULL,
USER_TYPE VARCHAR(100) NOT NULL,
ENCYPTED_PASSWORD VARCHAR(1000) NOT NULL,
PRIMARY KEY (EMPLOYEE_ID));