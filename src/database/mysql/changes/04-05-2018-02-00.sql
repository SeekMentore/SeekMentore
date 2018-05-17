USE SEEK_MENTORE;

CREATE TABLE TUTOR_DOCUMENTS(
DOCUMENT_ID INT NOT NULL AUTO_INCREMENT,
TUTOR_ID INT NOT NULL,
FS_KEY VARCHAR(1000) NOT NULL,
FILENAME VARCHAR(100) NOT NULL,
PRIMARY KEY (DOCUMENT_ID));

ALTER TABLE BECOME_TUTOR
ADD IS_DATA_MIGRATED VARCHAR(2);

ALTER TABLE BECOME_TUTOR
ADD WHEN_MIGRATED TIMESTAMP;

ALTER TABLE BECOME_TUTOR 
ADD REFERENCE VARCHAR(10);

ALTER TABLE BECOME_TUTOR 
ADD PREFERRED_TEACHING_TYPE VARCHAR(1000);
	
ALTER TABLE FIND_TUTOR 
ADD LOCATION VARCHAR(10);

ALTER TABLE FIND_TUTOR 
ADD REFERENCE VARCHAR(10);

ALTER TABLE FIND_TUTOR 
ADD ADDRESS_DETAILS VARCHAR(2000);

ALTER TABLE SUBSCRIBE_WITH_US 
ADD LOCATION VARCHAR(10);

ALTER TABLE SUBSCRIBE_WITH_US 
ADD REFERENCE VARCHAR(100);

ALTER TABLE SUBSCRIBE_WITH_US 
ADD ADDRESS_DETAILS VARCHAR(2000);

ALTER TABLE REGISTERED_TUTOR
ADD COLUMN TENTATIVE_TUTOR_ID INT NOT NULL,
ADD COLUMN DATE_OF_BIRTH DATE NOT NULL,
ADD COLUMN GENDER VARCHAR(10) NOT NULL,
ADD COLUMN QUALIFICATION VARCHAR(10) NOT NULL,
ADD COLUMN PRIMARY_PROFESSION VARCHAR(10) NOT NULL,
ADD COLUMN TRANSPORT_MODE VARCHAR(10) NOT NULL,
ADD COLUMN TEACHING_EXP INT,
ADD COLUMN INTERESTED_STUDENT_GRADES VARCHAR(1000) NOT NULL,
ADD COLUMN INTERESTED_SUBJECTS VARCHAR(1000) NOT NULL,
ADD COLUMN COMFORTABLE_LOCATIONS VARCHAR(1000) NOT NULL,
ADD COLUMN ADDITIONAL_DETAILS VARCHAR(2000),
ADD COLUMN ENCYPTED_PASSWORD VARCHAR(1000) NOT NULL,
ADD COLUMN RECORD_LAST_UPDATED  TIMESTAMP NOT NULL,
ADD COLUMN UPDATED_BY VARCHAR(100) NOT NULL,
ADD COLUMN USER_ID VARCHAR(100) NOT NULL UNIQUE;

CREATE TABLE PREFERRED_TEACHING_TYPE_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

CREATE TABLE REFERENCE_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));

INSERT INTO REFERENCE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES(01, 'Advertisement',NULL, 1, 1, NULL, NULL);
INSERT INTO REFERENCE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES(02, 'Marketing Representative', NULL, 1, 2, NULL, NULL);
INSERT INTO REFERENCE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES(03, 'Friend', NULL, 1, 4, NULL, NULL);
INSERT INTO REFERENCE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES(04, 'Social Websites', NULL, 1, 3, NULL, NULL);
INSERT INTO REFERENCE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES(05, 'Other', NULL, 1, 5, NULL, NULL);

INSERT INTO PREFERRED_TEACHING_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES(01, 'Private Tutoring', NULL, 1, 1, NULL, NULL);
INSERT INTO PREFERRED_TEACHING_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES(02, 'Combined Tutoring', NULL, 1, 2, NULL, NULL);
INSERT INTO PREFERRED_TEACHING_TYPE_LOOKUP(VALUE, LABEL, CATEGORY, ORDER_OF_CATEGORY, ORDER_IN_CATEGORY, DESCRIPTION, HIDDEN) VALUES(03, 'Tutoring at your place', NULL, 1, 3, NULL, NULL);

ALTER TABLE REGISTERED_TUTOR
ADD PREFERRED_TEACHING_TYPE VARCHAR(1000);
-- Executed till here In Prod Do not write any more create new file











