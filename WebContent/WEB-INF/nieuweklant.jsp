<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html>
<head>
<title>Nieuwe klant</title>
<link rel='stylesheet' href='<c:url value="/css/stijl.css"/>' />
<link rel="shortcut icon" type="image/x-icon"
	href='<c:url value="/images/favicon.ico"/>' />
</head>

<script type='text/javascript'>
	window.onload = function() {

		var form = document.getElementById("nieuweKlant");
		var button = document.getElementById("OK");
		var paswoord = document.getElementById("paswoord");
		var herhaalPaswoord = document.getElementById("herhaalPaswoord");
		var foutVeld = document.getElementsByClassName('fout')[0];
		var extrafout = document.getElementById("extrafout");
	
		herhaalPaswoord.addEventListener("blur", function(){
			
				if(paswoord.value != herhaalPaswoord.value){					
					foutVeld.textContent = "paswoorden moeten overeenkomen";					
				}else{					
					foutVeld.textContent = "";					
				}			
		});
		
		paswoord.addEventListener("blur", function(){
			
			if(paswoord.value != herhaalPaswoord.value){					
				foutVeld.textContent = "paswoorden moeten overeenkomen";	
				
			}else{					
				foutVeld.textContent = "";	
				
			}			
	});
 	
	 	
	extrafout.className += " extrafout";
					

	};
</script>

<body>



	<div id='mainContainer'>

		<h1>
			Het Cultuurhuis: nieuwe klant <img id='imghoofding'
				src='<c:url value="/images/nieuweklant.png"/>' />
		</h1>

		<a href='<c:url value="/index"/>'>Voorstellingen</a>

		<c:if test='${sessionScope.mand.leeg == false}'>


			<a href='<c:url value="/bevestig"/>'>Bevestiging Reservatie</a>
			<a href='<c:url value="/bekijkmandje"/>'>Bevestiging Reservatie</a>

		</c:if>


		<form id='nieuweKlant' method='post'>

			<label>Voornaam:</label> <input type="text" name="voornaam" required value='${klantInWording.voornaam}'>
			<label>Familienaam:</label> <input type="text" name="familienaam" required value='${klantInWording.familienaam}'>
			 <label>Straat:</label> <input type="text"	name="straat" required value='${klantInWording.straat}'>
			 <label>Huisnummer:</label> <input type="number" name="huisnummer" required value='${klantInWording.huisnr}'>
			 <label>Postcode:</label><input type="number" name="postcode" required value='${klantInWording.postcode}'> 
			 <label>Gemeente:</label><input type="text" name="gemeente" required value='${klantInWording.gemeente}'> 
			 <label>Gebruikersnaam:</label><input type="text" name="gebruikersnaam" required value='${klantInWording.gebruikersnaam}'>
			  <label>Paswoord:</label><input id="paswoord" type="password" name="paswoord" required > 
			  <label>Herhaal paswoord:</label> <input id="herhaalPaswoord" name="herhaalPaswoord" type="password" required > <br>
			<button type="submit" id="OK">OK</button>
			<br> 
			
			<c:if test='${empty onpaswoordelijk}'>
			<span class="fout"><c:if test='${loginBezet}'>Gebruikersnaam bestaat al. Kies een andere</c:if></span> 
			</c:if>
			
			<span id="extrafout" class="fout"><c:if test='${onpaswoordelijk}'>paswoorden moeten overeenkomen</c:if></span> 

		</form>


	</div>




</body>
</html>
