USE SEEK_MENTORE;

ALTER table FIND_TUTOR add column IS_DATA_MIGRATED varchar(2);
ALTER table FIND_TUTOR add column WHEN_MIGRATED timestamp;
-- Executed till here In Prod Do not write any more create new file