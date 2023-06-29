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
    <title><%= request.getAttribute("nome") %></title>
    
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
      .nome {
        font-size: 26px;
        font-weight: 600;
      }
      .prezzo,
      .descrizione-testuale {
        color: #393c41;
      }

      .prezzo {
        font-size: 15px;
      }

      .descrizione-testuale {
        font-size: 14px;
      }

      .descrizione {
        font-weight: 500;
        font-size: 17px;
        color: #171a20;
        margin-top: 15px;
        margin-bottom: 7px;
      }

      .add-to-cart {
        margin-top: 25px;
        align-self: center;
        height: 45px;
        font-weight: 400;
        max-width: 250px;
        margin-top: 15px;
        background-color: black;
        color: white;
        padding: 8px;
        border: none;
        outline: none;
      }

      .add-to-cart:hover {
        cursor: pointer;
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
      }
      @media screen and (max-width: 620px) {
  .image-container {
    width: 90%;
  }
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
    <div class="container">
      <div class="container-immagine">
        <div class="first-product-image slider" data-slides-to-show="1">
          <c:forEach var="immagine" items="${immagini}">
	          <div class="image-container align="center">
	          	<img src="/TopGear/immagini_a?id=${immagine}" />
	          	<c:if test="${fn:length(immagini) > 1}">
			      <button class="delete-button" onclick="cancellaImmagine('${immagine}')">Cancella</button>
			    </c:if>
	          </div>
          </c:forEach>
        </div>
      </div>
      
      
      <form class="informazioni-prodotto" action="upModAccessorio" method="post">
        Nome:<input type="text" name="nome" value="<%= request.getAttribute("nome") %>">
        <br>
  		Descrizione:<textarea id="descrizione" name="descrizione" rows="8" cols="50"><%= request.getAttribute("descrizione") %></textarea>
        <br>
        Prezzo:<input type="number" step="0.01" name="prezzo" value="<%= request.getAttribute("prezzo") %>">
        <br>
        Iva: <input type="number" step="0.01" name="iva" value="<%= request.getAttribute("iva") %>">
        <br>
        Quantità disponibile: <input type="number" name="disponibilita" value="<%= request.getAttribute("disponibilita") %>">
        <br>
        <label>
  			<input type="radio" name="visibile" value="false" <%= ("0".equals(String.valueOf(request.getAttribute("visibilita")))) ? "checked" : "" %>>
  			Non visibile
		</label>
		<br>
		<label>
  			<input type="radio" name="visibile" value="true" <%= ("1".equals(String.valueOf(request.getAttribute("visibilita")))) ? "checked" : "" %>>
  			Visibile
		</label>
		<input type="hidden" id="alfredo" name="dacancellare" value="<%= dacancellare %>">
		<input type="hidden" name="id" value="<%= request.getParameter("id") %>">
		<button type="submit">Salva modifiche</button>
        </form>
        
        
        <form id="uploadForm" enctype="multipart/form-data">
			<input type="file" id="imageFile" name="imageFile" accept="image/*" onchange="uploadImage(<%= request.getParameter("id") %>)">
		</form>

        
        
<% 
	ArrayList<String> lista = (ArrayList<String>) request.getAttribute("immagini");
	Object primoElemento = null;
	if (lista != null && !lista.isEmpty()) {
    	primoElemento = lista.get(0);
	}
%>
       	<button class="Annulla_modifiche" onClick="/catalogoadmin">Annulla</button>
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
		            // Gestisci gli errori di caricamento dell'immagine, se necessario
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
				        // Gestisci gli errori di caricamento dell'immagine, se necessario
				    }
		        });
		    }
		</script>
</html>
