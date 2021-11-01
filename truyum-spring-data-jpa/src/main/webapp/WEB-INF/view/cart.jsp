<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Cart</title>
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
		<h2>Cart</h2>
		<c:if test="${removeCartItemStatus}">
			<p class="notification" id="center-info">Item removed from Cart successfully</p>		
		</c:if>
		<table>
			<thead>
				<tr>
					<th class="left-align-table-content">Name</th>
					<th class="left-align-table-content">Free Delivery</th>
					<th class="right-align-table-content">Price</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cart}" var="product">
					<tr>
						<td class="left-align-table-content">${product.name}</td>
						<td class="left-align-table-content">${product.freeDelivery ? 'Yes' : 'No'}</td>
						<td class="right-align-table-content">Rs. <fmt:formatNumber
								type="number" minFractionDigits="2" maxFractionDigits="2"
								value="${product.price}" /></td>
						<td><a href="/remove-cart?menuItemId=${product.id}&userId=${userId}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td></td>
					<td class="left-align-table-content">Total</td>
					<td class="right-align-table-content">Rs. <fmt:formatNumber
							type="number" minFractionDigits="2" maxFractionDigits="2"
							value="${total}" /></td>
					<td></td>
				</tr>
			</tfoot>
		</table>
	</section>
	<footer>
		<p>Copyright &copy; 2021</p>
	</footer>
</body>
</html>