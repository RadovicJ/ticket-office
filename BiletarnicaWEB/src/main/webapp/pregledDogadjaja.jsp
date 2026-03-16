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
	
	table, th, td {
		border: 1px solid black;
		border-collapse: collapse;
	}

	a {
    	text-decoration: none;
	}
</style>
<title>Događaji</title>
</head>
<body>
	<h1>Događaji</h1>
	<form action="filter" method="get">
		<label>Tip:</label>
		<select name="idT">
			<option value="0">Izaberi</option>
			<c:forEach items="${tipoviDog }" var="t">
				<option value="${t.idTipDogadjaja }">${t.tip }</option>
			</c:forEach>
		</select>
		<label>Datum:</label>
		<input type="date" name="datum" min="${danas }">
		<label>Lokacija:</label>
		<select name="idL">
			<option value="0">Izaberi</option>
			<c:forEach items="${venues }" var="v">
				<option value="${v.idVenue }">${v.grad.ime } - ${v.naziv }</option>
			</c:forEach>
		</select>
		<input type="submit" value="Pretraži">
	</form>
	<br>
	<c:if test="${!empty dogadjaji }">
		<table border="1">
			<tr>
				<th>Tip Događaja</th>
				<th>Naziv</th>
				<th>Datum</th>
				<th>Lokacija</th>
				<th>Mesto</th>
				<th>Detalji</th>
				<th>Ulaznice</th>
			</tr>
			<c:forEach items="${dogadjaji }" var="d">
				<tr>
					<td>${d.tipdogadjaja.tip }</td>
					<td>${d.naziv }</td>
					<td><fmt:formatDate pattern = "dd.MM.yyyy." value = "${d.datum }" /></td>
					<td>${d.venue.naziv }</td>
					<td>${d.venue.grad.ime }</td>
					<td><a href="pregled?idD=${d.idDogadjaj }">Vidi događaj</a></td>
					<td>
						<a href="ulaznice?idD=${d.idDogadjaj }">Kupi ulaznice</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty dogadjaji }">
		<p>Nema događaja za prikaz</p>
	</c:if>
	<br>
	<a href="/Biletarnica">Početna</a>
</body>
</html>