<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%> 
<!doctype html>
<html>
<head>
<title>Zoek komende voorstellingen</title>
<link rel='stylesheet' href='<c:url value="/css/stijl.css"/>'/>
<link rel="shortcut icon" type="image/x-icon" href='<c:url value="/images/favicon.ico"/>' />
</head>
<body>
	

<div id='mainContainer'>	
	
	<h1>Het Cultuurhuis: Overzicht <img src='<c:url value="/images/bevestig.png"/>'></img></h1>
	
		<a href='<c:url value="/index"/>'>Voorstellingen</a>
	
	
	<h2>Gelukte reserveringen:</h2>
	
	<table>
<thead>


<tr class='oneven'>
    <th>Datum</th>
    <th>Titel</th> 
    <th>Uitvoerders</th>
    <th>Prijs(&#8364;)</th>
    <th>Plaatsen</th> 
   
  </tr>
</thead>

<tbody>
	
	<c:forEach var='rr' items='${lijstReservatieregels}'>
	
		<c:if test="${rr.gelukt}">
		
		<tr>
		<td><fmt:formatDate value='${rr.voorstelling.datum}' pattern='d/M/yy HH:mm'/></td>
		<td>${rr.voorstelling.titel}</td>
		<td>${rr.voorstelling.uitvoerders}</td>
		<td>&#8364;<fmt:formatNumber value='${rr.voorstelling.prijs*rr.voorstelling.plaatsen}' minFractionDigits='2'/> </td>
		<td>${rr.plaatsen}</td>
				</tr>
		
		
		
		</c:if>	
		
		<c:if test='${rr.gelukt==false}'>
		 tot hiieeer
			<c:set var='mislukt' value='Mislukte Reserveringen:'/>
		
		</c:if>
	</c:forEach>
</tbody>
</table>



<c:if test='${not empty mislukt}'>

<h2>${mislukt}</h2>
	<table>
<thead>


<tr class='oneven'>
    <th>Datum</th>
    <th>Titel</th> 
    <th>Uitvoerders</th>
    <th>Prijs(&#8364;)</th>
    <th>Plaatsen</th> 
    <th>Vrije plaatsen</th> 
   
  </tr>
</thead>

<tbody>
	
	<c:forEach var='rr' items='${lijstReservatieregels}'>
	
		<c:if test='${!rr.gelukt}'>
		
	
		
		<tr>
		<td><fmt:formatDate value='${rr.voorstelling.datum}' pattern='d/M/yy HH:mm'/></td>
		<td>${rr.voorstelling.titel}</td>
		<td>${rr.voorstelling.uitvoerders}</td>
		<td>&#8364;<fmt:formatNumber value='${rr.voorstelling.prijs}' minFractionDigits='2'/> </td>
		<td>${rr.plaatsen}</td>
		<td class='mislukt'>${rr.vrijePlaatsen}</td>
				
				
				</tr>
		
		
		
		</c:if>	
	</c:forEach>
</tbody>
</table>


</c:if>	


	
</div>




</body>
</html>
