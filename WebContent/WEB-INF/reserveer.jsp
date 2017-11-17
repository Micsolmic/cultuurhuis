<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%> 
<!doctype html>
<html>
<head>
<title>Reserveer</title>
<link rel='stylesheet' href='<c:url value="/css/stijl.css"/>'/>
<link rel="shortcut icon" type="image/x-icon" href='<c:url value="/images/favicon.ico"/>' />

<style>


dd{font-weight:bold;padding-bottom: 0.5em;}
</style>

</head>

<body>
<div id='mainContainer'>
<h1>Het Cultuurhuis: voorstellingen  <img id='imghoofding' src='<c:url value="/images/reserveren.png"/>'/></h1>
<a href='<c:url value="/index"/>'>Voorstellingen</a>

<dl>

<dt>Voorstelling:</dt>
<dd>${voorstelling.titel}</dd>
<dt>Uitvoerders:</dt>
<dd>${voorstelling.uitvoerders}</dd>
<dt>Datum:</dt>
<dd><fmt:formatDate value='${voorstelling.datum}' pattern='d/M/yy HH:mm'/></dd>
<dt>Prijs:</dt>
<dd>&#8364;  <fmt:formatNumber value='${voorstelling.prijs}' minFractionDigits='2'/></dd>
<dt>Vrije Plaatsen:</dt>
<dd>${voorstelling.vrijeplaatsen}</dd>

</dl>

<form id='form' method='post'>
Plaatsen: 
<input id='plaatsen' type='number' name='plaatsen' value='${zitjes}'>
<input type='hidden' name='voorstellingId' value='${voorstelling.id}' >
<button id='reserveer' type='submit'>Reserveren</button><span class='fout'></span>

</form>



</div>

<script>

window.onload = function(){
var form = document.getElementById('form');
var input = document.getElementById('plaatsen');
var knop = document.getElementById('reserveer');
var fout = document.getElementsByClassName('fout')[0];



knop.addEventListener('click',function(event){
	
	event.preventDefault();
	
	if((input.value >  ${voorstelling.vrijeplaatsen}) || (input.value < 1) || input.value == null){
		
		fout.innerHTML = ' \t tik een getal tussen 1 en '+ ${voorstelling.vrijeplaatsen};
		
	}else{
		
		form.submit();
		
	}
	
	});
	
};	
	



</script>

</body>
</html>

