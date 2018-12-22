USE SEEK_MENTORE;

ALTER TABLE DEMO_TRACKER
ADD COLUMN DEMO_DATE_AND_TIME_MILLIS INT NOT NULL;

ALTER TABLE TUTOR_MAPPER
ADD COLUMN DEMO_DATE_AND_TIME_MILLIS INT NOT NULL;

ALTER TABLE TUTOR_MAPPER
DROP COLUMN DEMO_DATE_AND_TIME;

ALTER TABLE TUTOR_MAPPER
DROP COLUMN DEMO_DATE_AND_TIME_MILLIS;

-- Old queries

ALTER TABLE DEMO_TRACKER
DROP COLUMN DEMO_DATE_AND_TIME_MILLIS;

ALTER TABLE TUTOR_MAPPER
ADD COLUMN DEMO_DATE_AND_TIME TIMESTAMP;

ALTER TABLE REGISTERED_TUTOR
ADD COLUMN RECORD_LAST_UPDATED_MILLIS BIGINT;

ALTER TABLE SUBSCRIBED_CUSTOMER
ADD COLUMN RECORD_LAST_UPDATED_MILLIS BIGINT;

ALTER TABLE TUTOR_DOCUMENTS
ADD COLUMN ACTION_DATE_MILLIS BIGINT;

UPDATE REGISTERED_TUTOR SET
RECORD_LAST_UPDATED_MILLIS = UNIX_TIMESTAMP(RECORD_LAST_UPDATED) * 1000;

UPDATE SUBSCRIBED_CUSTOMER SET
RECORD_LAST_UPDATED_MILLIS = UNIX_TIMESTAMP(RECORD_LAST_UPDATED) * 1000;

UPDATE TUTOR_DOCUMENTS SET
ACTION_DATE_MILLIS = UNIX_TIMESTAMP(ACTION_DATE) * 1000;

ALTER TABLE REGISTERED_TUTOR
DROP COLUMN RECORD_LAST_UPDATED;

ALTER TABLE SUBSCRIBED_CUSTOMER
DROP COLUMN RECORD_LAST_UPDATED;

ALTER TABLE TUTOR_DOCUMENTS
DROP COLUMN ACTION_DATE;

ALTER TABLE BECOME_TUTOR
ADD COLUMN APPLICATION_DATE_MILLIS BIGINT,
ADD COLUMN CONTACTED_DATE_MILLIS BIGINT,
ADD COLUMN VERIFICATION_DATE_MILLIS BIGINT,
ADD COLUMN SUGGESTION_DATE_MILLIS BIGINT,
ADD COLUMN RECONTACTED_DATE_MILLIS BIGINT,
ADD COLUMN SELECTION_DATE_MILLIS BIGINT,
ADD COLUMN REJECTION_DATE_MILLIS BIGINT,
ADD COLUMN PREVIOUS_APPLICATION_DATE_MILLIS BIGINT,
ADD COLUMN RECORD_LAST_UPDATED_MILLIS BIGINT,
ADD COLUMN WHEN_MIGRATED_MILLIS BIGINT;

UPDATE BECOME_TUTOR SET
APPLICATION_DATE_MILLIS = UNIX_TIMESTAMP(APPLICATION_DATE) * 1000,
CONTACTED_DATE_MILLIS = UNIX_TIMESTAMP(CONTACTED_DATE) * 1000,
VERIFICATION_DATE_MILLIS = UNIX_TIMESTAMP(VERIFICATION_DATE) * 1000,
SUGGESTION_DATE_MILLIS = UNIX_TIMESTAMP(SUGGESTION_DATE) * 1000,
RECONTACTED_DATE_MILLIS = UNIX_TIMESTAMP(RECONTACTED_DATE) * 1000,
SELECTION_DATE_MILLIS = UNIX_TIMESTAMP(SELECTION_DATE) * 1000,
REJECTION_DATE_MILLIS = UNIX_TIMESTAMP(REJECTION_DATE) * 1000,
PREVIOUS_APPLICATION_DATE_MILLIS = UNIX_TIMESTAMP(PREVIOUS_APPLICATION_DATE) * 1000,
RECORD_LAST_UPDATED_MILLIS = UNIX_TIMESTAMP(RECORD_LAST_UPDATED) * 1000,
WHEN_MIGRATED_MILLIS = UNIX_TIMESTAMP(WHEN_MIGRATED) * 1000;

