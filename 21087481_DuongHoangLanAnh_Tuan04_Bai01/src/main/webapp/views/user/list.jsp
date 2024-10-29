<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>User Management App</title>
	<style type="text/css">
	body {
		font-family: Arial, sans-serif;
		background-color: #f4f4f4;
		color: #333;
		margin: 0;
		padding: 20px;
	}
	h1 {
	    color: #5a5a5a;
	}
	h2 {
	    margin: 20px 0;
	}
	a {
	    text-decoration: none;
	    color: #007BFF;
	    margin: 0 15px;
	}
	a:hover {
	    text-decoration: underline;
	}
	table {
	    width: 100%;
	    max-width: 800px;
	    margin: 20px auto;
	    border-collapse: collapse;
	    background: #fff;
	    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
	}
	th, td {
	    padding: 10px;
	    text-align: left;
	    border: 1px solid #ddd;
	}
	th {
	    background-color: #f2f2f2;
	}
	tr:hover {
	    background-color: #fafafa;
	}
	.action-buttons {
	    display: flex;
	    gap: 10px;
	}
	.action-buttons .button {
	    padding: 8px 12px;
	    border: none;
	    border-radius: 4px;
	    cursor: pointer;
	    font-size: 14px;
	}
	.edit {
	    background-color: #28a745; /* Green for edit */
	    color: white;
	}
	.edit:hover {
	    background-color: #218838;
	}
	.delete {
	    background-color: #dc3545; /* Red for delete */
	    color: white;
	}
	.delete:hover {
	    background-color: #c82333;
	}
	</style>
</head>
<body>
	<div align="center">
		<h1>User Management</h1>
		<h2>
			<a href="${ pageContext.request.contextPath }/users?action=new">
				Add New User
			</a>
			&nbsp;&nbsp;&nbsp; 
			<a href="${ pageContext.request.contextPath }/users"> 
				List All Users 
			</a>
		</h2>
		
		<h2>List Users</h2>
		<table>
			<tr>
			    <th>Id</th>
			    <th>Full Name</th>
				<th>Email</th>
				<th>Job</th>
				<th>Birth Date</th>
				<th>Gender</th>
				<th>City</th>
				<th>Country</th>
				<th>Actions</th>
			</tr>
			<c:forEach items="${ listUsers }" var="user">
				<tr>
					<td> <c:out value="${ user.id }" /> </td>
					<td> <c:out value="${ user.fullName }" /> </td>
					<td> <c:out value="${ user.email }" /> </td>
					<td> <c:out value="${ user.job }" /> </td>
					<td> <c:out value="${ user.birthDate }" /> </td>
					<td> <c:out value="${ user.gender }" /> </td>
					<td> <c:out value="${ user.city }" /> </td>
					<td> <c:out value="${ user.country }" /> </td>
					<td class="action-buttons">
                        <a href="${pageContext.request.contextPath}/users?action=edit&id=<c:out value='${user.id}' />" class="button edit">Edit</a>
                        <a href="${pageContext.request.contextPath}/users?action=delete&id=<c:out value='${user.id}' />" class="button delete">Delete</a>    
                    </td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>