-- Sử dụng MariaDB để kết nối cơ sở dữ liệu


-- Tạo cơ sở dữ liệu
CREATE DATABASE IF NOT EXISTS quanlydanhmuc;

-- Sử dụng cơ sở dữ liệu
USE quanlydanhmuc;

-- Tạo bảng DanhMuc
CREATE TABLE IF NOT EXISTS DanhMuc (
    maDanhMuc INT(11) NOT NULL AUTO_INCREMENT,
    tenDanhMuc VARCHAR(255) NOT NULL,
    nguoiQuanLy VARCHAR(255),
    ghiChu TEXT DEFAULT NULL,
    PRIMARY KEY (maDanhMuc)
);

CREATE TABLE IF NOT EXISTS TinTuc (
    maTinTuc INT(11) NOT NULL AUTO_INCREMENT,
    tieuDe VARCHAR(255) NOT NULL,
    noiDung VARCHAR(255) NOT NULL,
    lienKet VARCHAR(255) NOT NULL,
    maDanhMuc INT(11) NOT NULL,
    PRIMARY KEY (maTinTuc),
    FOREIGN KEY (maDanhMuc) REFERENCES DanhMuc(maDanhMuc)
);


-- Import data
-- Insert data vào table danhmuc
USE quanlydanhmuc;
INSERT INTO `danhmuc` (`maDanhMuc`, `tenDanhMuc`, `nguoiQuanLy`, `ghiChu`) VALUES
	(1, 'Thời sự', 'Nguyễn Văn A', NULL),
	(2, 'Góc nhìn', 'Trần Văn B', NULL),
	(3, 'Thế giới', 'Đinh Văn C', NULL),
	(4, 'Kinh doanh', 'Ngô Văn D', NULL),
	(5, 'Bất động sản', 'Đỗ Văn E', NULL),
	(6, 'Khoa học', 'Trương Ngọc G', NULL),
	(7, 'Giải trí', 'Huỳnh Ngọc H', NULL),
	(8, 'Pháp luật', 'Bùi Mai K', NULL),
	(9, 'Giáo dục', 'Kim Hoàng N', NULL),
	(10, 'Sức khỏe', 'Vũ Ngọc P', NULL);

-- Insert data vào table tintuc
USE quanlydanhmuc;
INSERT INTO `tintuc` (`maTinTuc`, `tieuDe`, `noiDung`, `lienKet`, `maDanhMuc`) VALUES
    (1, 'Tin tức 1', 'Nội dung tin tức 1', 'http://example.com/tintuc1', 1),
    (2, 'Tin tức 2', 'Nội dung tin tức 2', 'http://example.com/tintuc2', 2),
    (3, 'Tin tức 3', 'Nội dung tin tức 3', 'http://example.com/tintuc3', 3),
    (4, 'Tin tức 4', 'Nội dung tin tức 4', 'http://example.com/tintuc4', 4),
    (5, 'Tin tức 5', 'Nội dung tin tức 5', 'http://example.com/tintuc5', 5),
    (6, 'Tin tức 6', 'Nội dung tin tức 6', 'http://example.com/tintuc6', 6),
    (7, 'Tin tức 7', 'Nội dung tin tức 7', 'http://example.com/tintuc7', 7),
    (8, 'Tin tức 8', 'Nội dung tin tức 8', 'http://example.com/tintuc8', 8),
    (9, 'Tin tức 9', 'Nội dung tin tức 9', 'http://example.com/tintuc9', 9),
    (10, 'Tin tức 10', 'Nội dung tin tức 10', 'http://example.com/tintuc10', 10);

