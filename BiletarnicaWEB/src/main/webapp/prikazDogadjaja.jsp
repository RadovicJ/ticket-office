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
<title>Događaj: ${dogadjaj.naziv }</title>
</head>
<body>
	<h1>${dogadjaj.naziv }</h1>
	<p>Tip Događaja: ${dogadjaj.tipdogadjaja.tip }</p>
	<p>Datum: <fmt:formatDate pattern="dd.MM.yyyy." value="${dogadjaj.datum }" /></p>
	<p>Lokacija: ${dogadjaj.venue.naziv }</p>
	<p>Mesto: ${dogadjaj.venue.grad.ime }</p>
	<p>Opis: ${dogadjaj.opis }</p>
	<p><a href="ulaznice?idD=${dogadjaj.idDogadjaj }">Kupi ulaznice</a></p>
	<a href="/Biletarnica/dogadjaji/svi">Nazad</a>
</body>
</html>