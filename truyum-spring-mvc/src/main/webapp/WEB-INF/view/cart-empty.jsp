<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		<p id="info">
			No items in cart. Use 'Add to Cart' option in <a
				href="/show-menu-list-customer">Menu Item List</a>.
		</p>
	</section>
	<footer>
		<p>Copyright &copy; 2021</p>
	</footer>
</body>
</html>