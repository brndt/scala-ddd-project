CREATE DATABASE IF NOT EXISTS `dev`;

USE `dev`;

CREATE TABLE IF NOT EXISTS cat (
  id         VARCHAR(255)        NOT NULL,
  name       VARCHAR(255)        NOT NULL,
  alt_names       VARCHAR(255)        NOT NULL,
  date_of_birth       DATE        NOT NULL,
  `character`       VARCHAR(255)        NOT NULL,
  weight       FLOAT        NOT NULL,
  energy_level       VARCHAR(255)        NOT NULL,
  UNIQUE KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE=utf8mb4_unicode_ci;
