<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="ISO-8859-1">
<title>TopGear - Pagamento</title>
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
	
	<button onclick="ConfirmOrder();"> Conferma ordine </button>
	
	<button onclick="stampaFattura();"> Stampa fattura in PDF </button>
	
	<script>
		function ConfirmOrder(){
			
			var paymentMethod = document.querySelector('input[name="paymentMethod"]:checked').value;
			
			if (paymentMethod === "mastercard") {
				//si procede con MasterCard
				//...
				procedeToSaveOrder();

			} else if (paymentMethod === "paypal") {
				//si procede con paypal
				//...
				procedeToSaveOrder();

			} else if (paymentMethod === "visa") {
				//si procede con visa
				//...
				procedeToSaveOrder();

			}
		}
		
		  function procedeToSaveOrder(){
			//passo 1: ottieni il carrello dal cookie
			const cart = getCartFromCookie();
		  
			//passo 2: esegui una richiesta AJAX per passare i dati alla servlet
			fetch('/SalvaOrdine', {
    			method: 'POST',
   				headers: {
      				'Content-Type': 'application/json',
   				},
    			body: JSON.stringify(cart),
  			})
    		.then(response => response.json())
    		.then(data => {
      			
      			console.log(data);
    		})
    		.catch(error => {
      			
     		 console.error(error);
    		});
			
			//passo 3: ???????????
					
			//passo 4: profit! ora cancella tutto dal cookie.
	        cart = [];
			saveCartToCookie(cart);
			
		}
		
		  function getCartFromCookie() {
	        if (document.cookie.includes("cart=")) {
	          const cartCookie = document.cookie
	            .split(";")
	            .find((cookie) => cookie.trim().startsWith("cart="));

	          const cartValue = cartCookie.split("=")[1];

	          return JSON.parse(decodeURIComponent(cartValue));
	        }
	        return [];
	    }
		
	      function saveCartToCookie(cart) {
	          const cartValue = encodeURIComponent(JSON.stringify(cart));

	          const expirationDate = new Date();
	          expirationDate.setDate(expirationDate.getDate() + 30);

	          document.cookie =
	            "cart=" + cartValue + "; expires=" + expirationDate.toUTCString();
	        }
		
	</script>
</body>
</html>