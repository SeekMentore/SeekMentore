USE SEEK_MENTORE;

CREATE TABLE EMAIL_TEMPLATE_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

CREATE TABLE EMAIL_TEMPLATE(
EMAIL_TEMPLATE_ID INT NOT NULL AUTO_INCREMENT,
EMAIL_TEMPLATE_LOOKUP_VALUE VARCHAR(100) NOT NULL,
TO_ADDRESS VARCHAR(1000) NOT NULL,
CC_ADDRESS VARCHAR(1000),
BCC_ADDRESS VARCHAR(1000),
SUBJECT_CONTENT TEXT,
MESSAGE_CONTENT TEXT,
ADDED_BY VARCHAR(100) NOT NULL,
ADDED_MILLIS BIGINT NOT NULL,
LAST_UPDATED_BY VARCHAR(100) NOT NULL,
LAST_UPDATED_MILLIS BIGINT NOT NULL,
PRIMARY KEY (EMAIL_TEMPLATE_ID));

INSERT INTO PUBLIC_APPLICATION_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('01', 'Fresh', NULL, 1, 1, NULL, NULL);
INSERT INTO PUBLIC_APPLICATION_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('02', 'Contacted Verification Pending', NULL, 1, 2, NULL, NULL);
INSERT INTO PUBLIC_APPLICATION_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('03', 'Re-Contacted Verification Pending', NULL, 1, 3, NULL, NULL);
INSERT INTO PUBLIC_APPLICATION_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('04', 'Verification Successful', NULL, 1, 4, NULL, NULL);
INSERT INTO PUBLIC_APPLICATION_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('05', 'Verification Failed', NULL, 1, 5, NULL, NULL);
INSERT INTO PUBLIC_APPLICATION_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('06', 'Suggested To Be Re-Contacted', NULL, 1, 6, NULL, NULL);
INSERT INTO PUBLIC_APPLICATION_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('07', 'Selected', NULL, 1, 7, NULL, NULL);
INSERT INTO PUBLIC_APPLICATION_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('08', 'Rejected', NULL, 1, 8, NULL, NULL);

UPDATE BECOME_TUTOR
SET APPLICATION_STATUS = '01'
WHERE APPLICATION_STATUS = 'FRESH';

UPDATE BECOME_TUTOR
SET APPLICATION_STATUS = '02'
WHERE APPLICATION_STATUS = 'CONTACTED_VERIFICATION_PENDING';

UPDATE BECOME_TUTOR
SET APPLICATION_STATUS = '03'
WHERE APPLICATION_STATUS = 'RECONTACTED_VERIFICATION_PENDING';

UPDATE BECOME_TUTOR
SET APPLICATION_STATUS = '04'
WHERE APPLICATION_STATUS = 'VERIFICATION_SUCCESSFUL';

UPDATE BECOME_TUTOR
SET APPLICATION_STATUS = '05'
WHERE APPLICATION_STATUS = 'VERIFICATION_FAILED';

UPDATE BECOME_TUTOR
SET APPLICATION_STATUS = '06'
WHERE APPLICATION_STATUS = 'SUGGESTED_TO_BE_RECONTACTED';

UPDATE BECOME_TUTOR
SET APPLICATION_STATUS = '07'
WHERE APPLICATION_STATUS = 'SELECTED';

UPDATE BECOME_TUTOR
SET APPLICATION_STATUS = '08'
WHERE APPLICATION_STATUS = 'REJECTED';

UPDATE FIND_TUTOR
SET ENQUIRY_STATUS = '01'
WHERE ENQUIRY_STATUS = 'FRESH';

UPDATE FIND_TUTOR
SET ENQUIRY_STATUS = '02'
WHERE ENQUIRY_STATUS = 'CONTACTED_VERIFICATION_PENDING';

UPDATE FIND_TUTOR
SET ENQUIRY_STATUS = '03'
WHERE ENQUIRY_STATUS = 'RECONTACTED_VERIFICATION_PENDING';

UPDATE FIND_TUTOR
SET ENQUIRY_STATUS = '04'
WHERE ENQUIRY_STATUS = 'VERIFICATION_SUCCESSFUL';

UPDATE FIND_TUTOR
SET ENQUIRY_STATUS = '05'
WHERE ENQUIRY_STATUS = 'VERIFICATION_FAILED';

UPDATE FIND_TUTOR
SET ENQUIRY_STATUS = '06'
WHERE ENQUIRY_STATUS = 'SUGGESTED_TO_BE_RECONTACTED';

