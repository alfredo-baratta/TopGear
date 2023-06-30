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
	
	<button onclick="confirmOrder()"> Conferma ordine </button>
	
	<button onclick="stampaFattura()"> Stampa fattura in PDF </button>
	
	<script>
	
		function confirmOrder(){
			
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
			//ruba tutte le info che il cookie ha
			var cart = getCartFromCookie();
		 	const totalProducts = getTotalProductsFromCookie();
		 	const totalPrice = getTotalPriceFromCookie();
		 	
		 	const orderData = {
		 		cart: cart,
		 		totalProducts: totalProducts,
		 		totalPrice: totalPrice
		 	};
		  
			//esegui una richiesta AJAX per passare i dati alla servlet
			fetch('SalvaOrdine', {
    			method: 'POST',
   				headers: {
      				'Content-Type': 'application/json',
   				},
    			body: JSON.stringify(orderData),
  			})
    		.then(response => response.json())
    		.then(data => {
      			
    			//ciò che risulta in data lo mostrerò a schermo prima o poi
      			console.log(data);
    		})
    		.catch(error => {
      			
     		 console.error(error);
    		});
					
			//cancella tutto dal cookie
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
	          
	          //'sta funzione qui verrà chiamata solo per resettare il cookie sostanzialmente
	          //quindi me ne frego dei valori og di questi due:
	          const totalProducts = 0;
	          const totalPrice = 0.0; 

	          const expirationDate = new Date();
	          expirationDate.setDate(expirationDate.getDate() + 30);

	          document.cookie =
	            "cart=" + cartValue + "; expires=" + expirationDate.toUTCString();
	            
	          document.cookie = "totalProducts=" + totalProducts.textContent + "; expires=" + expirationDate.toUTCString();
	          document.cookie = "totalPrice=" + totalPrice.textContent + "; expires=" + expirationDate.toUTCString();
	        }
	      
	    function getTotalProductsFromCookie() {
	    	  if (document.cookie.includes("totalProducts=")) {
	    	    const totalProductsCookie = document.cookie
	    	      .split(";")
	    	      .find((cookie) => cookie.trim().startsWith("totalProducts="));

	    	    const totalProductsValue = totalProductsCookie.split("=")[1];

	    	    return parseInt(totalProductsValue);
	    	  }
	    	  return 0;
	    	}

	    function getTotalPriceFromCookie() {
	    	  if (document.cookie.includes("totalPrice=")) {
	    	    const totalPriceCookie = document.cookie
	    	      .split(";")
	    	      .find((cookie) => cookie.trim().startsWith("totalPrice="));

	    	    const totalPriceValue = totalPriceCookie.split("=")[1];

	    	    return parseFloat(totalPriceValue);
	    	  }
	    	  return 0.0;
	    	}
	    	
	    function stampaFattura(){
	    		//nothing per ora
	    	}
	</script>
</body>
</html>