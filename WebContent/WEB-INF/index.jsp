<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html>
<head>
<title>Zoek komende voorstellingen</title>
<link rel='stylesheet' href='<c:url value="/css/stijl.css"/>'/>
</head>
<body>
	<header>
		<h1>Het Cultuurhuis: voorstellingen</h1>
		<c:if test='${extraLinks==true}'>
		<a href='<c:url value="/reservatiemanje"/>'>Reservatiemandje</a>
		<a href='<c:url value="/bevestig_reservatie"/>'>Bevestiging Reservatie</a>
		</c:if>
		<h2>Genres</h2>
		<ul id='genreslijst'>
			<c:forEach var='genre' items='${genres}'>
				<li>
					<a href=<c:url><c:param name='genre' value='${genre.naam}'/> </c:url>>
						${genre.naam}
					</a>
				</li>
			</c:forEach>


		</ul>
	</header>



</body>
</html>