UPDATE FIND_TUTOR
SET ENQUIRY_STATUS = '07'
WHERE ENQUIRY_STATUS = 'SELECTED';

UPDATE FIND_TUTOR
SET ENQUIRY_STATUS = '08'
WHERE ENQUIRY_STATUS = 'REJECTED';

UPDATE SUBSCRIBE_WITH_US
SET APPLICATION_STATUS = '01'
WHERE APPLICATION_STATUS = 'FRESH';

UPDATE SUBSCRIBE_WITH_US
SET APPLICATION_STATUS = '02'
WHERE APPLICATION_STATUS = 'CONTACTED_VERIFICATION_PENDING';

UPDATE SUBSCRIBE_WITH_US
SET APPLICATION_STATUS = '03'
WHERE APPLICATION_STATUS = 'RECONTACTED_VERIFICATION_PENDING';

UPDATE SUBSCRIBE_WITH_US
SET APPLICATION_STATUS = '04'
WHERE APPLICATION_STATUS = 'VERIFICATION_SUCCESSFUL';

UPDATE SUBSCRIBE_WITH_US
SET APPLICATION_STATUS = '05'
WHERE APPLICATION_STATUS = 'VERIFICATION_FAILED';

UPDATE SUBSCRIBE_WITH_US
SET APPLICATION_STATUS = '06'
WHERE APPLICATION_STATUS = 'SUGGESTED_TO_BE_RECONTACTED';

UPDATE SUBSCRIBE_WITH_US
SET APPLICATION_STATUS = '07'
WHERE APPLICATION_STATUS = 'SELECTED';

UPDATE SUBSCRIBE_WITH_US
SET APPLICATION_STATUS = '08'
WHERE APPLICATION_STATUS = 'REJECTED';

CREATE TABLE QUERY_STATUS_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

INSERT INTO QUERY_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('01', 'Fresh', NULL, 1, 1, NULL, NULL);
INSERT INTO QUERY_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('02', 'Responded', NULL, 1, 2, NULL, NULL);
INSERT INTO QUERY_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('03', 'Put On Hold', NULL, 1, 3, NULL, NULL);

UPDATE SUBMIT_QUERY
SET QUERY_STATUS = '01'
WHERE QUERY_STATUS = 'FRESH';

UPDATE SUBMIT_QUERY
SET QUERY_STATUS = '02'
WHERE QUERY_STATUS = 'RESPONDED';

UPDATE SUBMIT_QUERY
SET QUERY_STATUS = '03'
WHERE QUERY_STATUS = 'PUT_ON_HOLD';

CREATE TABLE COMPLAINT_STATUS_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

INSERT INTO COMPLAINT_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('01', 'Fresh', NULL, 1, 1, NULL, NULL);
INSERT INTO COMPLAINT_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('02', 'Resolved', NULL, 1, 2, NULL, NULL);
INSERT INTO COMPLAINT_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('03', 'Put On Hold', NULL, 1, 3, NULL, NULL);

CREATE TABLE USER_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

INSERT INTO USER_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('01', 'Employee', NULL, 1, 1, NULL, NULL);
INSERT INTO USER_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('02', 'Tutor', NULL, 1, 2, NULL, NULL);
INSERT INTO USER_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('03', 'Customer', NULL, 1, 3, NULL, NULL);

CREATE TABLE ENQUIRY_MATCH_STATUS_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

CREATE TABLE TUTOR_MAPPING_STATUS_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

CREATE TABLE DEMO_STATUS_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

INSERT INTO ENQUIRY_MATCH_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('01', 'Pending', NULL, 1, 1, NULL, NULL);
INSERT INTO ENQUIRY_MATCH_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('02', 'To Be Mapped', NULL, 1, 2, NULL, NULL);
INSERT INTO ENQUIRY_MATCH_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('03', 'Completed', NULL, 1, 3, NULL, NULL);
INSERT INTO ENQUIRY_MATCH_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('04', 'Aborted', NULL, 1, 3, NULL, NULL);

UPDATE ENQUIRIES
SET MATCH_STATUS = '01'
WHERE MATCH_STATUS = 'PENDING';

UPDATE ENQUIRIES
SET MATCH_STATUS = '02'
WHERE MATCH_STATUS = 'TO_BE_MAPPED';

UPDATE ENQUIRIES
SET MATCH_STATUS = '03'
WHERE MATCH_STATUS = 'COMPLETED';

UPDATE ENQUIRIES
SET MATCH_STATUS = '04'
WHERE MATCH_STATUS = 'ABORTED';

