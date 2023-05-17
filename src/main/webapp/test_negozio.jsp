<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Test funzionamento negozio/carrello</title>
</head>
<body>

<!-- Importing delle librerie per il testing -->
<jsp:directive.page import="entities.*"/>
<jsp:useBean id="cart" scope="session" class="entities.Carrello"></jsp:useBean>


<!-- Ok questo serve a inserire un determinato prodotto nel carrello -->
<% 
	String id = request.getParameter("id");	

	//se è stato aggiunto un nuovo prodotto al carrello
	if(id != null){
		
		//ottengo tutti i dati relativi al prodotto
		String nome = request.getParameter("nome");
		String descrizione = request.getParameter("descrizione");
		float prezzo = Float.parseFloat(request.getParameter("prezzo"));
		boolean isDisp = Boolean.parseBoolean(request.getParameter("isd"));
		int disp = Integer.parseInt(request.getParameter("disp"));
		//int iva = Integer.parseInt(request.getParameter("IVA"));
		
		//costruisco l'oggetto "prodotto"	1 		2			3		4	    5	  6		 
		Prodotto product = new Prodotto(id, nome, descrizione, prezzo, isDisp, disp, 22);
		
		//aggiungo il prodotto al carrello
		cart.insert(product);
	}
%>

<h1>CATALOGO A DISPOSIZIONE</h1>
<table width="60%" border="1" align="left">
	<tr>
		<th>Immagine</th>
		<th>Nome</th>
		<th>Descrizione</th>
		<th>Prezzo</th>
	</tr>
	<tr>
		<form action="test_negozio.jsp" method="post">
			<td><img src="sedili_noPNG.jpg" alt="Sedili" width="2"></td>
			<td>Sedili
			<td>Dei sedili aoo</td>
			<td>500 euris</td>
			<td><center><input type="submit" name="aggiungi" value="Aggiungi al carrello"/></center></td>
			<input type="hidden" name="id" value="1">
			<input type="hidden" name="nome" value="Sedili">
			<input type="hidden" name="descrizione" value="Dei sedili aoo">
			<input type="hidden" name="prezzo" value="500">
			<input type="hidden" name="isd" value="true">
			<input type="hidden" name="disp" value= 50>
			<input type="hidden" name="IVA" value= 22>
		</form>
	</tr>
	<tr>
		<form action="test_negozio.jsp" method="post">
			<td><img src="sedili_noPNG.jpg" alt="Sedili" width="2"></td>
			<td>Altro
			<td>Altra roba</td>
			<td>1000 euris</td>
			<td><center><input type="submit" name="aggiungi" value="Aggiungi al carrello"/></center></td>
			<input type="hidden" name="id" value="2">
			<input type="hidden" name="nome" value="Altro">
			<input type="hidden" name="descrizione" value="Altra roba">
			<input type="hidden" name="prezzo" value="1000">
			<input type="hidden" name="isd" value="true">
			<input type="hidden" name="disp" value= 10>
			<input type="hidden" name="IVA" value= 22>
		</form>
	</tr>
</table>

<a href="cart">Ritorna al carrello</a>

</body>
</html>