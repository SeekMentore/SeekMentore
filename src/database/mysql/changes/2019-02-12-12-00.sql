USE SEEK_MENTORE;

-- Special Interim Queries

ALTER TABLE DEMO
ADD DEMO_SERIAL_ID VARCHAR(500) AFTER DEMO_TRACKER_ID;

ALTER TABLE TUTOR_MAPPER
ADD TUTOR_MAPPER_SERIAL_ID VARCHAR(500) AFTER TUTOR_MAPPER_ID;

ALTER TABLE ENQUIRY
ADD ENQUIRY_SERIAL_ID VARCHAR(500) AFTER ENQUIRY_ID;

ALTER TABLE SUBSCRIBED_CUSTOMER
ADD CUSTOMER_SERIAL_ID VARCHAR(500) AFTER CUSTOMER_ID;

ALTER TABLE SUBSCRIBED_CUSTOMER_EMAIL_ID
ADD SUBSCRIBED_CUSTOMER_EMAIL_ID_SERIAL_ID VARCHAR(500) AFTER SUBSCRIBED_CUSTOMER_EMAIL_ID_ID;

ALTER TABLE SUBSCRIBED_CUSTOMER_CONTACT_NUMBER
ADD SUBSCRIBED_CUSTOMER_CONTACT_NUMBER_SERIAL_ID VARCHAR(500) AFTER SUBSCRIBED_CUSTOMER_CONTACT_NUMBER_ID;

ALTER TABLE REGISTERED_TUTOR
ADD TUTOR_SERIAL_ID VARCHAR(500) AFTER TUTOR_ID;

ALTER TABLE REGISTERED_TUTOR_EMAIL_ID
ADD REGISTERED_TUTOR_EMAIL_ID_SERIAL_ID VARCHAR(500) AFTER REGISTERED_TUTOR_EMAIL_ID_ID;

ALTER TABLE REGISTERED_TUTOR_CONTACT_NUMBER
ADD REGISTERED_TUTOR_CONTACT_NUMBER_SERIAL_ID VARCHAR(500) AFTER REGISTERED_TUTOR_CONTACT_NUMBER_ID;

ALTER TABLE TUTOR_DOCUMENT
ADD DOCUMENT_SERIAL_ID VARCHAR(500) AFTER DOCUMENT_ID;

ALTER TABLE MAIL_QUEUE
ADD MAIL_SERIAL_ID VARCHAR(500) AFTER MAIL_ID;

ALTER TABLE MAIL_ATTACHMENTS
ADD ATTACHMENT_SERIAL_ID VARCHAR(500) AFTER ATTACHMENT_ID;

ALTER TABLE APP_ERROR_REPORT
ADD ERROR_SERIAL_ID VARCHAR(500) AFTER ERROR_ID;

ALTER TABLE LOGON_TRACKER
ADD LOGON_SERIAL_ID VARCHAR(500) AFTER LOGON_ID;

ALTER TABLE PASSWORD_CHANGE_TRACKER
ADD PASSWORD_CHANGE_SERIAL_ID VARCHAR(500) AFTER PASSWORD_CHANGE_ID;

ALTER TABLE EMPLOYEE
ADD EMPLOYEE_SERIAL_ID VARCHAR(500) AFTER EMPLOYEE_ID;

ALTER TABLE EMPLOYEE_EMAIL_ID
ADD EMPLOYEE_EMAIL_ID_SERIAL_ID VARCHAR(500) AFTER EMPLOYEE_EMAIL_ID_ID;

ALTER TABLE EMPLOYEE_CONTACT_NUMBER
ADD EMPLOYEE_CONTACT_NUMBER_SERIAL_ID VARCHAR(500) AFTER EMPLOYEE_CONTACT_NUMBER_ID;

ALTER TABLE BECOME_TUTOR
ADD BECOME_TUTOR_SERIAL_ID VARCHAR(500) AFTER TENTATIVE_TUTOR_ID;

ALTER TABLE FIND_TUTOR
ADD FIND_TUTOR_SERIAL_ID VARCHAR(500) AFTER ENQUIRY_ID;

ALTER TABLE SUBMIT_QUERY
ADD QUERY_SERIAL_ID VARCHAR(500) AFTER QUERY_ID;

ALTER TABLE SUBSCRIBE_WITH_US
ADD SUBSCRIBE_WITH_US_SERIAL_ID VARCHAR(500) AFTER TENTATIVE_SUBSCRIPTION_ID;

ALTER TABLE COMPLAINT
ADD COMPLAINT_SERIAL_ID VARCHAR(500) AFTER COMPLAINT_ID;

-- Create the Serial ID via JAVA code now before executing the below queries --