INSERT INTO TUTOR_MAPPING_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('01', 'Pending', NULL, 1, 1, NULL, NULL);
INSERT INTO TUTOR_MAPPING_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('02', 'Demo Ready', NULL, 1, 2, NULL, NULL);
INSERT INTO TUTOR_MAPPING_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('03', 'Demo Scheduled', NULL, 1, 3, NULL, NULL);

UPDATE TUTOR_MAPPER
SET MAPPING_STATUS = '01'
WHERE MAPPING_STATUS = 'PENDING';

UPDATE TUTOR_MAPPER
SET MAPPING_STATUS = '02'
WHERE MAPPING_STATUS = 'DEMO_READY';

UPDATE TUTOR_MAPPER
SET MAPPING_STATUS = '03'
WHERE MAPPING_STATUS = 'DEMO_SCHEDULED';

INSERT INTO DEMO_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('01', 'Scheduled', NULL, 1, 1, NULL, NULL);
INSERT INTO DEMO_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('02', 'Successfull', NULL, 1, 2, NULL, NULL);
INSERT INTO DEMO_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('03', 'Failed', NULL, 1, 3, NULL, NULL);
INSERT INTO DEMO_STATUS_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('04', 'Canceled', NULL, 1, 3, NULL, NULL);

UPDATE DEMO_TRACKER
SET DEMO_STATUS = '01'
WHERE DEMO_STATUS = 'SCHEDULED';

UPDATE DEMO_TRACKER
SET DEMO_STATUS = '02'
WHERE DEMO_STATUS = 'SUCCESS';

UPDATE DEMO_TRACKER
SET DEMO_STATUS = '03'
WHERE DEMO_STATUS = 'FAILED';

UPDATE DEMO_TRACKER
SET DEMO_STATUS = '04'
WHERE DEMO_STATUS = 'CANCELED';

ALTER TABLE EMPLOYEE
ADD EMAIL_ID VARCHAR(100),
ADD CONTACT_NUMBER VARCHAR(15);

UPDATE EMPLOYEE SET
EMAIL_ID = CONCAT(USER_ID, '@', EMAIL_DOMAIN);

UPDATE EMPLOYEE SET
CONTACT_NUMBER = '9739936482'
WHERE EMPLOYEE_ID = 1;

UPDATE EMPLOYEE SET
CONTACT_NUMBER = '6391222214'
WHERE EMPLOYEE_ID = 2;

UPDATE EMPLOYEE SET
CONTACT_NUMBER = '8588076802'
WHERE EMPLOYEE_ID = 3;

CREATE TABLE EMPLOYEE_OTHER_EMAILS(
EMPLOYEE_OTHER_EMAILS_ID INT NOT NULL AUTO_INCREMENT,
EMPLOYEE_ID INT NOT NULL,
EMAIL_ID VARCHAR(100),
PRIMARY KEY (EMPLOYEE_OTHER_EMAILS_ID));

CREATE TABLE EMPLOYEE_OTHER_CONTACT_NUMBERS(
EMPLOYEE_OTHER_CONTACT_NUMBERS_ID INT NOT NULL AUTO_INCREMENT,
EMPLOYEE_ID INT NOT NULL,
CONTACT_NUMBER VARCHAR(10),
PRIMARY KEY (EMPLOYEE_OTHER_CONTACT_NUMBERS_ID));

ALTER TABLE REGISTERED_TUTOR
ADD USER_TYPE VARCHAR(100);

ALTER TABLE SUBSCRIBED_CUSTOMER
ADD USER_TYPE VARCHAR(100);

UPDATE REGISTERED_TUTOR SET
USER_TYPE = 'Tutor';

UPDATE SUBSCRIBED_CUSTOMER SET
USER_TYPE = 'Customer';

ALTER TABLE EMPLOYEE
ADD RECORD_LAST_UPDATED_MILLIS BIGINT,
ADD UPDATED_BY VARCHAR(100);

UPDATE EMPLOYEE SET
RECORD_LAST_UPDATED_MILLIS = UNIX_TIMESTAMP(SYSDATE()) * 1000,
UPDATED_BY = 'SYSTEM';

ALTER TABLE LOGON_TRACKER
DROP COLUMN LOGIN_TIME;

ALTER TABLE PASSWORD_CHANGE_TRACKER
DROP COLUMN CHANGE_TIME;

ALTER TABLE APP_ERROR_REPORT
DROP COLUMN OCCURED_AT;

