<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Edit Menu Item</title>
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
				<li><a id="menu" href="/show-menu-list-admin">Menu</a></li>
			</ul>
		</nav>
	</header>
	<section>
		<h2>Edit Menu Item</h2>
		<form:form name="menuItemForm" modelAttribute="menuItem" method="post"
			action="edit-menu-item">
			<form:hidden path="id" />
			<div class="input">
				<form:label path="name">Name</form:label>
				<form:input path="name" id="title" name="title"
					value="${menuItem.name}" />
				<form:errors path="name"/>
			</div>
			<div class="container">
				<div class="input">
					<form:label path="price">Price (Rs.)</form:label>
					<form:input path="price" type="number" id="price" name="price" />
					<form:errors path="price"/>
				</div>
				<div class="input">
					<form:label path="active">Active</form:label>
					<div class="abc">
						<form:radiobutton path="active" value="true" id="inStock"
							name="inStock" />
						<form:label path="active">Yes</form:label>
						<form:radiobutton path="active" value="false" id="inStock"
							name="inStock" />
						<form:label path="active">No</form:label>
					</div>
				</div>
				<div class="input">
					<form:label path="dateOfLaunch">Date of Launch</form:label>
					<form:input path="dateOfLaunch" type="date" id="dateOfLaunch"
						name="dateOfLaunch" />
					<form:errors path="dateOfLaunch"/>
				</div>
				<div class="input">
					<form:label path="category">Category</form:label>
					<form:select path="category">
						<c:forEach items="${categories}" var="category">
							<form:option value="${category}">${category}</form:option>
						</c:forEach>
					</form:select>
				</div>
			</div>
			<div class="input">
				<div class="abc">
					<form:checkbox path="freeDelivery" id="freeDelivery"
						name="freeDelivery" value="${menuItem.freeDelivery}" />
					<form:label path="freeDelivery">Free Delivery</form:label>
				</div>
			</div>
			<div class="input">
				<input type="submit" id="submit" name="submit" value="Save">
			</div>
		</form:form>
	</section>
	<footer>
		<p>Copyright &copy; 2021</p>
	</footer>
</body>
</html>