CREATE TABLE PACKAGE_ASSIGNMENT(
PACKAGE_ASSIGNMENT_ID INT NOT NULL AUTO_INCREMENT,
SUBSCRIPTION_PACKAGE_ID INT NOT NULL,
TOTAL_HOURS INT,
START_DATE_MILLIS BIGINT,
COMPLETED_HOURS INT,
COMPLETED_MINUTES INT,
END_DATE_MILLIS BIGINT,
WHO_ACTED VARCHAR(100),
ADMIN_REMARKS VARCHAR(2000),
CUSTOMER_REMARKS VARCHAR(2000),
TUTOR_REMARKS VARCHAR(2000),
ACTION_DATE_MILLIS BIGINT,
IS_CUSTOMER_GRIEVED VARCHAR(2),
IS_TUTOR_GRIEVED VARCHAR(2),
CUSTOMER_HAPPINESS_INDEX VARCHAR(2),
TUTOR_HAPPINESS_INDEX VARCHAR(2),
RECORD_LAST_UPDATED_MILLIS BIGINT,
UPDATED_BY VARCHAR(100),
PRIMARY KEY (PACKAGE_ASSIGNMENT_ID));

ALTER TABLE SUBSCRIPTION_PACKAGE
DROP COLUMN TOTAL_HOURS,
DROP COLUMN COMPLETED_HOURS,
DROP COLUMN COMPLETED_MINUTES;

ALTER TABLE PACKAGE_ASSIGNMENT
ADD CREATED_MILLIS BIGINT;

CREATE TABLE HAPPINESS_INDEX_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

INSERT INTO HAPPINESS_INDEX_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('01', 'Very Poor', NULL, 1, 1, NULL, NULL);
INSERT INTO HAPPINESS_INDEX_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('02', 'Fair Enough', NULL, 1, 2, NULL, NULL);
INSERT INTO HAPPINESS_INDEX_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('03', 'Good', NULL, 1, 3, NULL, NULL);
INSERT INTO HAPPINESS_INDEX_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('04', 'Very Good', NULL, 1, 2, NULL, NULL);
INSERT INTO HAPPINESS_INDEX_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('05', 'Superb', NULL, 1, 3, NULL, NULL);

CREATE TABLE PACKAGE_BILLING_TYPE_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

INSERT INTO PACKAGE_BILLING_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('01', 'Per Minute', NULL, 1, 1, NULL, NULL);
INSERT INTO PACKAGE_BILLING_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('02', 'Hourly', NULL, 1, 2, NULL, NULL);
INSERT INTO PACKAGE_BILLING_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('03', 'Daily', NULL, 1, 3, NULL, NULL);
INSERT INTO PACKAGE_BILLING_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('04', 'Half Weekly (MWF/TTS)', NULL, 1, 2, NULL, NULL);
INSERT INTO PACKAGE_BILLING_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('05', 'Weekly (MTWTFS)', NULL, 1, 3, NULL, NULL);
INSERT INTO PACKAGE_BILLING_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('06', 'Monthly (Sunday Off)', NULL, 1, 3, NULL, NULL);
INSERT INTO PACKAGE_BILLING_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('07', 'Quaterly (3 Months)', NULL, 1, 3, NULL, NULL);
INSERT INTO PACKAGE_BILLING_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('08', 'Half Yearly', NULL, 1, 3, NULL, NULL);
INSERT INTO PACKAGE_BILLING_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('09', 'Annualy', NULL, 1, 3, NULL, NULL);
INSERT INTO PACKAGE_BILLING_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('10', 'Fixed', NULL, 1, 3, NULL, NULL);

ALTER TABLE SUBSCRIPTION_PACKAGE
ADD PACKAGE_BILLING_TYPE VARCHAR(2),
ADD FINALIZED_RATE INT;

ALTER TABLE SUBSCRIPTION_PACKAGE
CHANGE FINALIZED_RATE FINALIZED_RATE_CLIENT INT;

ALTER TABLE SUBSCRIPTION_PACKAGE
ADD FINALIZED_RATE_TUTOR INT,
ADD ADDITIONAL_DETAILS VARCHAR(2000),
ADD ACTIVATING_REMARKS VARCHAR(2000),
ADD TERMINATING_REMARKS VARCHAR(2000);

ALTER TABLE SUBSCRIPTION_PACKAGE
CHANGE ADDITIONAL_DETAILS ADDITIONAL_DETAILS_CLIENT VARCHAR(2000);

ALTER TABLE SUBSCRIPTION_PACKAGE
ADD ADDITIONAL_DETAILS_TUTOR VARCHAR(2000);

ALTER TABLE MAIL_ATTACHMENTS
ADD IS_FS_STORED VARCHAR(2),
ADD FS_KEY VARCHAR(1000);

ALTER TABLE SUBSCRIPTION_PACKAGE
ADD SUBSCRIPTION_PACKAGE_SERIAL_ID VARCHAR(500);

