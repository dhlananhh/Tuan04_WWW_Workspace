<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Website quản lý tin tức trực tuyến</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/assets/styles/TrangChu.css'/>">
</head>
<body>
	<header>
        <img alt="logo" src="assets/images/news.png" class="bg-img">
    </header>
    
    <nav>
    	<a href="${ pageContext.request.contextPath }/DanhMucTinTuc">
			Danh mục tin tức
		</a>
        <a href="${ pageContext.request.contextPath }/DanhSachTinTuc">
			Danh sách tin tức
		</a>
        <a href="${ pageContext.request.contextPath }/TinTucForm">
			Thêm tin tức mới
		</a>
        <a href="${ pageContext.request.contextPath }/QuanLyForm">
			Chức năng quản lý
		</a>
    </nav>
    
    <div class="content"></div>
    
    <footer>
    	Dương Hoàng Lan Anh - 21087481 - DHKTPM17BTT
    </footer>
</body>
</html>