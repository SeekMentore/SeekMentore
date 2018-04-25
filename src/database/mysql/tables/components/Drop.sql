---------------------------------------------------------------------------------
--                                     SELECT DATABASE
---------------------------------------------------------------------------------
USE SEEK_MENTORE;

---------------------------------------------------------------------------------
--                                     TABLES
---------------------------------------------------------------------------------
-- Public Pages Tables
---------------------------------------------------------------------------------
DROP TABLE IF EXISTS BECOME_TUTOR;
DROP TABLE IF EXISTS FIND_TUTOR;
DROP TABLE IF EXISTS SUBSCRIBE_WITH_US;
DROP TABLE IF EXISTS SUBMIT_QUERY;

DROP TABLE IF EXISTS SUBSCRIBED_CUSTOMER;
DROP TABLE IF EXISTS REGISTERED_TUTOR;

DROP TABLE IF EXISTS GENDER_LOOKUP;
DROP TABLE IF EXISTS QUALIFICATION_LOOKUP;
DROP TABLE IF EXISTS PROFESSION_LOOKUP;
DROP TABLE IF EXISTS TRANSPORT_MODE_LOOKUP;
DROP TABLE IF EXISTS LOCATIONS_LOOKUP;
DROP TABLE IF EXISTS PREFERRED_TIME_LOOKUP;
DROP TABLE IF EXISTS STUDENT_GRADE_LOOKUP;
DROP TABLE IF EXISTS SUBJECTS_LOOKUP;

DROP TABLE IF EXISTS APP_ERROR_REPORT;

DROP TABLE IF EXISTS MAIL_QUEUE;
DROP TABLE IF EXISTS MAIL_ATTACHMENTS;

DROP TABLE IF EXISTS EMPLOYEE;
---------------------------------------------------------------------------------
--                                     DATABASE
---------------------------------------------------------------------------------
DROP DATABASE SEEK_MENTORE;

