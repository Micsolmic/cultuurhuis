<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html>
<head>
<title>Bevestig</title>
<link rel='stylesheet' href='<c:url value="/css/stijl.css"/>' />
<link rel="shortcut icon" type="image/x-icon"
	href='<c:url value="/images/favicon.ico"/>' />
	<link rel='stylesheet' href='<c:url value="/css/stijl.css"/>'/>
</head>
<body>



	<div id='mainContainer'>
	
	<h1>Het Cultuurhuis: bevestiging reservaties <img src='<c:url value="/images/bevestig.png"/>'></img></h1>
	
		<a href='<c:url value="/index"/>'>Voorstellingen</a>
	<a href='<c:url value="/bekijkmandje"/>'>Reservatiemandje</a>
	
	<h2>Stap 1: Wie ben je?</h2>
	<form method='post'>
	<label for="login">Gebruikersnaam:</label>
	<input name="login" required <c:if test='${stap2ontgrendeld}'>disabled="true"</c:if> >
	<label for="passwd">Passwoord:</label>
	<input name="passwd" type="password" required <c:if test='${stap2ontgrendeld}'>disabled="true" value="${klant.gebruikersnaam}"</c:if>>	
	<button type="submit" <c:if test='${stap2ontgrendeld}'>disabled="true"</c:if>>Zoek me op</button>
	
	
	</form>
	
	<form action='<c:url value="/nieuweklant"/>'>
	<button type="submit" <c:if test='${stap2ontgrendeld}'>disabled="true"</c:if>>Ik ben nieuw</button>
	</form>
	<p id="klantinfo">${klantinfo}</p>
	<span class="fout">${foutmelding}</span>
	<h2>Stap 2: Bevestigen</h2>
	
	
	
	<form method=get action='<c:url value="/overzicht"/>'>
	
	<button type="submit" <c:if test='${!stap2ontgrendeld}'>disabled="true"</c:if>>Bevestigen</button>
	</form>
	
	</div>




</body>
</html>
