<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>Prijava</title>
</head>
<body>
	<h1>Prijava</h1>

	<form action="${pageContext.request.contextPath}/login" method="post">
		<label>Korisničko ime: </label><input type="text" name="username"><br><br>
		<label>Lozinka: </label><input type="password" name="password"><br><br>

		<button>Prijavi se</button>
	</form>
	<c:if test="${!empty msgUNFE}">
		<p>${msgUNFE }</p>
	</c:if>
	<br>
	<a href="/Biletarnica">Početna</a>
</body>
</html>