ALTER TABLE BECOME_TUTOR
DROP COLUMN APPLICATION_DATE,
DROP COLUMN CONTACTED_DATE,
DROP COLUMN VERIFICATION_DATE,
DROP COLUMN SUGGESTION_DATE,
DROP COLUMN SELECTION_DATE,
DROP COLUMN REJECTION_DATE,
DROP COLUMN PREVIOUS_APPLICATION_DATE,
DROP COLUMN RECORD_LAST_UPDATED,
DROP COLUMN WHEN_MIGRATED;

ALTER TABLE BECOME_TUTOR
ADD COLUMN UPDATED_BY VARCHAR(100);

UPDATE BECOME_TUTOR SET
UPDATED_BY = 'SYSTEM';

ALTER TABLE FIND_TUTOR
ADD COLUMN ENQUIRY_DATE_MILLIS BIGINT,
ADD COLUMN CONTACTED_DATE_MILLIS BIGINT,
ADD COLUMN VERIFICATION_DATE_MILLIS BIGINT,
ADD COLUMN SUGGESTION_DATE_MILLIS BIGINT,
ADD COLUMN RECONTACTED_DATE_MILLIS BIGINT,
ADD COLUMN SELECTION_DATE_MILLIS BIGINT,
ADD COLUMN REJECTION_DATE_MILLIS BIGINT,
ADD COLUMN RECORD_LAST_UPDATED_MILLIS BIGINT,
ADD COLUMN WHEN_MIGRATED_MILLIS BIGINT;

UPDATE FIND_TUTOR SET
ENQUIRY_DATE_MILLIS = UNIX_TIMESTAMP(ENQUIRY_DATE) * 1000,
CONTACTED_DATE_MILLIS = UNIX_TIMESTAMP(CONTACTED_DATE) * 1000,
VERIFICATION_DATE_MILLIS = UNIX_TIMESTAMP(VERIFICATION_DATE) * 1000,
SUGGESTION_DATE_MILLIS = UNIX_TIMESTAMP(SUGGESTION_DATE) * 1000,
RECONTACTED_DATE_MILLIS = UNIX_TIMESTAMP(RECONTACTED_DATE) * 1000,
SELECTION_DATE_MILLIS = UNIX_TIMESTAMP(SELECTION_DATE) * 1000,
REJECTION_DATE_MILLIS = UNIX_TIMESTAMP(REJECTION_DATE) * 1000,
RECORD_LAST_UPDATED_MILLIS = UNIX_TIMESTAMP(RECORD_LAST_UPDATED) * 1000,
WHEN_MIGRATED_MILLIS = UNIX_TIMESTAMP(WHEN_MIGRATED) * 1000;

ALTER TABLE FIND_TUTOR
DROP COLUMN ENQUIRY_DATE,
DROP COLUMN CONTACTED_DATE,
DROP COLUMN VERIFICATION_DATE,
DROP COLUMN SUGGESTION_DATE,
DROP COLUMN SELECTION_DATE,
DROP COLUMN REJECTION_DATE,
DROP COLUMN RECORD_LAST_UPDATED,
DROP COLUMN WHEN_MIGRATED;

ALTER TABLE FIND_TUTOR
ADD COLUMN UPDATED_BY VARCHAR(100);

UPDATE FIND_TUTOR SET
UPDATED_BY = 'SYSTEM';

ALTER TABLE BECOME_TUTOR
DROP COLUMN RECONTACTED_DATE;

ALTER TABLE FIND_TUTOR
DROP COLUMN RECONTACTED_DATE;

