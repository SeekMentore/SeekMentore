USE SEEK_MENTORE;

CREATE TABLE TUTOR_RESTRICTED_COLUMN_CHANGES(
CHANGE_ID INT NOT NULL AUTO_INCREMENT,
TUTOR_ID INT NOT NULL,
COLUMN_CHANGED VARCHAR(100) NOT NULL,
COLUMN_TYPE VARCHAR(100) NOT NULL,
OLD_VALUE_VARCHAR VARCHAR(1000),
NEW_VALUE_VARCHAR VARCHAR(1000),
OLD_VALUE_DATE VARCHAR(1000),
NEW_VALUE_DATE VARCHAR(1000),
OLD_VALUE_TIMESTAMP TIMESTAMP,
NEW_VALUE_TIMESTAMP TIMESTAMP,
OLD_VALUE_INT INT,
NEW_VALUE_INT INT,
OLD_VALUE_TEXT INT,
NEW_VALUE_TEXT INT,
OLD_VALUE_BLOB LONGBLOB,
NEW_VALUE_BLOB LONGBLOB,
WHO_CHANGED VARCHAR(100) NOT NULL,
CHANGED_DATE TIMESTAMP NOT NULL,
CHANGE_REASON VARCHAR(2000),
PRIMARY KEY (CHANGE_ID));
-- Executed till here In Prod Do not write any more create new file

