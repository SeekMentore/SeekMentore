USE SEEK_MENTORE;

CREATE TABLE EMAIL_TEMPLATE_LOOKUP(
VALUE VARCHAR(100) NOT NULL,
LABEL VARCHAR(100) NOT NULL,
CATEGORY VARCHAR(100),
ORDER_OF_CATEGORY INT NOT NULL,
ORDER_IN_CATEGORY INT NOT NULL,
DESCRIPTION VARCHAR(500),
HIDDEN VARCHAR(2),
PRIMARY KEY (VALUE));