ALTER TABLE MAIL_QUEUE
DROP COLUMN ENTRY_DATE,
DROP COLUMN SEND_DATE,
DROP COLUMN ERROR_DATE;

CREATE TABLE ENQUIRY_ATTACHED_CUSTOMER_INFO(
ENQUIRY_ATTACHED_CUSTOMER_INFO_ID INT NOT NULL AUTO_INCREMENT,
ENQUIRY_ID INT NOT NULL,
CUSTOMER_ID INT NOT NULL,
STUDENT_NAME VARCHAR(100) NULL,
EMAIL_ID VARCHAR(100) NOT NULL,
CONTACT_NUMBER VARCHAR(15) NOT NULL,
PRIMARY KEY (ENQUIRY_ATTACHED_CUSTOMER_INFO_ID));

RENAME TABLE REGISTERED_TUTOR_OTHER_EMAILS TO REGISTERED_TUTOR_ALL_EMAIL_ID;

DROP TABLE REGISTERED_TUTOR_ALL_EMAIL_ID;

DROP TABLE REGISTERED_TUTOR_OTHER_CONTACT_NUMBERS;
DROP TABLE SUBSCRIBED_CUSTOMER_OTHER_EMAILS;
DROP TABLE SUBSCRIBED_CUSTOMER_OTHER_CONTACT_NUMBERS;
DROP TABLE EMPLOYEE_OTHER_EMAILS;
DROP TABLE EMPLOYEE_OTHER_CONTACT_NUMBERS;

CREATE TABLE REGISTERED_TUTOR_EMAIL_ID(
REGISTERED_TUTOR_EMAIL_ID_ID INT NOT NULL AUTO_INCREMENT,
TUTOR_ID INT NOT NULL,
EMAIL_ID VARCHAR(100) NOT NULL,
IS_PRIMARY VARCHAR(2),
PRIMARY KEY (REGISTERED_TUTOR_EMAIL_ID_ID));

CREATE TABLE REGISTERED_TUTOR_CONTACT_NUMBER(
REGISTERED_TUTOR_CONTACT_NUMBER_ID INT NOT NULL AUTO_INCREMENT,
TUTOR_ID INT NOT NULL,
CONTACT_NUMBER VARCHAR(10) NOT NULL,
IS_PRIMARY VARCHAR(2),
PRIMARY KEY (REGISTERED_TUTOR_CONTACT_NUMBER_ID));

CREATE TABLE SUBSCRIBED_CUSTOMER_EMAIL_ID(
SUBSCRIBED_CUSTOMER_EMAIL_ID_ID INT NOT NULL AUTO_INCREMENT,
CUSTOMER_ID INT NOT NULL,
EMAIL_ID VARCHAR(100) NOT NULL,
IS_PRIMARY VARCHAR(2),
PRIMARY KEY (SUBSCRIBED_CUSTOMER_EMAIL_ID_ID));

CREATE TABLE SUBSCRIBED_CUSTOMER_CONTACT_NUMBER(
SUBSCRIBED_CUSTOMER_CONTACT_NUMBER_ID INT NOT NULL AUTO_INCREMENT,
CUSTOMER_ID INT NOT NULL,
CONTACT_NUMBER VARCHAR(10) NOT NULL,
IS_PRIMARY VARCHAR(2),
PRIMARY KEY (SUBSCRIBED_CUSTOMER_CONTACT_NUMBER_ID));

CREATE TABLE EMPLOYEE_EMAIL_ID(
EMPLOYEE_EMAIL_ID_ID INT NOT NULL AUTO_INCREMENT,
EMPLOYEE_ID INT NOT NULL,
EMAIL_ID VARCHAR(100) NOT NULL,
IS_PRIMARY VARCHAR(2),
PRIMARY KEY (EMPLOYEE_EMAIL_ID_ID));

CREATE TABLE EMPLOYEE_CONTACT_NUMBER(
EMPLOYEE_CONTACT_NUMBER_ID INT NOT NULL AUTO_INCREMENT,
EMPLOYEE_ID INT NOT NULL,
CONTACT_NUMBER VARCHAR(10) NOT NULL,
IS_PRIMARY VARCHAR(2),
PRIMARY KEY (EMPLOYEE_CONTACT_NUMBER_ID));

RENAME TABLE ENQUIRIES TO ENQUIRY;
RENAME TABLE DEMO_TRACKER TO DEMO;

ALTER TABLE SUBMIT_QUERY
ADD IS_MAIL_SENT VARCHAR(2),
ADD MAIL_SENT_MILLIS BIGINT;