ALTER TABLE PACKAGE_ASSIGNMENT
ADD PACKAGE_ASSIGNMENT_SERIAL_ID VARCHAR(500);

ALTER TABLE PACKAGE_ASSIGNMENT
DROP COLUMN SUBSCRIPTION_PACKAGE_ID;

ALTER TABLE PACKAGE_ASSIGNMENT
ADD SUBSCRIPTION_PACKAGE_SERIAL_ID VARCHAR(500);

UPDATE DEMO SET 
IS_SUBSCRIPTION_CREATED = NULL,
IS_ENQUIRY_CLOSED = NULL
WHERE DEMO_STATUS = '02';

DELETE FROM SUBSCRIPTION_PACKAGE;

ALTER TABLE SUBSCRIPTION_PACKAGE
CHANGE SUBSCRIPTION_PACKAGE_SERIAL_ID SUBSCRIPTION_PACKAGE_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE PACKAGE_ASSIGNMENT
CHANGE PACKAGE_ASSIGNMENT_SERIAL_ID PACKAGE_ASSIGNMENT_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

CREATE TABLE CONTRACT_TYPE_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

INSERT INTO CONTRACT_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('01', 'Customer Subscription Package', NULL, 1, 1, NULL, NULL);

CREATE TABLE CONTRACT(
CONTRACT_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE,
CONTRACT_TYPE VARCHAR(2) NOT NULL,
CONTRACT_INITIATED_DATE_MILLIS BIGINT NOT NULL,
CONTRACT_TERMINATED_DATE_MILLIS BIGINT NOT NULL,
IS_ACTIVE VARCHAR(2) NOT NULL,
FS_KEY VARCHAR(1000) NOT NULL,
RECORD_LAST_UPDATED_MILLIS BIGINT NOT NULL,
UPDATED_BY VARCHAR(100) NOT NULL,
PRIMARY KEY (CONTRACT_SERIAL_ID));

ALTER TABLE SUBSCRIPTION_PACKAGE
ADD CONTRACT_SERIAL_ID VARCHAR(500);

ALTER TABLE CONTRACT
CHANGE CONTRACT_INITIATED_DATE_MILLIS INITIATED_DATE_MILLIS BIGINT NOT NULL,
CHANGE CONTRACT_TERMINATED_DATE_MILLIS TERMINATED_DATE_MILLIS BIGINT NOT NULL;

ALTER TABLE CONTRACT
CHANGE TERMINATED_DATE_MILLIS TERMINATED_DATE_MILLIS BIGINT;

ALTER TABLE SUBSCRIPTION_PACKAGE
DROP COLUMN SUBSCRIPTION_PACKAGE_ID;

ALTER TABLE SUBSCRIPTION_PACKAGE
ADD PRIMARY KEY(SUBSCRIPTION_PACKAGE_SERIAL_ID);

ALTER TABLE PACKAGE_ASSIGNMENT
DROP COLUMN PACKAGE_ASSIGNMENT_ID;

ALTER TABLE PACKAGE_ASSIGNMENT
ADD PRIMARY KEY(PACKAGE_ASSIGNMENT_SERIAL_ID);

CREATE TABLE ASSIGNMENT_ATTENDANCE(
ASSIGNMENT_ATTENDANCE_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE,
PACKAGE_ASSIGNMENT_SERIAL_ID VARCHAR(500) NOT NULL,
ENTRY_DATE_TIME BIGINT NOT NULL,
EXIT_DATE_TIME BIGINT NOT NULL,
DURATION_HOURS INT NOT NULL,
DURATION_MINUTES INT NOT NULL,
TOPICS_TAUGHT VARCHAR(2000) NOT NULL,
CLASSWORK_PROVIDED VARCHAR(2000),
HOMEWORK_PROVIDED VARCHAR(2000),
TEST_PROVIDED VARCHAR(2000),
TUTOR_REMARKS VARCHAR(2000),
TUTOR_PUNCTUALITY_INDEX VARCHAR(2),
PUNCTUALITY_REMARKS VARCHAR(2000),
TUTOR_EXPERTISE_INDEX VARCHAR(2),
EXPERTISE_REMARKS VARCHAR(2000),
TUTOR_KNOWLEDGE_INDEX VARCHAR(2),
KNOWLEDGE_REMARKS VARCHAR(2000),
STUDENT_REMARKS VARCHAR(2000),
PRIMARY KEY (ASSIGNMENT_ATTENDANCE_SERIAL_ID));

CREATE TABLE ASSIGNMENT_ATTENDANCE_DOCUMENT(
ASSIGNMENT_ATTENDANCE_DOCUMENT_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE,
ASSIGNMENT_ATTENDANCE_SERIAL_ID VARCHAR(500) NOT NULL,
ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE VARCHAR(2) NOT NULL,
FS_KEY VARCHAR(1000) NOT NULL,
ACTION_DATE_MILLIS BIGINT NOT NULL,
WHO_ACTED VARCHAR(100) NOT NULL,
PRIMARY KEY (ASSIGNMENT_ATTENDANCE_DOCUMENT_SERIAL_ID));

