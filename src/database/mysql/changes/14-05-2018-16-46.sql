USE SEEK_MENTORE;

CREATE TABLE ENQUIRIES(
ENQUIRY_ID INT NOT NULL AUTO_INCREMENT,
CUSTOMER_ID INT NOT NULL,
SUBJECT VARCHAR(10) NOT NULL,
GRADE VARCHAR(10) NOT NULL,
QUOTED_CLIENT_RATE INT,
NEGOTIATED_RATE_WITH_CLIENT INT,
CLIENT_NEGOTIATION_REMARKS VARCHAR(2000),
IS_MAPPED VARCHAR(2),
LAST_ACTION_DATE TIMESTAMP,
MATCH_STATUS VARCHAR(20) NOT NULL,
TUTOR_ID INT,
ADMIN_REMARKS VARCHAR(2000),
LOCATION_DETAILS VARCHAR(1000),
ADDRESS_DETAILS VARCHAR(2000),
ADDITIONAL_DETAILS VARCHAR(2000),
WHO_ACTED VARCHAR(100),
PRIMARY KEY (ENQUIRY_ID));

ALTER TABLE ENQUIRIES
ADD COLUMN PREFERRED_TEACHING_TYPE VARCHAR(1000);

CREATE TABLE TUTOR_MAPPER(
TUTOR_MAPPER_ID INT NOT NULL AUTO_INCREMENT,
ENQUIRY_ID INT NOT NULL,
TUTOR_ID INT NOT NULL,
QUOTED_TUTOR_RATE INT,
NEGOTIATED_RATE_WITH_TUTOR INT,
TUTOR_NEGOTIATION_REMARKS VARCHAR(2000),
IS_TUTOR_CONTACTED VARCHAR(2),
TUTOR_CONTACTED_DATE TIMESTAMP,
IS_TUTOR_AGREED VARCHAR(2),
IS_TUTOR_REJECTION_VALID VARCHAR(2),
ADMIN_TUTOR_REJECTION_VALIDITY_RESPONSE VARCHAR(2000),
TUTOR_RESPONSE VARCHAR(2000),
ADMIN_REMARKS_FOR_TUTOR VARCHAR(2000),
IS_CLIENT_CONTACTED VARCHAR(2),
CLIENT_CONTACTED_DATE TIMESTAMP,
IS_CLIENT_AGREED VARCHAR(2),
CLIENT_RESPONSE VARCHAR(2000),
IS_CLIENT_REJECTION_VALID VARCHAR(2),
ADMIN_CLIENT_REJECTION_VALIDITY_RESPONSE VARCHAR(2000),
ADMIN_REMARKS_FOR_CLIENT VARCHAR(2000),
ADMIN_ACTION_DATE TIMESTAMP,
ADMIN_ACTION_REMARKS VARCHAR(2000),
WHO_ACTED VARCHAR(100),
IS_DEMO_SCHEDULED VARCHAR(2),
PRIMARY KEY (TUTOR_MAPPER_ID));

ALTER TABLE TUTOR_MAPPER
ADD COLUMN MAPPING_STATUS VARCHAR(20) NOT NULL;

ALTER TABLE TUTOR_MAPPER
ADD COLUMN DEMO_DATE_AND_TIME TIMESTAMP;

CREATE TABLE DEMO_TRACKER(
DEMO_TRACKER_ID INT NOT NULL AUTO_INCREMENT,
TUTOR_MAPPER_ID INT NOT NULL,
DEMO_DATE_AND_TIME TIMESTAMP NOT NULL,
DEMO_OCCURRED VARCHAR(2),
DEMO_STATUS VARCHAR(20) NOT NULL,
CLIENT_REMARKS VARCHAR(2000),
TUTOR_REMARKS VARCHAR(2000),
CLIENT_SATISFIED_FROM_TUTOR VARCHAR(2),
TUTOR_SATISFIED_WITH_CLIENT VARCHAR(2),
ADMIN_SATISFIED_FROM_TUTOR VARCHAR(2),
ADMIN_SATISFIED_WITH_CLIENT VARCHAR(2),
WHO_ACTED VARCHAR(100),
IS_DEMO_SUCCESS VARCHAR(2),
NEED_PRICE_NEGOTIATION_WITH_CLIENT VARCHAR(2),
CLIENT_NEGOTIATION_REMARKS VARCHAR(2000),
NEED_PRICE_NEGOTIATION_WITH_TUTOR VARCHAR(2),
TUTOR_NEGOTIATION_REMARKS VARCHAR(2000),
ADMIN_REMARKS VARCHAR(2000),
PRIMARY KEY (DEMO_TRACKER_ID));

ALTER TABLE DEMO_TRACKER
ADD COLUMN NEGOTIATED_OVERRIDE_RATE_WITH_CLIENT INT,
ADD COLUMN NEGOTIATED_OVERRIDE_RATE_WITH_TUTOR INT;

ALTER TABLE DEMO_TRACKER
ADD COLUMN ADMIN_ACTION_DATE TIMESTAMP;

ALTER TABLE DEMO_TRACKER
ADD COLUMN ADMIN_FINALIZING_REMARKS VARCHAR(2000);

ALTER TABLE DEMO_TRACKER
ADD COLUMN RESCHEDULING_REMARKS VARCHAR(2000);


