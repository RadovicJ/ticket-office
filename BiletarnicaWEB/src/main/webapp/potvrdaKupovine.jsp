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
<title>Potvrda</title>
</head>
<body>
	<h1>Potvrda</h1>
	<p>Kupac: <sec:authentication property="principal.k.ime" /> <sec:authentication property="principal.k.prezime" /></p>
	<p>Događaj: ${dogadjaj.naziv }</p>
	<p>Tip Sedišta: ${tipsedista.kategorija }</p>
	<p>Cena: ${tipsedista.cena } din</p>
	<form action="saveKarta" method="post">
		<input name="korisnik" value="<sec:authentication property="principal.k.idKorisnik" />" hidden="">
		<input type="date" name="datum" value="${danas }" hidden="">
		<input name="dogadjaj" value="${dogadjaj.idDogadjaj }" hidden="">
		<input name="tipSed" value="${tipsedista.idTipSedista }" hidden="">
		<input type="submit" value="Potvrdi">
	</form>
	<c:if test="${!empty msgSaved}">
		<p>${msgSaved }</p>
	</c:if>
	<br>
	<a href="/Biletarnica/dogadjaji/svi">Nazad</a>
</body>
</html>