ALTER TABLE ASSIGNMENT_ATTENDANCE
CHANGE ENTRY_DATE_TIME ENTRY_DATE_TIME_MILLIS BIGINT NOT NULL,
CHANGE EXIT_DATE_TIME EXIT_DATE_TIME_MILLIS BIGINT NOT NULL;

ALTER TABLE ASSIGNMENT_ATTENDANCE
ADD RECORD_LAST_UPDATED_MILLIS BIGINT NOT NULL,
ADD UPDATED_BY VARCHAR(100) NOT NULL;

ALTER TABLE ASSIGNMENT_ATTENDANCE_DOCUMENT
ADD RECORD_LAST_UPDATED_MILLIS BIGINT NOT NULL,
ADD UPDATED_BY VARCHAR(100) NOT NULL;

ALTER TABLE ASSIGNMENT_ATTENDANCE_DOCUMENT
ADD FILENAME VARCHAR(500) NOT NULL;

ALTER TABLE ASSIGNMENT_ATTENDANCE_DOCUMENT
CHANGE ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE DOCUMENT_TYPE VARCHAR(2) NOT NULL;

RENAME TABLE DOCUMENT_TYPE_LOOKUP TO TUTOR_DOCUMENT_TYPE_LOOKUP;

CREATE TABLE ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

INSERT INTO ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('01', 'Classwork', NULL, 1, 1, NULL, NULL);
INSERT INTO ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('02', 'Homework', NULL, 1, 1, NULL, NULL);
INSERT INTO ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('03', 'Test', NULL, 1, 1, NULL, NULL);
INSERT INTO ASSIGNMENT_ATTENDANCE_DOCUMENT_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('04', 'Other', NULL, 1, 1, NULL, NULL);

ALTER TABLE ASSIGNMENT_ATTENDANCE
ADD UPDATED_BY_USER_TYPE VARCHAR(100) NOT NULL;

ALTER TABLE ASSIGNMENT_ATTENDANCE_DOCUMENT
ADD UPDATED_BY_USER_TYPE VARCHAR(100) NOT NULL;

ALTER TABLE ASSIGNMENT_ATTENDANCE_DOCUMENT
CHANGE ACTION_DATE_MILLIS ACTION_DATE_MILLIS BIGINT,
CHANGE WHO_ACTED WHO_ACTED VARCHAR(100);

ALTER TABLE SUBSCRIPTION_PACKAGE
ADD DEMO_SERIAL_ID VARCHAR(500) AFTER DEMO_ID;

UPDATE SUBSCRIPTION_PACKAGE S SET
S.DEMO_SERIAL_ID = (SELECT D.DEMO_SERIAL_ID FROM DEMO D WHERE D.DEMO_TRACKER_ID = S.DEMO_ID);

ALTER TABLE DEMO
CHANGE DEMO_SERIAL_ID DEMO_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE SUBSCRIPTION_PACKAGE
CHANGE DEMO_SERIAL_ID DEMO_SERIAL_ID VARCHAR(500) NOT NULL;

ALTER TABLE DEMO
DROP COLUMN DEMO_TRACKER_ID;

ALTER TABLE SUBSCRIPTION_PACKAGE
DROP COLUMN DEMO_ID;

ALTER TABLE DEMO
ADD RESCHEDULED_FROM_DEMO_SERIAL_ID VARCHAR(500);

ALTER TABLE TUTOR_MAPPER
CHANGE TUTOR_MAPPER_SERIAL_ID TUTOR_MAPPER_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE DEMO
ADD TUTOR_MAPPER_SERIAL_ID VARCHAR(500) NOT NULL AFTER TUTOR_MAPPER_ID;

UPDATE DEMO D SET
D.TUTOR_MAPPER_SERIAL_ID = (SELECT T.TUTOR_MAPPER_SERIAL_ID FROM TUTOR_MAPPER T WHERE T.TUTOR_MAPPER_ID = D.TUTOR_MAPPER_ID);

ALTER TABLE SUBSCRIPTION_PACKAGE
ADD TUTOR_MAPPER_SERIAL_ID VARCHAR(500) NOT NULL AFTER TUTOR_MAPPER_ID;

UPDATE SUBSCRIPTION_PACKAGE S SET
S.TUTOR_MAPPER_SERIAL_ID = (SELECT D.TUTOR_MAPPER_SERIAL_ID FROM DEMO D WHERE D.DEMO_SERIAL_ID = S.DEMO_SERIAL_ID);

ALTER TABLE TUTOR_MAPPER
DROP COLUMN TUTOR_MAPPER_ID;

