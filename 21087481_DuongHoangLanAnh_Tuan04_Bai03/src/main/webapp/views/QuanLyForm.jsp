<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Quản lý form</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/assets/styles/QuanLyForm.css'/>">
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
		<h1>Quản lý form</h1>
		<table>
			<tr>
			    <th>Thông tin danh mục</th>
			    <th>Mã tin tức</th>
				<th>Tiêu đề</th>
				<th>Nội dung TT</th>
				<th>Liên kết</th>
				<th>Actions</th>
			</tr>
			<c:forEach items="${ DanhSachTinTuc }" var="tinTuc">
				<tr>
					<td> <c:out value="${ tinTuc.danhMuc.tenDanhMuc }" /> </td>
					<td> <c:out value="${ tinTuc.maTinTuc }" /> </td>
					<td> <c:out value="${ tinTuc.tieuDe }" /> </td>
					<td> <c:out value="${ tinTuc.noiDung }" /> </td>
					<td> <c:out value="${ tinTuc.lienKet }" /> </td>
					<td class="action-buttons">
                        <!-- <a href="${pageContext.request.contextPath}/QuanLyForm?action=edit&id=<c:out value='${ tinTuc.maTinTuc }' />" class="button edit">Edit</a>  -->
                        <a 
                        	href="${pageContext.request.contextPath}/QuanLyForm?action=delete&id=<c:out value='${ tinTuc.maTinTuc }' />" class="button delete"
                        	onclick="return confirm('Are you sure?')">
                        		Delete
                     	</a>    
                    </td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>