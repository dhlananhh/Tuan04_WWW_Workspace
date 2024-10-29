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
		    max-width: 600px;
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
		input[type="text"],
		input[type="email"],
		input[type="date"] {
		    width: calc(100% - 22px);
		    padding: 10px;
		    border: 1px solid #ccc;
		    border-radius: 4px;
		    box-sizing: border-box;
		}
		input[type="radio"] {
		    margin-right: 10px;
		}
		label {
		    margin-right: 15px;
		}
		input[type="submit"] {
		    background-color: #28a745;
		    color: white;
		    border: none;
		    padding: 10px 20px;
		    border-radius: 4px;
		    cursor: pointer;
		    font-size: 16px;
		}
		input[type="submit"]:hover {
		    background-color: #218838;
		}
		.error-message {
		    color: red;
		    text-align: left;
		    margin: 10px 0;
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
		
		<h2>Edit User</h2>
		<form action="users?action=update" method="post">
			<c:if test="${ error != null }">
				<div class="error-message">
					<c:out value="${ errors }" escapeXml="false"></c:out>
				</div>
			</c:if>
			
			<table>
				<c:if test="${ user != null }">
					<input type="hidden" name="id" value="<c:out value='${ user.id }' />" />
				</c:if>
				<tr>
					<th>Full Name:</th>
					<td>
						<input type="text" name="fullName" maxlength="50" size="50" value="<c:out value='${user.fullName}' />" required />
					</td>
				</tr>
				<tr>
					<th>Email:</th>
					<td>
						<input type="email" name="email" maxlength="50" size="50" value="<c:out value='${user.email}' />" required />
					</td>
				</tr>
				<tr>
					<th>Job:</th>
					<td>
						<input type="text" name="job" maxlength="50" size="50" value="<c:out value='${user.job}' />" required />
					</td>
				</tr>
				<tr>
					<th>Birthdate:</th>
					<td>
						<input type="date" name="birthDate" size="50" value="<c:out value='${user.birthDate}' />" required />
					</td>
				</tr>
				<tr>
					<th>Gender:</th>
					<td>
						<label>
							<input type="radio" name="gender" value="Male" <c:if test="${ user.gender == 'Male' }">checked</c:if> />Male
						</label>
						<label>
							<input type="radio" name="gender" value="Female" <c:if test="${ user.gender == 'Female' }">checked</c:if> />Female
						</label>
					</td>
				</tr>
				<tr>
					<th>City:</th>
					<td>
						<input type="text" name="city" maxlength="50" size="50" value="<c:out value='${user.city}' />" required />
					</td>
				</tr>
				<tr>
					<th>Country:</th>
					<td>
						<input type="text" name="country" maxlength="50" size="50" value="<c:out value='${user.country}' />" required />
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
</body>