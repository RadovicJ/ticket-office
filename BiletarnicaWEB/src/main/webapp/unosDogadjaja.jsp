<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
<title>Unos događaja</title>
</head>
<body>
	<h1>Unos događaja</h1>
	<form action="saveDogadjaj" method="post">
		<label>Naziv Događaja: </label>
		<input type="text" name="naziv"><br><br>
		
		<label>Datum:</label>
		<input type="date" name="datum" min="${danas }"><br><br>
		
		<label>Opis Događaja: </label>
		<input type="text" name="opis"><br><br>
		
		<label>Tip:</label>
		<select name="idT">
			<option disabled="disabled" selected="selected">Izaberi</option>
			<c:forEach items="${tipoviDog }" var="t">
				<option value="${t.idTipDogadjaja }">${t.tip }</option>
			</c:forEach>
		</select><br><br>

		<label>Lokacija:</label>
		<select name="idL">
			<option disabled="disabled" selected="selected">Izaberi</option>
			<c:forEach items="${venuesMenadzera }" var="v">
				<option value="${v.idVenue }">${v.grad.ime } - ${v.naziv }</option>
			</c:forEach>
		</select><br><br>
		
		<input type="submit" value="Sačuvaj">
	</form>
	<c:if test="${!empty msgSaved}">
		<p>${msgSaved }</p>
	</c:if>
	<br>
	<a href="/Biletarnica">Početna</a>
</body>
</html>