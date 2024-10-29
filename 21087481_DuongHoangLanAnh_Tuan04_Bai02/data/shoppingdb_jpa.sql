-- Tạo cơ sở dữ liệu
CREATE DATABASE IF NOT EXISTS shoppingdb_jpa

-- Sử dụng cơ sở dữ liệu
USE shoppingdb_jpa;

-- Tạo bảng products
CREATE TABLE IF NOT EXISTS products (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  price DOUBLE DEFAULT NULL,
  image VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- Tạo bảng carts
CREATE TABLE IF NOT EXISTS carts (
  id INT(11) NOT NULL AUTO_INCREMENT,
  productId INT(11) NOT NULL,
  quantity INT(11) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id) REFERENCES products(id)
);
