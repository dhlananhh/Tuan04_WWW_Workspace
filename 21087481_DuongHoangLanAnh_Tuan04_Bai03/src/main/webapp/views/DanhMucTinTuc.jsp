<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Danh mục tin tức</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/assets/styles/DanhMucTinTuc.css'/>">
</head>
<body>
	<a href="javascript:history.back()" class="back-link">
    	Back
   	</a>

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


	<div class="container" align="center">
		<h1>Danh mục tin tức</h2>
		<table>
			<tr>
			    <th>Mã danh mục</th>
			    <th>Tên danh mục</th>
				<th>Người quản lý</th>
				<th>Ghi chú</th>
			</tr>
			<c:forEach items="${ listDanhMuc }" var="danhMuc">
				<tr>
					<td> <c:out value="${ danhMuc.maDanhMuc }" /> </td>
					<td> <c:out value="${ danhMuc.tenDanhMuc }" /> </td>
					<td> <c:out value="${ danhMuc.nguoiQuanLy }" /> </td>
					<td> <c:out value="${ danhMuc.ghiChu }" /> </td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>