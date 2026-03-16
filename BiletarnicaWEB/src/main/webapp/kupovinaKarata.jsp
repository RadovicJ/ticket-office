<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<title>Kupovina Karte</title>
</head>
<body>
	<h1>Kupovina Karte</h1>
	<p>Događaj: ${dogadjaj.naziv }</p>
	<table border="1">
		<tr>
			<th>Kategorija</th>
			<th>Preostali Kapacitet</th>
			<th>Cena</th>
			<sec:authorize access="hasRole('Posetilac')">
			<th>Kupovina</th>
			</sec:authorize>
		</tr>
		<c:forEach items="${kategorije }" var="k">
			<tr>
				<td>${k.kategorija }</td>
				<td>${k.raspolozivost }</td>
				<td>${k.cena } din</td>
				<sec:authorize access="hasRole('Posetilac')">
				<td>
					<c:if test="${k.raspolozivost != 0}"><a href="potvrda?idD=${dogadjaj.idDogadjaj }&idK=${k.idTipSedista }">Kupi</a></c:if>
					<c:if test="${k.raspolozivost == 0}">Rasprodato</c:if>
				</td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="/Biletarnica/dogadjaji/svi">Nazad</a>
</body>
</html>