ALTER TABLE DEMO
ADD IS_SUBSCRIPTION_CREATED VARCHAR(2),
ADD SUBSCRIPTION_CREATED_MILLIS BIGINT;

ALTER TABLE TUTOR_MAPPER
ADD IS_ENQUIRY_CLOSED VARCHAR(2),
ADD ENQUIRY_CLOSED_MILLIS BIGINT;

ALTER TABLE SUBSCRIPTION_PACKAGE
ADD ENQUIRY_ID INT NOT NULL,
ADD TUTOR_MAPPER_ID INT NOT NULL,
ADD DEMO_ID INT NOT NULL;

ALTER TABLE SUBSCRIPTION_PACKAGE
ADD CREATED_MILLIS BIGINT;

INSERT INTO REGISTERED_TUTOR_EMAIL_ID (TUTOR_ID, EMAIL_ID, IS_PRIMARY) SELECT TUTOR_ID, EMAIL_ID, 'Y' FROM REGISTERED_TUTOR;
INSERT INTO REGISTERED_TUTOR_CONTACT_NUMBER (TUTOR_ID, CONTACT_NUMBER, IS_PRIMARY) SELECT TUTOR_ID, CONTACT_NUMBER, 'Y' FROM REGISTERED_TUTOR;
INSERT INTO SUBSCRIBED_CUSTOMER_EMAIL_ID (CUSTOMER_ID, EMAIL_ID, IS_PRIMARY) SELECT CUSTOMER_ID, EMAIL_ID, 'Y' FROM SUBSCRIBED_CUSTOMER;
INSERT INTO SUBSCRIBED_CUSTOMER_CONTACT_NUMBER (CUSTOMER_ID, CONTACT_NUMBER, IS_PRIMARY) SELECT CUSTOMER_ID, CONTACT_NUMBER, 'Y' FROM SUBSCRIBED_CUSTOMER;
INSERT INTO EMPLOYEE_EMAIL_ID (EMPLOYEE_ID, EMAIL_ID, IS_PRIMARY) SELECT EMPLOYEE_ID, EMAIL_ID, 'Y' FROM EMPLOYEE;
INSERT INTO EMPLOYEE_CONTACT_NUMBER (EMPLOYEE_ID, CONTACT_NUMBER, IS_PRIMARY) SELECT EMPLOYEE_ID, CONTACT_NUMBER, 'Y' FROM EMPLOYEE;

ALTER TABLE REGISTERED_TUTOR
DROP COLUMN EMAIL_ID,
DROP COLUMN CONTACT_NUMBER;

ALTER TABLE SUBSCRIBED_CUSTOMER
DROP COLUMN EMAIL_ID,
DROP COLUMN CONTACT_NUMBER;

ALTER TABLE EMPLOYEE
DROP COLUMN EMAIL_ID,
DROP COLUMN CONTACT_NUMBER;

ALTER TABLE DEMO
ADD IS_ENQUIRY_CLOSED VARCHAR(2),
ADD ENQUIRY_CLOSED_MILLIS BIGINT;

ALTER TABLE ENQUIRY
ADD ENQUIRY_EMAIL VARCHAR(100),
ADD ENQUIRY_CONTACT_NUMBER VARCHAR(10);

UPDATE ENQUIRY EN SET
ENQUIRY_EMAIL = (SELECT EMAIL_ID FROM SUBSCRIBED_CUSTOMER_EMAIL_ID SC WHERE SC.CUSTOMER_ID = EN.CUSTOMER_ID),
ENQUIRY_CONTACT_NUMBER = (SELECT CONTACT_NUMBER FROM SUBSCRIBED_CUSTOMER_CONTACT_NUMBER SC WHERE SC.CUSTOMER_ID = EN.CUSTOMER_ID);

CREATE TABLE FORGOT_PASSWORD_TOKEN(
FORGOT_PASSWORD_TOKEN_ID INT NOT NULL AUTO_INCREMENT,
USER_ID VARCHAR(100) NOT NULL,
USER_TYPE VARCHAR(100) NOT NULL,
TOKEN VARCHAR(1000) NOT NULL,
ISSUE_DATE_MILLIS BIGINT NOT NULL,
EXPIRY_DATE_MILLIS BIGINT NOT NULL,
IS_VALID VARCHAR(2) NOT NULL,
PRIMARY KEY (FORGOT_PASSWORD_TOKEN_ID));
-- Prod deployed (do not modify this file now)