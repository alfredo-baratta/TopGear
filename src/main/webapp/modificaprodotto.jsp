<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<%@ page import="java.util.ArrayList" %>
    
<%@ include file="header.html" %>
<%String dacancellare = "";%>
<!DOCTYPE html>
<html lang="it">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Modifica</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    
     <style>
      * {
        margin: 0;
        padding: 0;
      }
      body {
        min-height: 100vh;
        height: 100%;
        font-family: Arial, Helvetica, sans-serif;
      }
      .container {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        width: 100%;
        min-height: 100vh;
        height: 100%;
        margin-top: 65px;
      }
      
      .container-immagine {
        display: flex;
        flex-direction: column;
        width: 50%;
        height: 100%;
        min-height: 100vh;
        margin-top: 15px;
      }
      .informazioni-prodotto {
        display: flex;
        flex-direction: column;
        width: 50%;
        height: 100%;
        min-height: 100vh;
        margin-top: 15px;
      }

      .descrizione {
        font-weight: 500;
        font-size: 17px;
        color: #171a20;
        margin-top: 15px;
        margin-bottom: 7px;
      }
      
      .submit-button, .up-label{
        background-color: #9ceaff;
        font-size: 14px;
        color: black;
        border: none;
        outline: none;
        width: 250px;
        height: 45px;
        border-radius: 5px;
    	display: block; /* Imposta il pulsante come blocco per poter applicare il margine laterale automatico */
        margin-top: 15px;
      }
      
      .up-label {
		  display: flex;
		  align-items: center;
		  justify-content: center;
		}
      
      .submit-button:hover, .up-label:hover {
        cursor: pointer;
        background-color: #4e9bb7;
        color: white;
      }
      
      .up-img{
      	display: none;
      }

      .first-product-image {
	  align-self: center;
	  width: 600px;
	  max-width: 600px;
	  max-height: 400px;
	  text-align: center;
	}
	
	.first-product-image img {
	  align-self: center;
	  width: 100%;
	  height: auto;
	  max-width: 600px;
	  max-height: 400px;
	  object-fit: contain;
	}
      .delete-button {
	  position: absolute;
	  top: 10px;
	  right: 10px;
	  margin: 3px;
	  background-color: #e53935;
	  color: #ffffff;
	  border: none;
	  border-radius: 4px;
	  padding: 6px 12px;
	  font-size: 14px;
	}
	
	.delete-button:hover {
	  background-color: #c62828;
	  cursor: pointer;
	}

	
      .image-container {
		  position: relative;
		  display: inline-block;
		  width: 600px;
	  }
	.first-product-image img,
	.image-container img {
	  width: 100%;
	  max-width: 600px;
	}
	.informazioni-prodotto {
		min-height: fit-content;
	}
	
	@media screen and (min-width: 1251px) {
		.informazioni-prodotto-form{
			padding-left: 10%;
			padding-right: 10%;
		}
		#uploadForm{
			padding-left: 10%;
			padding-right: 10%;
		}
	}
	
	.informazioni-prodotto input[type="text"],
	.informazioni-prodotto textarea {
	  width: 100%; /* Imposta la larghezza desiderata per i campi del form */
	}
	
	input[type="text"],
	input[type="number"],
	textarea{
		border: 1px solid grey;
		outline: none;
		margin-bottom: 15px;
		display: block;
		max-width: 450px;
		width: 450px;
        padding: 7px;
        border: 2px solid #dbdada;
        border-radius: 5px;
        outline: none;
        font-size: 12px;
		resize: none;
	}
	
	textarea{
		max-height: 100px;
	}
	
      
      @media screen and (max-width: 1251px) {
        .container {
          flex-direction: column;
          justify-content: flex-end;
        }
        .container-immagine {
          width: 100%;
          height: 100%;
          min-height: fit-content;
        }
        .informazioni-prodotto {
          width: 100%;
          height: 100%;
          width: 600px;
          align-self: center;
        }
      }

      @media screen and (max-width: 630px) {
        .first-product-image {
          width: 98%;
          gap: 0;
        }
        .informazioni-prodotto {
          width: 98%;
        }
        .image-container {
	    width: 90%;
	    }
	    input[type="text"],
		input[type="number"],
		textarea{
			border: 1px solid grey;
			outline: none;
			margin-bottom: 15px;
			display: block;
			max-width: 100%;
			width: 100%;
	        padding: 7px;
	        border: 2px solid #dbdada;
	        border-radius: 5px;
	        outline: none;
	        font-size: 12px;
			resize: none;
		}
      }
      
      .rbdiv{
      	margin-bottom: 10px;
      	display: block;
      }
      
    </style>
    <!-- Add the slick-theme.css if you want default styling -->
    <link
      rel="stylesheet"
      type="text/css"
      href="css/slick.css"
    />
    <!-- Add the slick-theme.css if you want default styling -->
    <link
      rel="stylesheet"
      type="text/css"
      href="css/slick-theme.css"
    />
  </head>
  <body>
  <%
      if(request.getAttribute("error") != null) {
      %>
      <p class="error-message"><%= request.getAttribute("messgerr") %></p>
      <%
      }
      %>
    <div class="container">
      <div class="container-immagine">
        <div class="first-product-image slider" data-slides-to-show="1">
          <c:forEach var="immagine" items="${immagini}">
	          <div class="image-container" align="center">
	          	<img src="/TopGear/immagini_a?id=${immagine}" />
	          	<c:if test="${fn:length(immagini) > 1}">
			      <button class="delete-button" onclick="cancellaImmagine('${immagine}')">Cancella</button>
			    </c:if>
	          </div>
          </c:forEach>
        </div>
      </div>
      
      <div class="informazioni-prodotto">
      <form class="informazioni-prodotto-form" action="upModAccessorio" method="post">
        <label for="nome">Nome:</label>
        <input type="text" name="nome" value="<%= request.getAttribute("nome") %>">
        
  		<label for="descrizione">Descrizione:</label>
  		<textarea id="descrizione" name="descrizione" rows="8" cols="50"><%= request.getAttribute("descrizione") %></textarea>
        
        <label for="prezzo">Prezzo:</label>
        <input type="number" id = "prezzo" step="0.01" name="prezzo" value="<%= request.getAttribute("prezzo") %>">
        
        <label for="prezzo">Iva:</label>
        <input type="number" id = "iva" step="0.01" name="iva" value="<%= request.getAttribute("iva") %>">
        
        <label for="qt_disp">Quantità disponibile:</label>
        <input type="number" id="qt_disp" name="disponibilita" value="<%= request.getAttribute("disponibilita") %>">
        
        <div class="rbdiv">
  			<input type="radio" id="rb1" class="visibile" name="visibile" value="false" <%= ("0".equals(String.valueOf(request.getAttribute("visibilita")))) ? "checked" : "" %>>
  			<label for="rb1">Non visibile</label>
		</div>
		
		<div>
  			<input type="radio" id="rb2" class="visibile" name="visibile" value="true" <%= ("1".equals(String.valueOf(request.getAttribute("visibilita")))) ? "checked" : "" %>>
  			<label for="rb2">Visibile</label>
		</div>
		
		<input type="hidden" id="dacancellare" name="dacancellare" value="<%= dacancellare %>">
		<input type="hidden" name="id" value="<%= request.getParameter("id") %>">
		
		<button type="submit" class="submit-button">Aggiorna campi testuali</button>
        </form>
        
        <form id="uploadForm" enctype="multipart/form-data">
			<input type="file" class="up-img" id="imageFile" name="imageFile" accept="image/*" onchange="uploadImage(<%= request.getParameter("id") %>)">
			<label for="imageFile" class="up-label">
			<i class="material-icons">add_photo_alternate</i>
			Carica immagine
			</label>
		</form>
        </div></div>   
        
