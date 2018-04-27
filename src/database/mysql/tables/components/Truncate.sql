---------------------------------------------------------------------------------
--                                     SELECT DATABASE
---------------------------------------------------------------------------------
USE SEEK_MENTORE;

---------------------------------------------------------------------------------
-- Public Pages Tables
---------------------------------------------------------------------------------
DELETE FROM BECOME_TUTOR;
DELETE FROM FIND_TUTOR;
DELETE FROM SUBSCRIBE_WITH_US;
DELETE FROM SUBMIT_QUERY;

DELETE FROM SUBSCRIBED_CUSTOMER;
DELETE FROM REGISTERED_TUTOR;

DELETE FROM GENDER_LOOKUP;
DELETE FROM QUALIFICATION_LOOKUP;
DELETE FROM PROFESSION_LOOKUP;
DELETE FROM TRANSPORT_MODE_LOOKUP;
DELETE FROM LOCATIONS_LOOKUP;
DELETE FROM PREFERRED_TIME_LOOKUP;
DELETE FROM STUDENT_GRADE_LOOKUP;
DELETE FROM SUBJECTS_LOOKUP;

DELETE FROM APP_ERROR_REPORT;

DELETE FROM MAIL_QUEUE;
DELETE FROM MAIL_ATTACHMENTS;

DELETE FROM EMPLOYEE;

