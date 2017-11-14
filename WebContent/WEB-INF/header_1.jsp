<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<header>
	<div>
	
	<h1>Het Cultuurhuis: voorstellingen <img id='imghoofding' src='<c:url value="/images/voorstellingen.png"/>' /></h1>
	
	<c:if test='${extraLinks==true}'>
		<a href='<c:url value="/reservatiemanje"/>'>Reservatiemandje</a>
		<a href='<c:url value="/bevestig_reservatie"/>'>Bevestiging
			Reservatie</a>
	</c:if>
	
	</div>
	

</header>