ALTER TABLE SUBSCRIBE_WITH_US
ADD COLUMN APPLICATION_DATE_MILLIS BIGINT,
ADD COLUMN CONTACTED_DATE_MILLIS BIGINT,
ADD COLUMN VERIFICATION_DATE_MILLIS BIGINT,
ADD COLUMN SUGGESTION_DATE_MILLIS BIGINT,
ADD COLUMN RECONTACTED_DATE_MILLIS BIGINT,
ADD COLUMN SELECTION_DATE_MILLIS BIGINT,
ADD COLUMN REJECTION_DATE_MILLIS BIGINT,
ADD COLUMN RECORD_LAST_UPDATED_MILLIS BIGINT,
ADD COLUMN WHEN_MIGRATED_MILLIS BIGINT;

UPDATE SUBSCRIBE_WITH_US SET
APPLICATION_DATE_MILLIS = UNIX_TIMESTAMP(APPLICATION_DATE) * 1000,
CONTACTED_DATE_MILLIS = UNIX_TIMESTAMP(CONTACTED_DATE) * 1000,
VERIFICATION_DATE_MILLIS = UNIX_TIMESTAMP(VERIFICATION_DATE) * 1000,
SUGGESTION_DATE_MILLIS = UNIX_TIMESTAMP(SUGGESTION_DATE) * 1000,
RECONTACTED_DATE_MILLIS = UNIX_TIMESTAMP(RECONTACTED_DATE) * 1000,
SELECTION_DATE_MILLIS = UNIX_TIMESTAMP(SELECTION_DATE) * 1000,
REJECTION_DATE_MILLIS = UNIX_TIMESTAMP(REJECTION_DATE) * 1000,
RECORD_LAST_UPDATED_MILLIS = UNIX_TIMESTAMP(RECORD_LAST_UPDATED) * 1000;

ALTER TABLE SUBSCRIBE_WITH_US
DROP COLUMN APPLICATION_DATE,
DROP COLUMN CONTACTED_DATE,
DROP COLUMN VERIFICATION_DATE,
DROP COLUMN SUGGESTION_DATE,
DROP COLUMN RECONTACTED_DATE,
DROP COLUMN SELECTION_DATE,
DROP COLUMN REJECTION_DATE,
DROP COLUMN RECORD_LAST_UPDATED;

ALTER TABLE SUBSCRIBE_WITH_US
ADD COLUMN UPDATED_BY VARCHAR(100);

UPDATE SUBSCRIBE_WITH_US SET
UPDATED_BY = 'SYSTEM';

ALTER TABLE SUBSCRIBE_WITH_US
ADD COLUMN IS_DATA_MIGRATED VARCHAR(2);

UPDATE EMPLOYEE SET USER_TYPE = 'Employee';

ALTER TABLE SUBMIT_QUERY
ADD COLUMN QUERY_REQUESTED_DATE_MILLIS BIGINT,
ADD COLUMN CONTACTED_DATE_MILLIS BIGINT,
ADD COLUMN RECORD_LAST_UPDATED_MILLIS BIGINT,
ADD COLUMN UPDATED_BY VARCHAR(100);

UPDATE SUBMIT_QUERY SET
QUERY_REQUESTED_DATE_MILLIS = UNIX_TIMESTAMP(QUERY_REQUESTED_DATE) * 1000,
CONTACTED_DATE_MILLIS = UNIX_TIMESTAMP(CONTACTED_DATE) * 1000,
RECORD_LAST_UPDATED_MILLIS = UNIX_TIMESTAMP(RECORD_LAST_UPDATED) * 1000,
UPDATED_BY = 'SYSTEM';

ALTER TABLE SUBMIT_QUERY
DROP COLUMN QUERY_REQUESTED_DATE,
DROP COLUMN CONTACTED_DATE,
DROP COLUMN RECORD_LAST_UPDATED;

