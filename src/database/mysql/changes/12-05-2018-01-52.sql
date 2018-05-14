USE SEEK_MENTORE;

ALTER TABLE SUBSCRIBED_CUSTOMER
ADD COLUMN ENQUIRY_ID INT(11) ,
ADD COLUMN STUDENT_GRADE VARCHAR(10) NOT NULL,
ADD COLUMN SUBJECTS VARCHAR(10) NOT NULL,
ADD COLUMN LOCATION VARCHAR(10) NOT NULL,
ADD COLUMN ADDRESS_DETAILS VARCHAR(2000) NOT NULL,
ADD COLUMN ENCRYPTED_PASSWORD VARCHAR(1000) NOT NULL,
ADD COLUMN RECORD_LAST_UPDATED  TIMESTAMP NOT NULL,
ADD COLUMN UPDATED_BY VARCHAR(100) NOT NULL,
ADD COLUMN USER_ID VARCHAR(100) NOT NULL UNIQUE,
ADD COLUMN ADDITIONAL_DETAILS VARCHAR(2000);

ALTER TABLE EMPLOYEE
CHANGE ENCYPTED_PASSWORD ENCRYPTED_PASSWORD VARCHAR(1000) NOT NULL;

ALTER TABLE REGISTERED_TUTOR
CHANGE ENCYPTED_PASSWORD ENCRYPTED_PASSWORD VARCHAR(1000) NOT NULL;

ALTER TABLE PASSWORD_CHANGE_TRACKER
CHANGE ENCYPTED_PASSWORD_OLD ENCRYPTED_PASSWORD_OLD VARCHAR(1000) NOT NULL,
CHANGE ENCYPTED_PASSWORD_NEW ENCRYPTED_PASSWORD_NEW VARCHAR(1000) NOT NULL;
-- Not Executed till here In Prod Do not write any more create new file