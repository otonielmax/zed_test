-- Update this file with database structure and basic data (metadada, catalogs, etc)
-- Here you'll find an example
-- IMPORTANT: FileName must start with "V" + versionNumber + "__" and have .sql extension

CREATE TABLE cpl_incidence.incidence (
  id varchar(36) NOT NULL PRIMARY KEY,
  title varchar(45) DEFAULT NULL,
  description varchar(120) DEFAULT NULL,
  placa varchar(10) DEFAULT NULL,
  date_device TIMESTAMP,
  date_user TIMESTAMP,
  direction_gps varchar(100) DEFAULT NULL,
  direction_user varchar(100) DEFAULT NULL,
  status varchar(3) DEFAULT NULL,
  create_date TIMESTAMP DEFAULT NOW(),
  update_date TIMESTAMP DEFAULT NOW()
);

CREATE INDEX incidence_title ON cpl_incidence.incidence(title);



