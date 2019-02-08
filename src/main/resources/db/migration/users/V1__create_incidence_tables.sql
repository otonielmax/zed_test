-- Update this file with database structure and basic data (metadada, catalogs, etc)
-- Here you'll find an example
-- IMPORTANT: FileName must start with "V" + versionNumber + "__" and have .sql extension

CREATE TABLE cpl_incidence.incidence (
  id varchar(36) NOT NULL PRIMARY KEY,
  title varchar(45) DEFAULT NULL,
  description varchar(120) DEFAULT NULL,
  placa varchar(10) DEFAULT NULL,
  date_device TIMESTAMP NULL,
  date_user TIMESTAMP NULL,
  direction_gps varchar(100) DEFAULT NULL,
  direction_gps_lat double(15,12) DEFAULT NULL,
  direction_gps_lng double(15,12) DEFAULT NULL,
  direction_user varchar(100) DEFAULT NULL,
  direction_user_lat double(15,12) DEFAULT NULL,
  direction_user_lng double(15,12) DEFAULT NULL,
  status varchar(3) DEFAULT NULL,
  type varchar(3) DEFAULT NULL,
  id_user varchar(36) NOT NULL,
  create_date TIMESTAMP DEFAULT NOW(),
  update_date TIMESTAMP DEFAULT NOW()
);

CREATE INDEX incidence_title ON cpl_incidence.incidence(title);
CREATE INDEX incidence_id ON cpl_incidence.incidence(id);

CREATE TABLE cpl_incidence.incidence_image (
  id varchar(36) NOT NULL PRIMARY KEY,
  url_download varchar(500) DEFAULT NULL,
  url_display varchar(500) DEFAULT NULL,
  id_incidence varchar(36) DEFAULT NULL,
  create_date TIMESTAMP DEFAULT NOW(),
  update_date TIMESTAMP DEFAULT NOW()
);

CREATE INDEX incidence_image_id ON cpl_incidence.incidence_image(id);
