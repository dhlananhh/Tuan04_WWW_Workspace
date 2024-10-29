<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cart Page</title>
    <link rel="stylesheet" href="<c:url value='/assets/styles/cart.css'/>">
</head>
<body>

	<header>
		<h1><a href="${ pageContext.request.contextPath }/cart/">Cart Page</a></h1>
	</header>
	
	<nav>
	    <ul>
	        <li><a href="#">Home</a></li>
	        <li><a href="#">About</a></li>
	        <li><a href="#">Services</a></li>
	        <li><a href="#">Products</a></li>
	        <li><a href="#">Contact</a></li>
	        <li><a href="../cart/index.jsp">View Cart</a></li>
	    </ul>
	</nav>

	<div class="container">
	    <h2>YOUR SHOPPING CART</h2>
	    <table>
	        <thead>
	            <tr>
	                <th>Option</th>
	                <th>Product ID</th>
	                <th>Product Name</th>
	                <th>Product Image</th>
	                <th>Unit Price</th>
	                <th>Quantity</th>
	                <th>Total</th>
	            </tr>
	        </thead>
	        <tbody>
	        	<c:set var="total" value="0"></c:set>
	            <c:forEach var="item" items="${sessionScope.cart}">
	            	<c:set var="total" value="${total + item.product.price}"></c:set>
	                <tr>
	                    <td>
	                    	<a 	href="${ pageContext.request.contextPath }/cart?action=remove&id=${item.product.id}"
	                    		onclick="return confirm('Are you sure?')">
	                    		Remove
	                    	</a>
	                    </td>
	                    <td>${ item.product.id }</td>
	                    <td>${ item.product.name }</td>
	                    <td>
							<img src="${ pageContext.request.contextPath }/assets/images/${ item.product.image }" width="120">
						</td>
	                    <td>${ item.product.price }</td>
	                    <td>${ item.quantity }</td>
	                    <td>${ item.product.price * item.quantity }</td>
	                </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    <div class="total">
	        Subtotal (USD): $ ${ total }
	    </div>
	    <div class="button-container">
	        <a class="button" href="${ pageContext.request.contextPath }">
	        	Continue shopping
        	</a>
	    </div>
	</div>

	<footer>
	    <p>© 2024 IUH Store. All rights reserved.</p>
	    <p>Contact: info@iuhstore.vn | Telephone: 0123-456-789</p>
	</footer>

</body>
</html>