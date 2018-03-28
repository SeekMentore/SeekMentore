---------------------------------------------------------------------------------
--                                     DATABASE
									-------------
---------------------------------------------------------------------------------
CREATE DATABASE IF NOT EXISTS SEEK_MENTOR;
USE SEEK_MENTOR;

---------------------------------------------------------------------------------
--                                     TABLES
                                    -------------
---------------------------------------------------------------------------------
--                                     FORMS
								    -------------
---------------------------------------------------------------------------------
-- Form 2 Tables
---------------------------------------------------------------------------------
CREATE TABLE TUTOR(
TUTOR_ID INT NOT NULL AUTO_INCREMENT,
USER_ID VARCHAR(50) NOT NULL,
DATE_OF_BIRTH DATE,
CONTACT_NUMBER VARCHAR(15) NOT NULL,
EMAIL_ID VARCHAR(50) NOT NULL,
NAME VARCHAR(50),
FATHER_NAME VARCHAR(50),

GENDER VARCHAR(1),
MARITAL_STATUS VARCHAR(1),
ACCOUNT_NUMBER VARCHAR(15),
PERMANENT_ADDRESS VARCHAR(1000),
TEMP_ADD_SAME VARCHAR(1),
TEMPORARY_ADDRESS VARCHAR(1000),
DATE_OF_EPF_JOIN DATE,
DATE_OF_EPS_JOIN DATE,
PRIMARY KEY (EMP_ID));
-- Employee PF Nomination Details Table
CREATE TABLE FORM2_EMP_PF_NOM_DTLS(
EMP_PF_NOM_ID INT NOT NULL AUTO_INCREMENT,
EMP_ID VARCHAR(11),
NAME_ADDRESS VARCHAR(500),
RELATIONSHIP VARCHAR(50),
DATE_OF_BIRTH DATE,
PERCENTAGE INT,
GAURDIAN VARCHAR(100),
PRIMARY KEY (EMP_PF_NOM_ID),
KEY FK_FORM2_EMP_ID_EMP_PF_NOM (EMP_ID),
CONSTRAINT FK_FORM2_EMP_ID_EMP_PF_NOM FOREIGN KEY (EMP_ID) REFERENCES FORM2 (EMP_ID));
-- Widow Children Pension Nominee Details Table
CREATE TABLE FORM2_WDW_CHLDRN_NOM_DTLS(
WDW_CHLDRN_NOM_ID INT NOT NULL AUTO_INCREMENT,
EMP_ID VARCHAR(11),
NAME VARCHAR(50),
ADDRESS VARCHAR(500),
DATE_OF_BIRTH DATE,
RELATIONSHIP VARCHAR(50),
PRIMARY KEY (WDW_CHLDRN_NOM_ID),
KEY FK_FORM2_EMP_ID_WDW_CHLDRN_NOM (EMP_ID),
CONSTRAINT FK_FORM2_EMP_ID_WDW_CHLDRN_NOM FOREIGN KEY (EMP_ID) REFERENCES FORM2 (EMP_ID));
-- Widow Children Pension Nominee Details Table
CREATE TABLE FORM2_ONLY_WDW_NOM_DTLS(
ONLY_WDW_NOM_ID INT NOT NULL AUTO_INCREMENT,
EMP_ID VARCHAR(11),
NAME_ADDRESS VARCHAR(500),
DATE_OF_BIRTH DATE,
RELATIONSHIP VARCHAR(50),
PRIMARY KEY (ONLY_WDW_NOM_ID),
KEY FK_FORM2_EMP_ID_ONLY_WDW_NOM (EMP_ID),
CONSTRAINT FK_FORM2_EMP_ID_ONLY_WDW_NOM FOREIGN KEY (EMP_ID) REFERENCES FORM2 (EMP_ID));

---------------------------------------------------------------------------------
-- Form 11 Tables
---------------------------------------------------------------------------------
CREATE TABLE FORM11(
EMP_ID VARCHAR(11) NOT NULL,
FORM_STATUS VARCHAR(11),
NAME VARCHAR(50),
FATHER_HUSBAND_NAME VARCHAR(50),
DATE_OF_BIRTH DATE,
GENDER VARCHAR(1),
RELATION_WITH VARCHAR(1),
MOBILE_NUMBER VARCHAR(15),
EMAIL_ID VARCHAR(100),
EARLIER_MEMBER_OF_PF_1952 VARCHAR(1),
EARLIER_MEMBER_OF_PF_1995 VARCHAR(1),
UAN VARCHAR(50),
PF_MEMBER_ID VARCHAR(50),
DATE_OF_EXIT DATE,
SCHEME_CERTIFICATE_NUMBER VARCHAR(50),
PPO_NUMBER VARCHAR(50),
INTERNATIONAL_WORKER VARCHAR(1),
COUNTRY_OF_ORIGIN VARCHAR(4),
PASSPORT_NUMBER VARCHAR(20),
PASSPORT_VALID_FROM DATE,
PASSPORT_VALID_TO DATE,
EDUCATIONAL_QUALIFICATION VARCHAR(100),
MARITAL_STATUS VARCHAR(1),
SPECIALLY_ABLED VARCHAR(1),
CATEGORY VARCHAR(1),
NON_CONTRIBUTORY_DAYS INT,
PF_ACC_NUMBER VARCHAR(30),
ESTABLISH_NAME_ADDR VARCHAR(500),
TRUST_NAME_ADDR VARCHAR(500),
DATE_OF_JOINING DATE,
PRIMARY KEY (EMP_ID));
-- Employee PF Nomination Details Table
CREATE TABLE FORM11_KYC_DTLS(
KYC_DTLS_ID INT NOT NULL AUTO_INCREMENT,
EMP_ID VARCHAR(11),
DOCUMENT_NAME VARCHAR(100),
DOCUMENT_TYPE VARCHAR(500),
DOCUMENT_NUMBER VARCHAR(50),
REMARKS VARCHAR(500),
PRIMARY KEY (KYC_DTLS_ID),
KEY FK_FORM11_EMP_ID_KYC (EMP_ID),
CONSTRAINT FK_FORM11_EMP_ID_KYC FOREIGN KEY (EMP_ID) REFERENCES FORM11 (EMP_ID));