CREATE TABLE COMPLAINT(
COMPLAINT_ID INT NOT NULL AUTO_INCREMENT,
NAME VARCHAR(100) NOT NULL,
COMPLAINT_FILED_DATE_MILLIS BIGINT,
COMPLAINT_STATUS VARCHAR(2) NOT NULL,
USER_ID VARCHAR(100) NOT NULL,
COMPLAINT_DETAILS VARCHAR(2000) NOT NULL,
COMPLAINT_USER VARCHAR(2) NOT NULL,
COMPLAINT_RESPONSE VARCHAR(2000),
IS_CONTACTED VARCHAR(2) NOT NULL,
WHO_CONTACTED VARCHAR(100),
CONTACTED_DATE_MILLIS BIGINT,
RESOLVED VARCHAR(2),
NOT_RESOLVED VARCHAR(2),
NOT_RESOLVED_REASON VARCHAR(2000),
WHO_NOT_RESOLVED VARCHAR(100),
RECORD_LAST_UPDATED_MILLIS BIGINT,
UPDATED_BY VARCHAR(100),
PRIMARY KEY (COMPLAINT_ID));

CREATE TABLE BANK_DETAIL(
BANK_ACCOUNT_ID INT NOT NULL AUTO_INCREMENT,
TUTOR_ID INT NOT NULL,
BANK_NAME VARCHAR(200) NOT NULL,
ACCOUNT_NUMBER VARCHAR(200) NOT NULL,
IFSC_CODE VARCHAR(100) NOT NULL,
ACCOUNT_HOLDER_NAME VARCHAR(200) NOT NULL,
IS_DEFAULT VARCHAR(2),
IS_APPROVED VARCHAR(2),
WHO_ACTED VARCHAR(100),
ACTION_DATE_MILLIS BIGINT,
REMARKS VARCHAR(2000),
PRIMARY KEY (BANK_ACCOUNT_ID));

CREATE TABLE SUBSCRIPTION_PACKAGE(
SUBSCRIPTION_PACKAGE_ID INT NOT NULL AUTO_INCREMENT,
CUSTOMER_ID INT NOT NULL,
TUTOR_ID INT NOT NULL,
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
PRIMARY KEY (SUBSCRIPTION_PACKAGE_ID));

CREATE TABLE ALERT_REMINDER(
ALERT_REMINDER_ID INT NOT NULL AUTO_INCREMENT,
INITIATED_DATE_MILLIS BIGINT NOT NULL,
ACTION_DATE_MILLIS BIGINT,
INITIATED_BY VARCHAR(100) NOT NULL,
ACTION_BY VARCHAR(100),
DUE_DATE_MILLIS BIGINT,
SUBJECT VARCHAR(2000),
RECIPIENT_USER_ID VARCHAR(100),
PRIMARY KEY (ALERT_REMINDER_ID));

CREATE TABLE TASK(
TASK_ID INT NOT NULL AUTO_INCREMENT,
INITIATED_DATE_MILLIS BIGINT NOT NULL,
ACTION_DATE_MILLIS BIGINT,
INITIATED_BY VARCHAR(100) NOT NULL,
ACTION_BY VARCHAR(100),
DUE_DATE_MILLIS BIGINT,
SUBJECT VARCHAR(2000),
RECIPIENT_USER_ID VARCHAR(100),
PRIMARY KEY (TASK_ID));

CREATE TABLE WORKFLOW(
WORKFLOW_ID INT NOT NULL AUTO_INCREMENT,
INITIATED_DATE_MILLIS BIGINT NOT NULL,
ACTION_DATE_MILLIS BIGINT,
INITIATED_BY VARCHAR(100) NOT NULL,
ACTION_BY VARCHAR(100),
DUE_DATE_MILLIS BIGINT,
SUBJECT VARCHAR(2000),
RECIPIENT_USER_ID VARCHAR(100),
PRIMARY KEY (WORKFLOW_ID));

ALTER TABLE ENQUIRIES
ADD COLUMN LAST_ACTION_DATE_MILLIS BIGINT;

UPDATE ENQUIRIES SET
LAST_ACTION_DATE_MILLIS = UNIX_TIMESTAMP(LAST_ACTION_DATE) * 1000;

ALTER TABLE ENQUIRIES
DROP COLUMN LAST_ACTION_DATE;

ALTER TABLE ENQUIRIES
ADD COLUMN ENTRY_DATE_MILLIS BIGINT;

UPDATE ENQUIRIES SET
ENTRY_DATE_MILLIS = UNIX_TIMESTAMP(SYSDATE()) * 1000;

