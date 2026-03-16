<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<title>Unos Mesta</title>
</head>
<body>
	<h1>Unos Mesta</h1>
	<form:form action="saveGrad" method="post" modelAttribute="grad">
		<label>Unesi naziv mesta:</label>
		<form:input type="text" path="ime" />
		<input type="submit" value="Sačuvaj">
	</form:form>
	<c:if test="${!empty msgSaved}">
		<p>${msgSaved }</p>
	</c:if>
	<a href="/Biletarnica">Početna</a>
</body>
</html>