ALTER TABLE DEMO
DROP COLUMN TUTOR_MAPPER_ID;

ALTER TABLE SUBSCRIPTION_PACKAGE
DROP COLUMN TUTOR_MAPPER_ID;

-- Reverting back all enquiries to PENDING
DELETE FROM TUTOR_MAPPER;
DELETE FROM DEMO;
DELETE FROM SUBSCRIPTION_PACKAGE;
DELETE FROM CONTRACT;
DELETE FROM PACKAGE_ASSIGNMENT;
DELETE FROM ASSIGNMENT_ATTENDANCE;
DELETE FROM ASSIGNMENT_ATTENDANCE_DOCUMENT;

UPDATE ENQUIRY SET 
MATCH_STATUS = '01',
IS_MAPPED = NULL,
TUTOR_ID = NULL,
ADMIN_REMARKS = NULL;

DELETE FROM AWS_S3_DELETE_REPORT;

ALTER TABLE TUTOR_MAPPER
ADD ENQUIRY_SERIAL_ID VARCHAR(500) AFTER ENQUIRY_ID;

ALTER TABLE SUBSCRIPTION_PACKAGE
ADD ENQUIRY_SERIAL_ID VARCHAR(500) AFTER ENQUIRY_ID;

ALTER TABLE TUTOR_MAPPER
DROP COLUMN ENQUIRY_ID;

ALTER TABLE SUBSCRIPTION_PACKAGE
DROP COLUMN ENQUIRY_ID;

ALTER TABLE ENQUIRY
CHANGE ENQUIRY_SERIAL_ID ENQUIRY_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE ENQUIRY
DROP COLUMN ENQUIRY_ID;

ALTER TABLE ENQUIRY
ADD CUSTOMER_SERIAL_ID VARCHAR(500) AFTER CUSTOMER_ID;

ALTER TABLE SUBSCRIPTION_PACKAGE
ADD CUSTOMER_SERIAL_ID VARCHAR(500) AFTER CUSTOMER_ID;

ALTER TABLE SUBSCRIBED_CUSTOMER_EMAIL_ID
ADD CUSTOMER_SERIAL_ID VARCHAR(500) AFTER CUSTOMER_ID;

ALTER TABLE SUBSCRIBED_CUSTOMER_CONTACT_NUMBER
ADD CUSTOMER_SERIAL_ID VARCHAR(500) AFTER CUSTOMER_ID;

UPDATE ENQUIRY E SET
E.CUSTOMER_SERIAL_ID = (SELECT C.CUSTOMER_SERIAL_ID FROM SUBSCRIBED_CUSTOMER C WHERE C.CUSTOMER_ID = E.CUSTOMER_ID);

UPDATE SUBSCRIBED_CUSTOMER_EMAIL_ID SE SET
SE.CUSTOMER_SERIAL_ID = (SELECT C.CUSTOMER_SERIAL_ID FROM SUBSCRIBED_CUSTOMER C WHERE C.CUSTOMER_ID = SE.CUSTOMER_ID);

UPDATE SUBSCRIBED_CUSTOMER_CONTACT_NUMBER SC SET
SC.CUSTOMER_SERIAL_ID = (SELECT C.CUSTOMER_SERIAL_ID FROM SUBSCRIBED_CUSTOMER C WHERE C.CUSTOMER_ID = SC.CUSTOMER_ID);

ALTER TABLE ENQUIRY
DROP COLUMN CUSTOMER_ID;

ALTER TABLE SUBSCRIPTION_PACKAGE
DROP COLUMN CUSTOMER_ID;

ALTER TABLE SUBSCRIBED_CUSTOMER_EMAIL_ID
DROP COLUMN CUSTOMER_ID;

ALTER TABLE SUBSCRIBED_CUSTOMER_CONTACT_NUMBER
DROP COLUMN CUSTOMER_ID;

ALTER TABLE SUBSCRIBED_CUSTOMER
CHANGE CUSTOMER_SERIAL_ID CUSTOMER_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE SUBSCRIBED_CUSTOMER
DROP COLUMN CUSTOMER_ID;

ALTER TABLE SUBSCRIBED_CUSTOMER_EMAIL_ID
CHANGE SUBSCRIBED_CUSTOMER_EMAIL_ID_SERIAL_ID SUBSCRIBED_CUSTOMER_EMAIL_ID_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE SUBSCRIBED_CUSTOMER_CONTACT_NUMBER
CHANGE SUBSCRIBED_CUSTOMER_CONTACT_NUMBER_SERIAL_ID SUBSCRIBED_CUSTOMER_CONTACT_NUMBER_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE SUBSCRIBED_CUSTOMER_EMAIL_ID
DROP COLUMN SUBSCRIBED_CUSTOMER_EMAIL_ID_ID;

