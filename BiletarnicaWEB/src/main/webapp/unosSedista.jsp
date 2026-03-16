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
<title>Unos Sedišta</title>
</head>
<body>
	<h1>Unos Sedišta</h1>
	<form action="saveSedista" method="post">
		<label>Događaj:</label>
		<select name="idD">
			<option disabled="disabled" selected="selected">Izaberi</option>
			<c:forEach items="${dogadjajiMenadzera }" var="d">
				<option value="${d.idDogadjaj }">${d.naziv } - <fmt:formatDate pattern = "dd.MM.yyyy." value = "${d.datum }" /> - ${d.venue.naziv }, ${d.venue.grad.ime }</option>
			</c:forEach>
		</select><br><br>
		
		<label>Kategorija: </label>
		<input type="text" name="kat"><br><br>
		
		<label>Cena:</label>
		<input type="number" name="cena"><br><br>
		
		<label>Raspoloživost: </label>
		<input type="number" name="rasp"><br><br>
		
		<input type="submit" value="Sačuvaj">
	</form>
	<c:if test="${!empty msgSaved}">
		<p>${msgSaved }</p>
	</c:if>
	<br>
	<a href="/Biletarnica">Početna</a>
</body>
</html>