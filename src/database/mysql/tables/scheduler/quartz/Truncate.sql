---------------------------------------------------------------------------------
--                                     SELECT DATABASE
---------------------------------------------------------------------------------
USE SEEK_MENTORE;

---------------------------------------------------------------------------------
-- Tables
---------------------------------------------------------------------------------
DELETE FROM QRTZ_FIRED_TRIGGERS;
DELETE FROM QRTZ_PAUSED_TRIGGER_GRPS;
DELETE FROM QRTZ_SCHEDULER_STATE;
DELETE FROM QRTZ_LOCKS;
DELETE FROM QRTZ_SIMPLE_TRIGGERS;
DELETE FROM QRTZ_SIMPROP_TRIGGERS;
DELETE FROM QRTZ_CRON_TRIGGERS;
DELETE FROM QRTZ_BLOB_TRIGGERS;
DELETE FROM QRTZ_TRIGGERS;
DELETE FROM QRTZ_JOB_DETAILS;
DELETE FROM QRTZ_CALENDARS;