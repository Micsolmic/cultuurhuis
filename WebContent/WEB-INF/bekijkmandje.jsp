<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html>
<head>
<title>Mandje</title>
<link rel='stylesheet' href='<c:url value="/css/stijl.css"/>' />
<link rel="shortcut icon" type="image/x-icon"
	href='<c:url value="/images/favicon.ico"/>' />
</head>
<body>



	<div id='mainContainer'>

		<h1>
			Het Cultuurhuis: reservatiemandje <img id='imghoofding'
				src='<c:url value="/images/mandje.png"/>' />
		</h1>

		<a href='<c:url value="/index"/>'>Voorstellingen</a>

		<c:if test='${sessionScope.mand.leeg == false}'>


			<a href='<c:url value="/bevestig"/>'>Bevestiging Reservatie</a>

		</c:if>

		<form method="post">

			<table>
				<thead>


					<tr class='oneven'>
						<th>Datum</th>
						<th>Titel</th>
						<th>Uitvoerders</th>
						<th>Prijs</th>
						<th>plaatsen</th>
						<th><button id="knop">Verwijderen</button></th>
					</tr>



				</thead>

				<tbody>



					<c:forEach var='reservatieregel' items='${reservatieregels}'
						varStatus='status'>

						<tr <c:if test='${status.count%2==0}'>class='oneven'</c:if>>

							<td><fmt:formatDate
									value='${reservatieregel.voorstelling.datum}'
									pattern='d/M/yy HH:mm' /></td>
							<td>${reservatieregel.voorstelling.titel}</td>
							<td>${reservatieregel.voorstelling.uitvoerders}</td>
							<td>&#8364; <fmt:formatNumber
									value='${reservatieregel.voorstelling.prijs}'
									minFractionDigits='2' />
							</td>
							<td>${reservatieregel.plaatsen}</td>


							<td><input type="checkbox" name="verwijder"
								value="${reservatieregel.voorstelling.id}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</form>
		<c:if test="${empty reservatieregels}">
			<em>Nog geen voorstellingen in mandje</em>
		</c:if>
		<p>
			Te betalen: &#8364;
			<fmt:formatNumber value='${totaal}' minFractionDigits='2' />
		</p>
	</div>




</body>
</html>
