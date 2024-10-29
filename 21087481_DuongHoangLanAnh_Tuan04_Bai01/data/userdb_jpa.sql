-- Tạo cơ sở dữ liệu
CREATE DATABASE IF NOT EXISTS userdb_jpa 

-- Sử dụng cơ sở dữ liệu
USE userdb_jpa;

-- Tạo bảng users
CREATE TABLE IF NOT EXISTS users (
  id INT(11) NOT NULL AUTO_INCREMENT,
  fullName VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  job VARCHAR(50) NOT NULL,
  birthDate VARCHAR(20) NOT NULL,
  gender VARCHAR(10) NOT NULL,
  city VARCHAR(50) NOT NULL,
  country VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);
