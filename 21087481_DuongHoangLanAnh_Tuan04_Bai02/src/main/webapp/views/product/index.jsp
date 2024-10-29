<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>IUH Store</title>
    <link rel="stylesheet" href="<c:url value='/assets/styles/product.css'/>">
</head>
<body>
	
	<header>
		<h1><a href="${pageContext.request.contextPath}">IUH Bookstore</a></h1>
	</header>
	
	<nav>
	    <ul>
	        <li><a href="#">Home</a></li>
	        <li><a href="#">About</a></li>
	        <li><a href="#">Services</a></li>
	        <li><a href="#">Products</a></li>
	        <li><a href="#">Contact</a></li>
	        <li><a href="${pageContext.request.contextPath}/cart">View Cart</a></li>
	    </ul>
	</nav>
	
	<div class="container">
	    <c:forEach var="product" items="${ products }">
	        <div class="smartphone">
	        	<p>Product Id: ${ product.id }</p>
	            <img 	src="${pageContext.request.contextPath}/assets/images/${ product.image }" 
	            		width="120" alt="${ product.name }">
	            <h2>${product.name}</h2>
	            <p class="price">Unit Price: $ ${ product.price }</p>
	            <label>Quantity: </label>
	            <input type="number" class="quantity" value="1" min="1" max="10">
	            <a 	class="button add-to-cart" 
	            	href="${pageContext.request.contextPath }/cart?&action=buy&id=${ product.id }">
	            	Add to cart
            	</a>
	        </div>
	    </c:forEach>
	</div>
	
	<footer>
	    <p>© 2024 IUH Store. All rights reserved.</p>
	    <p>Contact: info@iuhstore.vn | Telephone: 0123-456-789</p>
	</footer>
	
</body>
</html>