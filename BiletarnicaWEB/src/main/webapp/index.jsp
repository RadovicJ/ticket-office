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
<title>Biletarnica</title>
</head>
<body>
	<h1>Biletarnica</h1>
	<sec:authorize access="isAuthenticated()">
		<h3>Prijavljeni ste kao: <sec:authentication property="principal.k.ime" /> <sec:authentication property="principal.k.prezime" /></h3>
		<a href="${pageContext.request.contextPath}/logout" >Odjava</a> 
		<a href="korisnik/pregled">Profil</a><br><br>
	</sec:authorize>
	<sec:authorize access="!isAuthenticated()">
		<h3>Niste Prijavljeni</h3>
		<a href="${pageContext.request.contextPath}/login" >Prijava</a> 
		<a href="registracija">Registracija</a><br><br>
	</sec:authorize>
	
	<a href="dogadjaji/svi">Događaji</a><br>
	
	<sec:authorize access="hasRole('Menadžer')">
		<a href="dogadjaji/unos">Unos Događaja</a><br>
		<a href="dogadjaji/unosSedista">Unos Sedišta</a><br>
	</sec:authorize>
	
	<sec:authorize access="hasRole('Admin')">
		<a href="grad/unos">Unos Mesta</a><br>
		<a href="lokacija/unos">Unos Lokacije</a><br>
		<a href="unosMenadzera">Unos Menadžera</a><br>
	</sec:authorize>
</body>
</html>