ALTER TABLE SUBSCRIBED_CUSTOMER_CONTACT_NUMBER
DROP COLUMN SUBSCRIBED_CUSTOMER_CONTACT_NUMBER_ID;

ALTER TABLE ENQUIRY
ADD TUTOR_SERIAL_ID VARCHAR(500) AFTER TUTOR_ID;

ALTER TABLE TUTOR_MAPPER
ADD TUTOR_SERIAL_ID VARCHAR(500) AFTER TUTOR_ID;

ALTER TABLE TUTOR_DOCUMENT
ADD TUTOR_SERIAL_ID VARCHAR(500) AFTER TUTOR_ID;

ALTER TABLE BANK_DETAIL
ADD TUTOR_SERIAL_ID VARCHAR(500) AFTER TUTOR_ID;

ALTER TABLE SUBSCRIPTION_PACKAGE
ADD TUTOR_SERIAL_ID VARCHAR(500) AFTER TUTOR_ID;

UPDATE TUTOR_DOCUMENT TD SET
TD.TUTOR_SERIAL_ID = (SELECT T.TUTOR_SERIAL_ID FROM REGISTERED_TUTOR T WHERE T.TUTOR_ID = TD.TUTOR_ID);

ALTER TABLE REGISTERED_TUTOR
CHANGE TUTOR_SERIAL_ID TUTOR_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE ENQUIRY
DROP COLUMN TUTOR_ID;

ALTER TABLE TUTOR_MAPPER
DROP COLUMN TUTOR_ID;

ALTER TABLE BANK_DETAIL
DROP COLUMN TUTOR_ID;

ALTER TABLE SUBSCRIPTION_PACKAGE
DROP COLUMN TUTOR_ID;

ALTER TABLE REGISTERED_TUTOR_EMAIL_ID
ADD TUTOR_SERIAL_ID VARCHAR(500) AFTER TUTOR_ID;

ALTER TABLE REGISTERED_TUTOR_CONTACT_NUMBER
ADD TUTOR_SERIAL_ID VARCHAR(500) AFTER TUTOR_ID;

UPDATE REGISTERED_TUTOR_EMAIL_ID RE SET
RE.TUTOR_SERIAL_ID = (SELECT T.TUTOR_SERIAL_ID FROM REGISTERED_TUTOR T WHERE T.TUTOR_ID = RE.TUTOR_ID);

UPDATE REGISTERED_TUTOR_CONTACT_NUMBER RC SET
RC.TUTOR_SERIAL_ID = (SELECT T.TUTOR_SERIAL_ID FROM REGISTERED_TUTOR T WHERE T.TUTOR_ID = RC.TUTOR_ID);

ALTER TABLE REGISTERED_TUTOR_EMAIL_ID
DROP COLUMN TUTOR_ID;

ALTER TABLE REGISTERED_TUTOR_CONTACT_NUMBER
DROP COLUMN TUTOR_ID;

ALTER TABLE ENQUIRY_ATTACHED_CUSTOMER_INFO
ADD ENQUIRY_SERIAL_ID VARCHAR(500) AFTER ENQUIRY_ID,
ADD CUSTOMER_SERIAL_ID VARCHAR(500) AFTER CUSTOMER_ID,
ADD ENQUIRY_ATTACHED_CUSTOMER_INFO_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE AFTER ENQUIRY_ATTACHED_CUSTOMER_INFO_ID;

ALTER TABLE ENQUIRY_ATTACHED_CUSTOMER_INFO
DROP COLUMN ENQUIRY_ID,
DROP COLUMN CUSTOMER_ID,
DROP COLUMN ENQUIRY_ATTACHED_CUSTOMER_INFO_ID;

ALTER TABLE REGISTERED_TUTOR_EMAIL_ID
CHANGE REGISTERED_TUTOR_EMAIL_ID_SERIAL_ID REGISTERED_TUTOR_EMAIL_ID_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE REGISTERED_TUTOR_CONTACT_NUMBER
CHANGE REGISTERED_TUTOR_CONTACT_NUMBER_SERIAL_ID REGISTERED_TUTOR_CONTACT_NUMBER_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE REGISTERED_TUTOR_EMAIL_ID
DROP COLUMN REGISTERED_TUTOR_EMAIL_ID_ID;

ALTER TABLE REGISTERED_TUTOR_CONTACT_NUMBER
DROP COLUMN REGISTERED_TUTOR_CONTACT_NUMBER_ID;

ALTER TABLE TUTOR_DOCUMENT
CHANGE DOCUMENT_SERIAL_ID DOCUMENT_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE TUTOR_DOCUMENT
DROP COLUMN DOCUMENT_ID;

ALTER TABLE BANK_DETAIL
ADD BANK_ACCOUNT_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE AFTER BANK_ACCOUNT_ID;

ALTER TABLE BANK_DETAIL
DROP COLUMN BANK_ACCOUNT_ID;

