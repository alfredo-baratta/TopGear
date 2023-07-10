<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="entities.*" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dettagli ordine</title>
</head>
<body>
	<%= request.getAttribute("totale") %>
	<%= request.getAttribute("data_pagamento") %>
	
	<br>
	<c:forEach var="prodotto" items="${ordini_accessorio}">
    <div class="single-order">
      <div class="order">
        <div class="order-description">
          <p class="order-date"><%= request.getAttribute("data_pagamento") %> </p>
          <p class="order-totale">Prezzo totale: <%= request.getAttribute("data_pagamento") %> €</p>
        </div>
        <div class="single-product">
          <p>${prodotto.getAccessorio().getImmagine()}</p>
          <p>Nome: ${prodotto.getAccessorio().getNome()}</p>
          <p>Descrizione: ${prodotto.getAccessorio().getDescrizione()}</p>
          <p>Quantita: ${prodotto.getQuantita()}</p>
          <p>Prezzo: ${prodotto.getAccessorio().getPrezzo()}</p>
        </div>
      </div>
      <div class="button">
      	<button onclick="location.href='ordine?id=0" class=return>Torna indietro</button>
      </div>
     </div>
    </c:forEach>
</body>
</html>