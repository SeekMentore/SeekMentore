---------------------------------------------------------------------------------
--                                     SELECT DATABASE
---------------------------------------------------------------------------------
USE INDIA_PROVIDENT_FUND;

---------------------------------------------------------------------------------
--                                     TABLES
---------------------------------------------------------------------------------
-- Form 2 Tables
---------------------------------------------------------------------------------
DROP TABLE IF EXISTS FORM2_EMP_PF_NOM_DTLS;
DROP TABLE IF EXISTS FORM2_WDW_CHLDRN_NOM_DTLS;
DROP TABLE IF EXISTS FORM2_ONLY_WDW_NOM_DTLS;
DROP TABLE IF EXISTS FORM2;

---------------------------------------------------------------------------------
-- Form 11 Tables
---------------------------------------------------------------------------------
DROP TABLE IF EXISTS FORM11_KYC_DTLS;
DROP TABLE IF EXISTS FORM11;

---------------------------------------------------------------------------------
-- Form F Tables
---------------------------------------------------------------------------------
DROP TABLE IF EXISTS FORMF_EMP_GRTY_NOM_DTLS;
DROP TABLE IF EXISTS FORMF;

---------------------------------------------------------------------------------
-- Look Up tables
---------------------------------------------------------------------------------
DROP TABLE IF EXISTS COUNTRY;
DROP TABLE IF EXISTS STATE;
DROP TABLE IF EXISTS MARITAL_STATUS;
DROP TABLE IF EXISTS RELATIONSHIP;
DROP TABLE IF EXISTS CATEGORY;
DROP TABLE IF EXISTS GENDER;

---------------------------------------------------------------------------------
--                                     DATABASE
---------------------------------------------------------------------------------
DROP DATABASE INDIA_PROVIDENT_FUND;

