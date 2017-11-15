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
			
			<c:import url='/WEB-INF/header_1.jsp'/> 
		
		<c:if test='${sessionScope.mand.leeg == false}'>
	
	<a href='<c:url value="/bekijkmandje"/>'>Reservatiemandje</a>
	<a href='<c:url value="/bevestiging"/>'>Bevestiging Reservatie</a>
	
	</c:if>
			
		<h2>Genres</h2>
	
		<ul id='genrelijst' style='padding-left: 0'>
				<c:forEach var='genre' items='${genres}'>
				<li>
			
				   <c:url value='/index' var='detailURL'>
				   <c:param name='genreId' value="${genre.id}"/> 
				   <c:param name='genreNaam' value="${genre.naam}"/>  						
				   </c:url>
				   <a href="${detailURL}">${genre.naam}</a>
				</li>
			</c:forEach>


		</ul> 


<table>
<thead>


<tr class='oneven'>
    <th>Datum</th>
    <th>Titel</th> 
    <th>Uitvoerders</th>
    <th>Prijs</th>
    <th>Vrije plaatsen</th> 
    <th>Reserveren</th>
  </tr>


</thead>

<tbody>



<c:forEach var='voorstelling' items='${vsg}' varStatus='status'>
	
<tr <c:if test='${status.count%2==0}'>class='oneven'</c:if>>
	
    <td> <fmt:formatDate value='${voorstelling.datum}' pattern='d/M/yy HH:mm'/> </td>  
    <td>${voorstelling.titel}</td> 
    <td>${voorstelling.uitvoerders}</td>
    <td>&#8364;  <fmt:formatNumber value='${voorstelling.prijs}' minFractionDigits='2'/> </td>
    <td>${voorstelling.vrijeplaatsen}</td>
   
  
    <td>
	<c:if test='${voorstelling.vrijeplaatsen > 0}'>
	<c:url value="/reserveer" var='reserveerUrl'> 
	<c:param name='voorstellingId' value='${voorstelling.id}'/>
	</c:url>
	<a href='${reserveerUrl}'>Reserveer</a>
	
	</c:if>
	
</c:forEach>	
</tbody>
</table>
<p>
<c:if test='${empty vsg}'>
	<em>Momenteel geen voorstellingen</em>
	</c:if>
</p>

	
	
</div>




</body>
</html>
