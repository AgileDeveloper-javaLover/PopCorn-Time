<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark p-2">
		<a class="navbar-brand">PoPCorn Time</a>
		<ul class="navbar-nav">
			<li class="nav-item active"><a class="nav-link" href="/logout">LogOut</a>
				<li class="nav-item active"><a class="nav-link" href="home.html">AddMovies</a>
			
			<li class="nav-item active"><a class="nav-link" href="/allMovie">UpdateMovies</a>
		
		</ul>
	</nav>
	<c:forEach items="${movies}" var="movie">
					<a href="/movieGetById/${movie.id}">${movie.name}</a>
					<br>
					<br>
	</c:forEach>

			</body>
</html>