ALTER TABLE TUTOR_MAPPER
ADD COLUMN TUTOR_CONTACTED_DATE_MILLIS BIGINT,
ADD COLUMN CLIENT_CONTACTED_DATE_MILLIS BIGINT,
ADD COLUMN ADMIN_ACTION_DATE_MILLIS BIGINT;

UPDATE TUTOR_MAPPER SET
TUTOR_CONTACTED_DATE_MILLIS = UNIX_TIMESTAMP(TUTOR_CONTACTED_DATE) * 1000,
CLIENT_CONTACTED_DATE_MILLIS = UNIX_TIMESTAMP(CLIENT_CONTACTED_DATE) * 1000,
ADMIN_ACTION_DATE_MILLIS = UNIX_TIMESTAMP(ADMIN_ACTION_DATE) * 1000;

ALTER TABLE TUTOR_MAPPER
DROP COLUMN TUTOR_CONTACTED_DATE,
DROP COLUMN CLIENT_CONTACTED_DATE,
DROP COLUMN ADMIN_ACTION_DATE;

ALTER TABLE TUTOR_MAPPER
DROP COLUMN DEMO_DATE_AND_TIME;

ALTER TABLE TUTOR_MAPPER
ADD COLUMN ENTRY_DATE_MILLIS BIGINT;

UPDATE TUTOR_MAPPER SET
ENTRY_DATE_MILLIS = UNIX_TIMESTAMP(SYSDATE()) * 1000;

ALTER TABLE DEMO_TRACKER
ADD COLUMN DEMO_DATE_AND_TIME_MILLIS BIGINT,
ADD COLUMN ADMIN_ACTION_DATE_MILLIS BIGINT,
ADD COLUMN ENTRY_DATE_MILLIS BIGINT;

UPDATE DEMO_TRACKER SET
DEMO_DATE_AND_TIME_MILLIS = UNIX_TIMESTAMP(DEMO_DATE_AND_TIME) * 1000,
ADMIN_ACTION_DATE_MILLIS = UNIX_TIMESTAMP(ADMIN_ACTION_DATE) * 1000,
ENTRY_DATE_MILLIS = UNIX_TIMESTAMP(SYSDATE()) * 1000;

ALTER TABLE DEMO_TRACKER
DROP COLUMN DEMO_DATE_AND_TIME,
DROP COLUMN ADMIN_ACTION_DATE;

ALTER TABLE REGISTERED_TUTOR
ADD COLUMN IS_BLACKLISTED VARCHAR(2),
ADD COLUMN BLACKLISTED_REMARKS VARCHAR(2000),
ADD COLUMN BLACKLISTED_DATE_MILLIS BIGINT,
ADD COLUMN WHO_BLACKLISTED VARCHAR(100);

ALTER TABLE SUBSCRIBED_CUSTOMER
ADD COLUMN IS_BLACKLISTED VARCHAR(2),
ADD COLUMN BLACKLISTED_REMARKS VARCHAR(2000),
ADD COLUMN BLACKLISTED_DATE_MILLIS BIGINT,
ADD COLUMN WHO_BLACKLISTED VARCHAR(100);

ALTER TABLE BECOME_TUTOR
ADD COLUMN IS_BLACKLISTED VARCHAR(2),
ADD COLUMN BLACKLISTED_REMARKS VARCHAR(2000),
ADD COLUMN BLACKLISTED_DATE_MILLIS BIGINT,
ADD COLUMN WHO_BLACKLISTED VARCHAR(100);

ALTER TABLE FIND_TUTOR
ADD COLUMN IS_BLACKLISTED VARCHAR(2),
ADD COLUMN BLACKLISTED_REMARKS VARCHAR(2000),
ADD COLUMN BLACKLISTED_DATE_MILLIS BIGINT,
ADD COLUMN WHO_BLACKLISTED VARCHAR(100);

ALTER TABLE SUBSCRIBE_WITH_US
ADD COLUMN IS_BLACKLISTED VARCHAR(2),
ADD COLUMN BLACKLISTED_REMARKS VARCHAR(2000),
ADD COLUMN BLACKLISTED_DATE_MILLIS BIGINT,
ADD COLUMN WHO_BLACKLISTED VARCHAR(100);