---------------------------------------------------------------------------------
-- Form F Tables
---------------------------------------------------------------------------------
CREATE TABLE FORMF(
EMP_ID VARCHAR(11) NOT NULL,
FORM_STATUS VARCHAR(11),
NAME VARCHAR(50),
NOTICE_DATE DATE,
FULL_NAME VARCHAR(100),
GENDER VARCHAR(1),
DOA DATE,
DEPARTMENT VARCHAR(100),
EMPLOYEE_NUMBER VARCHAR(50),
FATHER_NAME VARCHAR(50),
HUSBAND_NAME VARCHAR(50),
MARITAL_STATUS VARCHAR(1),
DATE_OF_BIRTH DATE,
PERMANENT_ADDRESS VARCHAR(500),
STATE VARCHAR(4),
PINCODE VARCHAR(10),
APPOINTMENT_DATE DATE,
DESIGNATION VARCHAR(50),
FAMILY_FLAG VARCHAR(1),
SELF_DEPENDENTS VARCHAR(20),
HUSBAND_DEPENDENTS VARCHAR(20),
PRIMARY KEY (EMP_ID));
-- Employee Gratuity Nominee Details Table
CREATE TABLE FORMF_EMP_GRTY_NOM_DTLS(
EMP_GRTY_NOM_ID INT NOT NULL AUTO_INCREMENT,
EMP_ID VARCHAR(11),
NAME_AND_ADDRESS VARCHAR(500),
RELATIONSHIP VARCHAR(1),
AGE INT,
PERCENTAGE INT,
PRIMARY KEY (EMP_GRTY_NOM_ID),
KEY FK_FORMF_EMP_ID_EMP_GRTY_NOM (EMP_ID),
CONSTRAINT FK_FORMF_EMP_ID_EMP_GRTY_NOM FOREIGN KEY (EMP_ID) REFERENCES FORMF (EMP_ID));	

---------------------------------------------------------------------------------
-- Look Up tables
---------------------------------------------------------------------------------
-- Country Details Table
CREATE TABLE COUNTRY(
COUNTRY_CODE VARCHAR(4) NOT NULL,
COUNTRY_NAME VARCHAR(200) NOT NULL,
COUNTRY_DESC VARCHAR(500),
PRIMARY KEY (COUNTRY_CODE));
-- Country Details Table
CREATE TABLE STATE(
STATE_CODE VARCHAR(4) NOT NULL,
STATE_NAME VARCHAR(200) NOT NULL,
COUNTRY_CODE VARCHAR(11),
STATE_DESC VARCHAR(500),
PRIMARY KEY (STATE_CODE),
KEY FK_COUNTRY_COUNTRY_CODE_STATE (COUNTRY_CODE),
CONSTRAINT FK_COUNTRY_COUNTRY_CODE_STATE FOREIGN KEY (COUNTRY_CODE) REFERENCES COUNTRY (COUNTRY_CODE));
-- Marital Status Details Table
CREATE TABLE MARITAL_STATUS(
STATUS_CODE VARCHAR(4) NOT NULL,
STATUS_NAME VARCHAR(200) NOT NULL,
STATUS_DESC VARCHAR(500),
PRIMARY KEY (STATUS_CODE));
-- Relationship Details Table
CREATE TABLE RELATIONSHIP(
RELATIONSHIP_CODE VARCHAR(4) NOT NULL,
RELATIONSHIP_NAME VARCHAR(200) NOT NULL,
RELATIONSHIP_DESC VARCHAR(500),
PRIMARY KEY (RELATIONSHIP_CODE));
-- Category Details Table				
CREATE TABLE CATEGORY(
CATEGORY_CODE VARCHAR(4) NOT NULL,
CATEGORY_NAME VARCHAR(200) NOT NULL,
CATEGORY_DESC VARCHAR(500),
PRIMARY KEY (CATEGORY_CODE));
-- Gender Details Table				
CREATE TABLE GENDER(
GENDER_CODE VARCHAR(4) NOT NULL,
GENDER_NAME VARCHAR(200) NOT NULL,
GENDER_DESC VARCHAR(500),
PRIMARY KEY (GENDER_CODE));