ALTER TABLE MAIL_ATTACHMENTS
ADD MAIL_SERIAL_ID VARCHAR(500) AFTER MAIL_ID;

ALTER TABLE MAIL_QUEUE
CHANGE MAIL_SERIAL_ID MAIL_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

UPDATE MAIL_ATTACHMENTS ME SET
ME.MAIL_SERIAL_ID = (SELECT M.MAIL_SERIAL_ID FROM MAIL_QUEUE M WHERE M.MAIL_ID = ME.MAIL_ID);

ALTER TABLE MAIL_ATTACHMENTS
DROP COLUMN MAIL_ID;

ALTER TABLE MAIL_QUEUE
DROP COLUMN MAIL_ID;

ALTER TABLE MAIL_ATTACHMENTS
CHANGE ATTACHMENT_SERIAL_ID ATTACHMENT_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE MAIL_ATTACHMENTS
DROP COLUMN ATTACHMENT_ID;

ALTER TABLE APP_ERROR_REPORT
CHANGE ERROR_SERIAL_ID ERROR_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE APP_ERROR_REPORT
DROP COLUMN ERROR_ID;

DELETE FROM AWS_S3_DELETE_REPORT;

ALTER TABLE AWS_S3_DELETE_REPORT
ADD S3_DELETE_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE AFTER S3_DELETE_ID;

ALTER TABLE AWS_S3_DELETE_REPORT
DROP COLUMN S3_DELETE_ID;

DELETE FROM EMAIL_TEMPLATE_LOOKUP;

DELETE FROM EMAIL_TEMPLATE;

ALTER TABLE EMAIL_TEMPLATE
ADD EMAIL_TEMPLATE_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE AFTER EMAIL_TEMPLATE_ID;

ALTER TABLE EMAIL_TEMPLATE
DROP COLUMN EMAIL_TEMPLATE_ID;

ALTER TABLE LOGON_TRACKER
CHANGE LOGON_SERIAL_ID LOGON_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE LOGON_TRACKER
DROP COLUMN LOGON_ID;

RENAME TABLE LOGON_TRACKER TO LOGIN_TRACKER;

ALTER TABLE LOGIN_TRACKER
CHANGE LOGON_SERIAL_ID LOGIN_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE PASSWORD_CHANGE_TRACKER
CHANGE PASSWORD_CHANGE_SERIAL_ID PASSWORD_CHANGE_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE PASSWORD_CHANGE_TRACKER
DROP COLUMN PASSWORD_CHANGE_ID;

DELETE FROM FORGOT_PASSWORD_TOKEN;

ALTER TABLE FORGOT_PASSWORD_TOKEN
ADD FORGOT_PASSWORD_TOKEN_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE AFTER FORGOT_PASSWORD_TOKEN_ID;

ALTER TABLE FORGOT_PASSWORD_TOKEN
DROP COLUMN FORGOT_PASSWORD_TOKEN_ID;

ALTER TABLE EMPLOYEE_EMAIL_ID
ADD EMPLOYEE_SERIAL_ID VARCHAR(500) AFTER EMPLOYEE_ID;

ALTER TABLE EMPLOYEE_CONTACT_NUMBER
ADD EMPLOYEE_SERIAL_ID VARCHAR(500) AFTER EMPLOYEE_ID;

UPDATE EMPLOYEE_EMAIL_ID EE SET
EE.EMPLOYEE_SERIAL_ID = (SELECT E.EMPLOYEE_SERIAL_ID FROM EMPLOYEE E WHERE E.EMPLOYEE_ID = EE.EMPLOYEE_ID);

UPDATE EMPLOYEE_CONTACT_NUMBER EC SET
EC.EMPLOYEE_SERIAL_ID = (SELECT E.EMPLOYEE_SERIAL_ID FROM EMPLOYEE E WHERE E.EMPLOYEE_ID = EC.EMPLOYEE_ID);

ALTER TABLE EMPLOYEE_EMAIL_ID
DROP COLUMN EMPLOYEE_ID;

ALTER TABLE EMPLOYEE_CONTACT_NUMBER
DROP COLUMN EMPLOYEE_ID;

ALTER TABLE EMPLOYEE
CHANGE EMPLOYEE_SERIAL_ID EMPLOYEE_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE EMPLOYEE
DROP COLUMN EMPLOYEE_ID;

ALTER TABLE EMPLOYEE_EMAIL_ID
CHANGE EMPLOYEE_EMAIL_ID_SERIAL_ID EMPLOYEE_EMAIL_ID_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE EMPLOYEE_EMAIL_ID
DROP COLUMN EMPLOYEE_EMAIL_ID_ID;

ALTER TABLE EMPLOYEE_CONTACT_NUMBER
CHANGE EMPLOYEE_CONTACT_NUMBER_SERIAL_ID EMPLOYEE_CONTACT_NUMBER_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE EMPLOYEE_CONTACT_NUMBER
DROP COLUMN EMPLOYEE_CONTACT_NUMBER_ID;

