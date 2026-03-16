<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<style>
	body {
		background-color: rgb(200, 200, 200);
	}

	a {
    	text-decoration: none;
	}
</style>
<title>
	<sec:authorize access="hasRole('Admin')">Unos menadžera</sec:authorize>
	<sec:authorize access="!isAuthenticated()">Registracija</sec:authorize>
</title>
</head>
<body>
	<h1>
		<sec:authorize access="hasRole('Admin')">Unos menadžera</sec:authorize>
		<sec:authorize access="!isAuthenticated()">Registracija</sec:authorize>
	</h1>
	<form action="saveKorisnik" method="post">
		<label>Ime: </label>
		<input type="text" name="ime"><br><br>
		<label>Prezime: </label>
		<input type="text" name="prezime"><br><br>
		<label>Korisničko ime: </label>
		<input type="text" name="username"><br><br>
		<label>Lozinka: </label>
		<input type="password" name="password"><br><br>
		<input hidden="" name="idRole" value=<sec:authorize access="hasRole('Admin')">2</sec:authorize>
											<sec:authorize access="!isAuthenticated()">3</sec:authorize>>
		
		<input type="submit" value="Sačuvaj">
	</form>
	<c:if test="${!empty msgSaved}">
		<p>${msgSaved }</p>
	</c:if>
	<br>
	<a href="/Biletarnica">Početna</a>
</body>
</html>