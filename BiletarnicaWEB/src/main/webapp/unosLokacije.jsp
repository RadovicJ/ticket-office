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
<title>Unos Lokacije</title>
</head>
<body>
	<h1>Unos Lokacije</h1>
	<form action="saveVenue" method="post">
		<label>Naziv Lokacije: </label>
		<input type="text" name="naziv"><br><br>
		
		<label>Mesto: </label>
		<select name="idG">
			<option disabled="disabled" selected="selected">Izaberi</option>
			<c:forEach items="${mesta }" var="m">
				<option value="${m.idGrad }">${m.ime }</option>
			</c:forEach>
		</select><br><br>
		
		<label>Menadžer: </label>
		<select name="idK">
			<option disabled="disabled" selected="selected">Izaberi</option>
			<c:forEach items="${menadzeri }" var="m">
				<option value="${m.idKorisnik }">${m.ime } ${m.prezime }</option>
			</c:forEach>
		</select><br><br>
		
		<input type="submit" value="Sačuvaj">
	</form>
	<c:if test="${!empty msgSaved}">
		<p>${msgSaved }</p>
	</c:if>
	<a href="/Biletarnica">Početna</a>
</body>
</html>