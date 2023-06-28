<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>Pagamento</title>
<style>

body{
	display: flex;
	align-items: center;
	justify-content: space-between;
}

div{
	display: flex;
	flex-direction: row;
	align-items: center;
}

img{
	height: 50px;
}
</style>
</head>
<body>

	<h2>Seleziona il metodo di pagamento:</h2>
	
	<div>
		<input type="radio" id="mastercard" name="paymentMethod" value="mastercard">
		<label for="mastercard"><img src="assets/mstercard.png" alt="MasterCard"></label>
	</div>
	
	<div>
		<input type="radio" id="paypal" name="paymentMethod" value="paypal">
		<label for="paypal"><img src="assets/paypal.png" alt="PayPal"></label>
	</div>
	
	<div>
		<input type="radio" id="visa" name="paymentMethod" value="visa">
		<label for="visa"><img src="assets/visa.png" alt="Visa"></label>
	</div>
	
	<button onclick="ButtonFunction();"> Conferma ordine </button>
	
	<script>
		function ButtonFunction(){
			
			var paymentMethod = document.querySelector('input[name="paymentMethod"]:checked').value;
			
			if (paymentMethod === "mastercard") {
				// si procede con MasterCard

			} else if (paymentMethod === "paypal") {
				// si procede con paypal

			} else if (paymentMethod === "visa") {
				// si procede con visa

			}
			
			//chiama la funzione per ottenere il cart dal cookie (ricopia la funzione apposita da cart.jsp) e 
			//inserisce tutti i dati nel DB eventualmente facendo una chiamata post ad una servlet "CreazioneOrdineServlet" DA FARE
			
			//ok update mi sà che me la posso cavare con un bean apposito, let's all get ready for Ordine.java!
			//spero che questo funzioni:
			const cart = getCartFromCookie();
				//penso di spostarlo in una function apposita
			}
	</script>
</body>
</html>