ALTER TABLE REGISTERED_TUTOR
ADD COLUMN UN_BLACKLISTED_REMARKS VARCHAR(2000),
ADD COLUMN UN_BLACKLISTED_DATE_MILLIS BIGINT,
ADD COLUMN WHO_UN_BLACKLISTED VARCHAR(100);

ALTER TABLE SUBSCRIBED_CUSTOMER
ADD COLUMN UN_BLACKLISTED_REMARKS VARCHAR(2000),
ADD COLUMN UN_BLACKLISTED_DATE_MILLIS BIGINT,
ADD COLUMN WHO_UN_BLACKLISTED VARCHAR(100);

ALTER TABLE BECOME_TUTOR
ADD COLUMN UN_BLACKLISTED_REMARKS VARCHAR(2000),
ADD COLUMN UN_BLACKLISTED_DATE_MILLIS BIGINT,
ADD COLUMN WHO_UN_BLACKLISTED VARCHAR(100);

ALTER TABLE FIND_TUTOR
ADD COLUMN UN_BLACKLISTED_REMARKS VARCHAR(2000),
ADD COLUMN UN_BLACKLISTED_DATE_MILLIS BIGINT,
ADD COLUMN WHO_UN_BLACKLISTED VARCHAR(100);

ALTER TABLE SUBSCRIBE_WITH_US
ADD COLUMN UN_BLACKLISTED_REMARKS VARCHAR(2000),
ADD COLUMN UN_BLACKLISTED_DATE_MILLIS BIGINT,
ADD COLUMN WHO_UN_BLACKLISTED VARCHAR(100);

ALTER TABLE TUTOR_DOCUMENTS
ADD COLUMN DOCUMENT_TYPE VARCHAR(2);

ALTER TABLE BECOME_TUTOR
ADD COLUMN ADDRESS_DETAILS VARCHAR(2000);

ALTER TABLE REGISTERED_TUTOR
ADD COLUMN ADDRESS_DETAILS VARCHAR(2000);

CREATE TABLE REGISTERED_TUTOR_OTHER_EMAILS(
REGISTERED_TUTOR_OTHER_EMAILS_ID INT NOT NULL AUTO_INCREMENT,
TUTOR_ID INT NOT NULL,
EMAIL_ID VARCHAR(100),
PRIMARY KEY (REGISTERED_TUTOR_OTHER_EMAILS_ID));

CREATE TABLE REGISTERED_TUTOR_OTHER_CONTACT_NUMBERS(
REGISTERED_TUTOR_OTHER_CONTACT_NUMBERS_ID INT NOT NULL AUTO_INCREMENT,
TUTOR_ID INT NOT NULL,
CONTACT_NUMBER VARCHAR(10),
PRIMARY KEY (REGISTERED_TUTOR_OTHER_CONTACT_NUMBERS_ID));

CREATE TABLE SUBSCRIBED_CUSTOMER_OTHER_EMAILS(
SUBSCRIBED_CUSTOMER_OTHER_EMAILS_ID INT NOT NULL AUTO_INCREMENT,
CUSTOMER_ID INT NOT NULL,
EMAIL_ID VARCHAR(100),
PRIMARY KEY (SUBSCRIBED_CUSTOMER_OTHER_EMAILS_ID));

CREATE TABLE SUBSCRIBED_CUSTOMER_OTHER_CONTACT_NUMBERS(
SUBSCRIBED_CUSTOMER_OTHER_CONTACT_NUMBERS_ID INT NOT NULL AUTO_INCREMENT,
CUSTOMER_ID INT NOT NULL,
CONTACT_NUMBER VARCHAR(10),
PRIMARY KEY (SUBSCRIBED_CUSTOMER_OTHER_CONTACT_NUMBERS_ID));

ALTER TABLE MAIL_QUEUE
ADD COLUMN ENTRY_DATE_MILLIS BIGINT,
ADD COLUMN SEND_DATE_MILLIS BIGINT,
ADD COLUMN ERROR_DATE_MILLIS BIGINT;

