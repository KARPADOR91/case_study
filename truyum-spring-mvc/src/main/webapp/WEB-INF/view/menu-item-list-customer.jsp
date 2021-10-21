<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Menu Item List Customer</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="/css/style.css" rel="stylesheet">
</head>
<body>
	<header>
		<div id="branding">
			<span>truYum</span> <span class="material-icons">restaurant</span>
		</div>
		<nav>
			<ul>
				<li><a id="menu" href="/show-menu-list-customer">Menu</a></li>
				<li><a id="cart" href="/show-cart?userId=1">Cart</a></li>
			</ul>
		</nav>
	</header>
	<section>
		<h2>Menu Items</h2>
		<c:if test="${addCartStatus}">
			<p class="notification" id="center-info">Item added to Cart Successfully</p>		
		</c:if>
		<table>
			<tr>
				<th class="left-align-table-content">Name</th>
				<th>Free Delivery</th>
				<th class="right-align-table-content">Price</th>
				<th>Category</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${menuItemList}" var="menuItem">
				<tr>
					<td class="left-align-table-content">${menuItem.name}</td>
					<td>${menuItem.freeDelivery ? 'Yes' : 'No'}</td>
					<td class="right-align-table-content">Rs. <fmt:formatNumber
							type="number" minFractionDigits="2" maxFractionDigits="2"
							value="${menuItem.price}" /></td>
					<td>${menuItem.category}</td>
					<td><a href="/add-to-cart?menuItemId=${menuItem.id}">Add
							to Cart</a></td>
				</tr>
			</c:forEach>
		</table>
	</section>
	<footer>
		<p>Copyright &copy; 2021</p>
	</footer>
</body>
</html>