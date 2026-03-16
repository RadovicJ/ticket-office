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
<title>Moj Profil</title>
</head>
<body>
	<h1>Moj Profil</h1>
	<sec:authorize access="hasRole('Posetilac')">
		<h2>Moje Karte</h2>
		<table border="1">
			<tr>
				<th>Događaj</th>
				<th>Lokacija</th>
				<th>Mesto</th>
				<th>Datum</th>
				<th>Kategorija</th>
				<th>Red</th>
				<th>Broj</th>
				<th>Cena</th>
				<th>Datum Kupovine</th>
			</tr>
			<c:forEach items="${karteKorisnika }" var="kk">
				<tr>
					<td>${kk.sediste.tipsedista.dogadjaj.naziv }</td>
					<td>${kk.sediste.tipsedista.dogadjaj.venue.naziv }</td>
					<td>${kk.sediste.tipsedista.dogadjaj.venue.grad.ime }</td>
					<td><fmt:formatDate pattern = "dd.MM.yyyy." value = "${kk.sediste.tipsedista.dogadjaj.datum }" /></td>
					<td>${kk.sediste.tipsedista.kategorija }</td>
					<td>${kk.sediste.red }</td>
					<td>${kk.sediste.broj }</td>
					<td>${kk.sediste.tipsedista.cena } din</td>
					<td><fmt:formatDate pattern = "dd.MM.yyyy." value = "${kk.datumProdaje }" /></td>
				</tr>
			</c:forEach>
		</table>
	</sec:authorize>
	
	<sec:authorize access="hasRole('Menadžer')">
		<h2>Moja Mesta Održavanja</h2>
		<c:forEach items="${venues }" var="v">
			<br>
			<h3 style="font-size: 28px">${v.naziv } - ${v.grad.ime }</h3>
			<table border="1">	
				<tr>
					<th>Datum</th>
					<th>Naziv Dodađaja</th>
					<th>Broj Rasp. Karata</th>
					<th>Prihod</th>
				</tr>
				<c:forEach items="${dogadjajiMenadzera }" var="d">
					<tr>
						<td><fmt:formatDate pattern = "dd.MM.yyyy." value = "${d.datum }" /></td>
						<td>${d.naziv }</td>
						<c:set var="totalPrice" value="0" />
						<c:set var="totalKartas" value="0" />
						<c:forEach items="${d.kartas }" var="k">
							<c:set var="totalPrice" value="${totalPrice + k.sediste.tipsedista.cena}" />
							<c:set var="totalKartas" value="${totalKartas + 1}" />
						</c:forEach>
						<td>${totalKartas }</td>
						<td>${totalPrice } din</td>
					</tr>
				</c:forEach>
			</table>
		</c:forEach>
	</sec:authorize>
	
	<sec:authorize access="hasRole('Admin')">
		<h2>Pregled lokacija i menadžera</h2>
		<table border="1">
			<tr>
				<th>Menadžer</th>
				<th>Naziv lokacije</th>
			</tr>
			<c:forEach items="${sveLokacije }" var="l">
				<tr>
					<td>${l.korisnik.ime } ${l.korisnik.prezime }</td>
					<td>${l.naziv }</td>
				</tr>
			</c:forEach>
			<c:forEach items="${slobMenadžeri }" var="sm">
				<tr>
					<td>${sm.ime } ${sm.prezime }</td>
					<td></td>
				</tr>
			</c:forEach>
		</table>
	</sec:authorize>
	
	<br>
	<h2>Promena Lozinke</h2>
	<form action="changePassword" method="post">
		<input hidden="" type="number" name="idK" value="<sec:authentication property="principal.k.idKorisnik" />">
	
		<label>Nova lozinka: </label>
		<input type="password" name="password"><br><br>

		<input type="submit" value="Promeni">
	</form>
	<c:if test="${!empty msgSaved}">
		<p>${msgSaved }</p>
	</c:if>
	<br>
	<a href="/Biletarnica">Početna</a>
</body>
</html>