UPDATE MAIL_QUEUE SET
ENTRY_DATE_MILLIS = UNIX_TIMESTAMP(ENTRY_DATE) * 1000,
SEND_DATE_MILLIS = UNIX_TIMESTAMP(SEND_DATE) * 1000,
ERROR_DATE_MILLIS = UNIX_TIMESTAMP(ERROR_DATE) * 1000;

ALTER TABLE APP_ERROR_REPORT
ADD COLUMN OCCURED_AT_MILLIS BIGINT;

UPDATE APP_ERROR_REPORT SET
OCCURED_AT_MILLIS = UNIX_TIMESTAMP(OCCURED_AT) * 1000;

ALTER TABLE LOGON_TRACKER
ADD COLUMN LOGIN_TIME_MILLIS BIGINT;

UPDATE LOGON_TRACKER SET
LOGIN_TIME_MILLIS = UNIX_TIMESTAMP(LOGIN_TIME) * 1000;

ALTER TABLE PASSWORD_CHANGE_TRACKER
ADD COLUMN CHANGE_TIME_MILLIS BIGINT;

UPDATE PASSWORD_CHANGE_TRACKER SET
CHANGE_TIME_MILLIS = UNIX_TIMESTAMP(CHANGE_TIME) * 1000;

ALTER TABLE DEMO_TRACKER
ADD COLUMN RE_SCHEDULE_COUNT INT;

UPDATE DEMO_TRACKER SET
RE_SCHEDULE_COUNT = 0;

CREATE TABLE PUBLIC_APPLICATION_STATUS_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

ALTER TABLE SUBSCRIBED_CUSTOMER
ADD COLUMN FIND_TUTOR_ID INT AFTER ENQUIRY_ID;

UPDATE SUBSCRIBED_CUSTOMER SET
FIND_TUTOR_ID = ENQUIRY_ID;

ALTER TABLE SUBSCRIBED_CUSTOMER
DROP COLUMN ENQUIRY_ID;

ALTER TABLE SUBSCRIBED_CUSTOMER 
MODIFY COLUMN STUDENT_GRADE VARCHAR(1000),
MODIFY COLUMN SUBJECTS VARCHAR(1000),
MODIFY COLUMN LOCATION VARCHAR(1000);

CREATE TABLE DOCUMENT_TYPE_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

INSERT INTO DOCUMENT_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('01', 'PAN Card', NULL, 1, 1, NULL, NULL);
INSERT INTO DOCUMENT_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('02', 'Profile Photo', NULL, 1, 2, NULL, NULL);
INSERT INTO DOCUMENT_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES('03', 'Aadhaar Card', NULL, 1, 3, NULL, NULL);

UPDATE TUTOR_DOCUMENTS SET
DOCUMENT_TYPE = '01'
WHERE SUBSTRING(FILENAME, 1, POSITION('.' IN FILENAME) - 1) = 'PAN_CARD';

UPDATE TUTOR_DOCUMENTS SET
DOCUMENT_TYPE = '02'
WHERE SUBSTRING(FILENAME, 1, POSITION('.' IN FILENAME) - 1) = 'PROFILE_PHOTO';

UPDATE TUTOR_DOCUMENTS SET
DOCUMENT_TYPE = '03'
WHERE SUBSTRING(FILENAME, 1, POSITION('.' IN FILENAME) - 1) = 'AADHAAR_CARD';

RENAME TABLE TUTOR_DOCUMENTS TO TUTOR_DOCUMENT;

CREATE TABLE AWS_S3_DELETE_REPORT(
S3_DELETE_ID INT NOT NULL AUTO_INCREMENT,
OCCURED_AT_MILLIS BIGINT NOT NULL,
FS_KEY_TO_BE_DELETED VARCHAR(2000) NOT NULL,
FS_KEY_RECYCLED VARCHAR(2000) NOT NULL,
IS_FOLDER VARCHAR(2) NOT NULL,
USER_ID VARCHAR(100) NOT NULL,
USER_TYPE VARCHAR(100) NOT NULL,
PRIMARY KEY (S3_DELETE_ID));
-- Only to be executed in PROD