ALTER TABLE REGISTERED_TUTOR
ADD BECOME_TUTOR_SERIAL_ID VARCHAR(500) AFTER TENTATIVE_TUTOR_ID;

UPDATE REGISTERED_TUTOR T SET
T.BECOME_TUTOR_SERIAL_ID = (SELECT B.BECOME_TUTOR_SERIAL_ID FROM BECOME_TUTOR B WHERE B.TENTATIVE_TUTOR_ID = T.TENTATIVE_TUTOR_ID);

ALTER TABLE BECOME_TUTOR
CHANGE BECOME_TUTOR_SERIAL_ID BECOME_TUTOR_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE REGISTERED_TUTOR
DROP COLUMN TENTATIVE_TUTOR_ID;

ALTER TABLE BECOME_TUTOR
DROP COLUMN TENTATIVE_TUTOR_ID;

ALTER TABLE SUBSCRIBED_CUSTOMER
ADD FIND_TUTOR_SERIAL_ID VARCHAR(500) AFTER FIND_TUTOR_ID;

UPDATE SUBSCRIBED_CUSTOMER C SET
C.FIND_TUTOR_SERIAL_ID = (SELECT F.FIND_TUTOR_SERIAL_ID FROM FIND_TUTOR F WHERE F.ENQUIRY_ID = C.FIND_TUTOR_ID);

ALTER TABLE FIND_TUTOR
CHANGE FIND_TUTOR_SERIAL_ID FIND_TUTOR_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE SUBSCRIBED_CUSTOMER
DROP COLUMN FIND_TUTOR_ID;

ALTER TABLE FIND_TUTOR
DROP COLUMN ENQUIRY_ID;

ALTER TABLE FIND_TUTOR
CHANGE ENQUIRY_STATUS APPLICATION_STATUS VARCHAR(100) NOT NULL;

ALTER TABLE FIND_TUTOR
CHANGE ENQUIRY_DATE_MILLIS APPLICATION_DATE_MILLIS BIGINT NOT NULL;

ALTER TABLE SUBMIT_QUERY
CHANGE QUERY_SERIAL_ID QUERY_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE SUBMIT_QUERY
DROP COLUMN QUERY_ID;

ALTER TABLE SUBSCRIBE_WITH_US
CHANGE SUBSCRIBE_WITH_US_SERIAL_ID SUBSCRIBE_WITH_US_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE SUBSCRIBE_WITH_US
DROP COLUMN TENTATIVE_SUBSCRIPTION_ID;

ALTER TABLE COMPLAINT
CHANGE COMPLAINT_SERIAL_ID COMPLAINT_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE;

ALTER TABLE COMPLAINT
DROP COLUMN COMPLAINT_ID;

DELETE FROM ALERT_REMINDER;

ALTER TABLE ALERT_REMINDER
ADD ALERT_REMINDER_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE AFTER ALERT_REMINDER_ID;

ALTER TABLE ALERT_REMINDER
DROP COLUMN ALERT_REMINDER_ID;

DELETE FROM TASK;

ALTER TABLE TASK
ADD TASK_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE AFTER TASK_ID;

ALTER TABLE TASK
DROP COLUMN TASK_ID;

DELETE FROM WORKFLOW;

ALTER TABLE WORKFLOW
ADD WORKFLOW_SERIAL_ID VARCHAR(500) NOT NULL UNIQUE AFTER WORKFLOW_ID;

ALTER TABLE WORKFLOW
DROP COLUMN WORKFLOW_ID;

ALTER TABLE SUBSCRIBED_CUSTOMER
ADD UPDATED_BY_USER_TYPE VARCHAR(100) NOT NULL AFTER UPDATED_BY;

UPDATE SUBSCRIBED_CUSTOMER C SET
UPDATED_BY_USER_TYPE = 'SYSTEM'
WHERE UPDATED_BY = 'SYSTEM_SCHEDULER';

UPDATE SUBSCRIBED_CUSTOMER C SET
UPDATED_BY_USER_TYPE = 'Employee'
WHERE UPDATED_BY_USER_TYPE IS NULL;

ALTER TABLE SUBSCRIBED_CUSTOMER
MODIFY FIND_TUTOR_SERIAL_ID VARCHAR(500) NOT NULL AFTER CUSTOMER_SERIAL_ID;

ALTER TABLE TUTOR_DOCUMENT
DROP COLUMN TUTOR_ID;

ALTER TABLE REGISTERED_TUTOR
DROP COLUMN TUTOR_ID;
-- Done in Home System
-- Done in Office System

ALTER TABLE ENQUIRY
CHANGE LOCATION_DETAILS LOCATION VARCHAR(10);