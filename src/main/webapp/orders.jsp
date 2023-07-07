<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>TopGear - Ordini</title>

<style>
	.container {
	  display: flex;
	  flex-direction: column;
	  align-items: center;
	}
	
	.single-order{
	  display: flex;
	  flex-direction: row;
	  align-items: center;
	}

	.order {
	  background-color: #ccc;
	  border-radius: 10px;
	  margin: 10px;
	  padding: 10px;
	  width: 400px;
	  display: flex;
	  justify-content: space-between;
	  align-items: center;
	}

	.order-description {
	  display: flex;
	  flex-direction: column;
	}

	.order-totale {
	  margin-top: 10px;
	}
	
	.order-button {
  	  width: 100%;
	  padding-top: 100%;
	  background-color: #555;
	  color: #fff;
	  font-weight: bold;
	  border-radius: 5px;
	}

	.button {
	  display: flex;
	  align-items: center;
	}

</style>

</head>
<body>
	
	<div class="container">
	<% if(request.getAttribute("ordini") != null) { %>
	
    <c:forEach var="ordine" items="${ordini}">
    <div class="single-order">
      <div class="order">
        <div class="order-description">
          <p class="order-date">Data: ${ordine.getDataPagamento()} </p>
          <p class="order-totale">Prezzo totale: ${ordine.getTotale()} €</p>
        </div>
      </div>
      <div class="button">
      	<button onclick="location.href='ordine?id=${ordine.getId()}" class="order-button">Dettagli</button>
      </div>
     </div>
    </c:forEach>
      <% } %>
    </div>
	
	<script>

	</script>
</body>
</html>