<% 
	ArrayList<String> lista = (ArrayList<String>) request.getAttribute("immagini");
	Object primoElemento = null;
	if (lista != null && !lista.isEmpty()) {
    	primoElemento = lista.get(0);
	}
%>
    </div>

  </body>
  <%@ include file="footer.html" %>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
    		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  			crossorigin="anonymous"></script>
  <script
      type="text/javascript"
      src="js/slick.min.js"
    ></script>
  <script>
	  $(document).ready(function () {
	      $(".slider").slick({
	        autoplay: true,
	        autoplaySpeed: 2000,
	        dots: true,
	        responsive: [
	          {
	            breakpoint: 630,
	            settings: {
	              slidesToShow: 1,
	            },
	          },
	        ],
	      });
	    });	  
  
	  function cancellaImmagine(idImmagine) {
		    var idImg = {
		        immagineDaCancellare: idImmagine
		    };
		    
		    $.ajax({
		        url: 'cancellaimmagine',
		        type: 'POST',
		        dataType: 'json',
		        contentType: 'application/x-www-form-urlencoded',
		        processData: false,
		        data: $.param(idImg),
		        success: function(response) {
		            location.reload();
		        },
		        error: function() {
		        }
		    });
		}

		function uploadImage(idAccessorio) {
			  var formData = new FormData($('#uploadForm')[0]);
			  formData.append("idaccessorio", idAccessorio);
		
			  $.ajax({
				    url: 'UploadImmagine',
				    type: 'POST',
				    dataType: 'json',
				    contentType: false,
				    processData: false,
				    data: formData,
				    success: function(response) {
				        location.reload();
				    },
				    error: function() {
				    }
		        });
		    }
		</script>
</html>
