---------------------------------------------------------------------------------
--                                     SELECT DATABASE
---------------------------------------------------------------------------------
USE SEEK_MENTORE;

---------------------------------------------------------------------------------
-- Audit Tables
---------------------------------------------------------------------------------
DELETE FROM AUDIT_TRIGGER_NAME_TABLE;
DELETE FROM AUDIT_TABLE;
DELETE FROM AUDIT_METADATA;
DELETE FROM AUDIT_KEY_LOOKUP;