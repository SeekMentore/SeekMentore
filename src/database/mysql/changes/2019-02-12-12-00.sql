USE SEEK_MENTORE;

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
-- Done in Office System
-- Done in Home System

ALTER TABLE DEMO
ADD DEMO_SERIAL_ID VARCHAR(500) AFTER DEMO_TRACKER_ID;

ALTER TABLE SUBSCRIPTION_PACKAGE
ADD DEMO_SERIAL_ID VARCHAR(500) AFTER DEMO_ID;

-- Create the Serial ID Now --

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