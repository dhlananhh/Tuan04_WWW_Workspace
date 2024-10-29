<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Thêm tin tức mới</title>
	
	<!-- CSS StyleSheet -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/assets/styles/TinTucForm.css'/>">
	
	<!-- Include jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    
    <!-- Include jQuery Validation Plugin -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.min.js"></script>
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
    	<h1>Thêm tin tức mới</h1>
    	
    	<!-- Dòng in ra thông báo lỗi nếu có -->
    	<div id="errorMessage" class="errorMessage"></div>
    
		<form id="newsForm" action="TinTucForm?action=insert" method="post" >
			<c:if test="${ error != null }">
				<div class="error-message">
					<c:out value="${ errors }" escapeXml="false"></c:out>
				</div>
			</c:if>
			
			<table>
				<c:if test="${ tinTuc != null }">
					<input type="hidden" id="maTinTuc" name="maTinTuc" value="<c:out value='${ tinTuc.maTinTuc }' />" />
				</c:if>
				<tr>
					<th>Tiêu đề:</th>
					<td>
						<input type="text" id="tieuDe" name="tieuDe" maxlength="255" size="50" value="<c:out value='${ tinTuc.tieuDe }' />" 
							required required title="Tiêu đề không được để trống" />
						<span style="color: red" class="errTieuDe">*</span>
					</td>
				</tr>
				<tr>
					<th>Nội dung:</th>
					<td>
						<!-- <input type="text" name="noiDung" maxlength="255" size="50" value="<c:out value='${ tinTuc.noiDung }' />" required />  -->
						<textarea id="noiDung" name="noiDung" rows="5" cols="50" required required title="Nội dung tin tức không được để trống"><c:out value="${ tinTuc.noiDung }" /></textarea>
						<span style="color: red" class="errNoiDung">*</span>
					</td>
				</tr>
				<tr>
					<th>Liên kết:</th>
					<td>
						<input type="text" id="lienKet" name="lienKet" maxlength="255" size="50" value="<c:out value='${ tinTuc.lienKet }' />" 
							required required title="Liên kết phải format là: http://" />
						<span style="color: red" class="errLienKet">*</span>
					</td>
				</tr>
				<tr>
					<th>Mã danh mục:</th>
					<td>
						<select id="maDanhMuc" name="maDanhMuc">
							<c:forEach items="${ listDanhMuc }" var="danhMuc">
								<option value="<c:out value='${ danhMuc.maDanhMuc }' />">
									<c:out value="${ danhMuc.tenDanhMuc }" />
								</option>
							</c:forEach>
						</select>
						<span style="color: red" class="errMaDanhMuc">*</span>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="Save" />
					</td>
				</tr>
			</table>
		</form>
    </div>
    
    
    <!-- JQuery Validate Form -->
    <script type="text/javascript">
        $(document).ready(function() {
            $("#newsForm").validate({
                rules: {
                    tieuDe: {
                        required: true,
                        maxlength: 255
                    },
                    noiDung: {
                        required: true
                    },
                    lienKet: {
                        required: true,
                        maxlength: 255
                    },
                    maDanhMuc: {
                        required: true
                    }
                },
                messages: {
                    tieuDe: {
                        required: "Tiêu đề không được để trống.",
                        maxlength: "Tiêu đề không được quá 255 ký tự."
                    },
                    noiDung: {
                        required: "Nội dung tin tức không được để trống."
                    },
                    lienKet: {
                        required: "Liên kết không được để trống.",
                        maxlength: "Liên kết không được quá 255 ký tự."
                    },
                    maDanhMuc: {
                        required: "Thông tin danh mục không được để trống."
                    }
                },
                errorPlacement: function(error, element) {
                    if (element.attr("name") == "tieuDe") {
                        error.appendTo(".errTieuDe");
                    } else if (element.attr("name") == "noiDung") {
                        error.appendTo(".errNoiDung");
                    } else if (element.attr("name") == "lienKet") {
                        error.appendTo(".errLienKet");
                    } else if (element.attr("name") == "maDanhMuc") {
                        error.appendTo(".errMaDanhMuc");
                    }
                },
                success: function(label) {
                    label.text("*").addClass("valid");
                }
            });
        });
    </script>
</body>
</html>