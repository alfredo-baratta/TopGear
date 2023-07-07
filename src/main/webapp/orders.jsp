<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="entities.*" %>
<%@ page import="java.util.List" %>
    
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
	
	
	.pagination {
        display: flex;
        flex-direction: row;
        justify-content: center;
        margin-top: 20px;
    }

    .pagination button {
        margin: 0 5px;
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
    
    <% 
   		 
        int currentPage = (Integer) request.getAttribute("currentPage");
    	int pageSize = (Integer) request.getAttribute("pageSize");
        int totalOrders = (Integer) request.getAttribute("totalOrders");
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);

        // Genera i pulsanti per la paginazione
        String baseUrl = request.getRequestURL().toString().replace("orders.jsp", "orders");

        // Pulsante Pagina Precedente
 		if (totalPages > 1) {
       		if (currentPage > 1) {
          		String prevUrl = baseUrl + "?page=" + (currentPage - 1);
	 		 %>
	          	<a href="<%= prevUrl %>"><button>Precedente</button></a>
	 	 	 <%
	      	}
	
	     	for (int i = 1; i <= totalPages; i++) {
	        	  String pageUrl = baseUrl + "?page=" + i;
	 			 %>
	          		<a href="<%= pageUrl %>"><button<%= (currentPage == i) ? " disabled" : "" %>><%= i %></button></a>
	  			 <%
	     	}

	      	if (currentPage < totalPages) {
	         	 String nextUrl = baseUrl + "?page=" + (currentPage + 1);
	  			%>
	          		<a href="<%= nextUrl %>"><button>Successiva</button></a>
	 	 		<%
        	} 
    	%>
    	</div>
      <% } } %>
	
	<script>

	</script>
</body>
</html>