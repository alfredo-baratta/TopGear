<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="header.html" %>
<!DOCTYPE html>
<html lang="it">
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
    		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
  			crossorigin="anonymous"></script>
    <title>TopGear - Carrello</title>
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

      .riepilogo {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        border: 3px solid #f5f5f5;
        border-radius: 5px;
        width: 100%;
        padding: 14px;
        max-width: 300px;
        max-height: 220px;
        margin-top: 20px;
      }

      .carrello-left {
        width: 60%;
        display: flex;
        flex-direction: column;
        align-items: center;
      }

      #carrello-vuoto {
        color: black;
        font-size: 15px;
        font-weight: bold;
        width: 100%;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        align-items: center;
        border: none;
        outline: none;
        margin-bottom: 15px;
      }

      .dettagli-riepilogo {
        display: flex;
        flex-direction: column;
        width: 100%;
      }

      .dettagli-box {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        width: 100%;
        margin-bottom: 0;
      }

      .title {
        color: grey;
        font-weight: 600;
        font-size: 17px;
        margin-bottom: 20px;
      }

      #prodotti-totali {
        color: black;
        font-size: 15px;
        font-weight: bold;
      }

      #prezzo-totale {
        color: black;
        font-size: 15px;
        font-weight: bold;
      }

      .container {
        width: 100%;
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: space-around;
        margin-top: 105px;
        min-height: 100vh;
      }

      button.checkout {
        background-color: #9ceaff;
        color: black;
        padding: 10px;
        border: none;
        outline: none;
        width: 100%;
        height: 45px;
        font-weight: 400;
        margin-top: 15px;
        border-radius: 5px;
      }

      button.checkout:hover {
        cursor: pointer;
        background-color: #4e9bb7;
        color: white;
      }

      .riepilogo hr {
        margin-top: 10px;
        margin-bottom: 10px;
        width: 100%;
        border-top: 1px solid #f5f5f5;
      }

      button.removeAll {
        cursor: pointer;
        background-color: #ffd5b4;
        color: black;
      }

      button.removeAll:hover {
        cursor: pointer;
        background-color: #fa885f;
        color: white;
      }

      table th {
        color: grey;
        font-weight: 600;
        padding-bottom: 10px;
        border-bottom: 2px solid #f5f5f5;
        font-size: 14px;
      }
      
      .table-head .emptyCell{
      	width: 100%;
      }

      table td {
        text-align: center;
        padding-top: 15px;
        padding-bottom: 15px;
      }
      
      .rimuovi-prodotto-bottone{
      	text-align: right;
      }

      .table-title {
        width: 100%;
        display: flex;
        justify-content: space-between;
        align-items: center;
        border: none;
        outline: none;
        margin-bottom: 15px;
      }
      
      .buttonRemoveAll{
      	display: flex;
      	align-items: center;
      	margin-left: 10px;
      }

      .item:not(:last-of-type) {
        border-bottom: 2px solid #f5f5f5;
      }

      .wrapper {
        display: flex;
        align-items: center;
        justify-content: center;
      }
      .wrapper span {
        width: 100%;
        text-align: center;
        font-size: 16px;
        font-weight: 600;
        cursor: pointer;
        user-select: none;
      }
      .wrapper span.num {
        font-size: 16px;
        border-right: 2px solid rgba(0, 0, 0, 0.2);
        border-left: 2px solid rgba(0, 0, 0, 0.2);
        pointer-events: none;
      }

      .first-row-cell {
        display: flex;
        gap: 5px;
        align-items: center;
      }

      .first-row-cell img {
        height: 100px;
      }

      .first-row-cell p {
        font-weight: 500;
        margin-top: 0;
      }

      .prezzo-testo {
        font-weight: 600;
      }

      .remove-button {
        width: 30px;
        height: 30px;
        background-color: #ffd5b4;
        border: 1px solid transparent;
        border-radius: 5px;
      }

      .remove-button:hover {
        background-color: #fa885f;
        border: 1px solid transparent;
        color: white;
        cursor: pointer;
      }

      .span-plus:hover {
        background-color: #9ceaff;
        color: black;
        cursor: pointer;
      }

      .span-minus:hover {
        background-color: #9ceaff;
        color: black;
        cursor: pointer;
      }

      @media screen and (max-width: 1000px) {
        .carrello-left {
          width: 90%;
        }
        .riepilogo {
          margin-top: 25px;
        }
      }

      @media screen and (max-width: 600px) {

        .table th{
          display: none;
        }
        
        .table,
        .table tbody,
        .table tr,
        .table td {
          display: flex;
          flex-direction: column;
          justify-content: center;
          width: 100%;
        }
        .table tr {
          margin-bottom: 15px;
        }
        .table td {
          position: relative;
        }
        .remove-button{
          align-self: center;
        }
        .riepilogo{
          align-self: center;
        }
        

      }
      @media screen and (max-width: 370px) {
        .riepilogo {
          padding-left: 0;
          padding-right: 0;
          padding-top: 14px;
          padding-bottom: 14px;
        }

        .riepilogo hr {
          display: none;
        }

        .riepilogo button {
          width: 90%;
        }

        .dettagli-box {
          justify-content: space-around;
        }
      }
    </style>

    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
  </head>
  <body>
    <div class="container">
      <div class="carrello-left">
        <div class="table-title" id="table-title">
          <h3>CARRELLO</h3>
          <button
            class="removeAll"
            onclick="removeAllFromCart()"
            id="removeAll"
            style="
              width: 30px;
              height: 30px;
              border: 1px solid transparent;
              border-radius: 5px;
            "
          >
            <i class="fa fa-trash" aria-hidden="true"></i>
          </button>
        </div>
        <table
          style="width: 100%; border-collapse: collapse"
          class="table"
          id="products-table"
          summary="Tabella dei prodotti"
        >
          <thead id="table-head">
            <tr>
              <th class="table-head">PRODOTTO</th>
              <th class="table-head">QUANTITA'</th>
              <th class="table-head">PREZZO</th>
              <th class="table-head emptyCell"></th>
            </tr>
          </thead>
          <tbody>
            <tr class="item">
              <td class="first-row-cell">
                <img
                  style="height: 100px"
                  src="dogwood-7978952_1920.jpg"
                  alt="image"
                  srcset=""
                />
                <p style="font-weight: 500; margin-top: 0">nome generico</p>
              </td>
              <td>
                <div class="wrapper">
                  <span class="minus">-</span>
                  <span id="num">1</span>
                  <span class="plus">+</span>
                </div>
              </td>
              <td>
                <p class="prezzo-testo">5333€</p>
              </td>
              <td class="rimuovi-prodotto-bottone">
                <button
                  class="removeProduct"
                  style="
                    width: 30px;
                    height: 30px;
                    border: 1px solid transparent;
                    border-radius: 5px;
                  "
                >
                  <i class="fa fa-trash" aria-hidden="true"></i>
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <div class="riepilogo">
        <div class="dettagli-riepilogo">
          <div class="dettagli-box">
            <p class="title">Prodotti presenti:</p>
            <p id="prodotti-totali">0</p>
          </div>
          <div class="dettagli-box">
            <p class="title">Totale con IVA:</p>
            <p id="prezzo-totale">0€</p>
          </div>
        </div>
        <hr />
        <button class="checkout" onclick="procedeToCheckout()" id="checkout">Checkout</button>
      </div>
    </div>
    <script>
      updateCartInfo();

      function loadCartProduct(productId) {
        const cart = getCartFromCookie();

        const existingProductIndex = cart.findIndex(
          (item) => item.productId === productId
        );

        if (existingProductIndex > -1) {
          const row = document.getElementById("num-" + productId);
          row.textContent = cart[existingProductIndex].quantity;
        }
      }

      const cart = getCartFromCookie();

      const table = document.getElementById("products-table");
          
      while (table.rows.length > 0) {
        table.deleteRow(0);
      }

      if (cart.length === 0) {
        setCartEmpty();
        showHeadTable();
        
      } else {
    	  
    	showHeadTable();
      	  
        cart.forEach((item) => {
          const row = table.insertRow();
          row.classList.add("item");
          row.setAttribute("id", "row-" + item.productId);

          // Prima cella
          const nameCell = row.insertCell();
          nameCell.classList.add("first-row-cell");

          const productImage = document.createElement("img");
          productImage.setAttribute(
            "src",
            "/TopGear/immagini-a?id=" + item.imageId
          );

          const productName = document.createElement("p");
          productName.textContent = item.nome;

          nameCell.appendChild(productImage);
          nameCell.appendChild(productName);

          // Seconda cella
          const quantityCell = row.insertCell();

          const div = document.createElement("div");
          div.classList.add("wrapper");

          const spanMinus = document.createElement("span");
          spanMinus.setAttribute("id", "minus-" + item.productId);
          spanMinus.classList.add("span-minus");
          spanMinus.textContent = "-";
          spanMinus.classList.add("minus");

          const spanNum = document.createElement("span");
          spanNum.setAttribute("id", "num-" + item.productId);
          spanNum.textContent = item.quantity;
          spanNum.classList.add("num");

          const spanPlus = document.createElement("span");
          spanPlus.setAttribute("id", "plus-" + item.productId);
          spanPlus.classList.add("span-plus");
          spanPlus.textContent = "+";
          spanPlus.classList.add("plus");

          spanPlus.addEventListener("click", function () {
            const spanNum = document.getElementById("num-" + item.productId);
            const newQuantity = incrementQuantity(item.productId);
            loadCartProduct(item.productId);
          });

          spanMinus.addEventListener("click", function () {
            if (item.quantity > 1) {
              const spanNum = document.getElementById("num-" + item.productId);
              const newQuantity = decrementQuantity(item.productId);
              loadCartProduct(item.productId);
            } else {
              removeOneFromCart(item.productId, item.quantity);
            }
          });

          div.appendChild(spanMinus);
          div.appendChild(spanNum);
          div.appendChild(spanPlus);
          quantityCell.appendChild(div);

          // Terza cella
          const priceCell = row.insertCell();

          const prezzoParagraph = document.createElement("p");
          prezzoParagraph.textContent = item.prezzo + "€";
          prezzoParagraph.classList.add("wrapper");
          prezzoParagraph.classList.add("prezzo-testo");

          priceCell.appendChild(prezzoParagraph);

          // Quarta cella
          const removeButtonCell = row.insertCell();
		  removeButtonCell.classList.add("rimuovi-prodotto-bottone");
          
          const removeProductButton = document.createElement("button");
          removeProductButton.classList.add("remove-button");

          removeProductButton.innerHTML =
            '<i class="fa fa-trash" aria-hidden="true"></i>';

          removeProductButton.addEventListener("click", function () {
            const productId = item.productId;
            removeFromCart(productId);
          });

          removeButtonCell.appendChild(removeProductButton);
        });
        
      }
      
      // Setta il messaggio di informazione quando il cart è vuoto
      function setCartEmpty(){
    	  const emptyRow = table.insertRow();
          const emptyCell = emptyRow.insertCell();
          emptyCell.textContent = "Non sono presenti prodotti nel carrello.";
          emptyCell.style.fontWeight = "bold";
          emptyCell.style.paddingTop = "25px";
          emptyCell.colSpan = 4;
          emptyCell.classList.add("carrello-vuoto");

          const buttonToRemove = document.getElementById("removeAll");
          buttonToRemove.setAttribute("hidden", "true");
          
      }

      // Aggiorna le informazioni sul carrello nel riepilogo
      function updateCartInfo() {
        const cart = getCartFromCookie();
        const totalProducts = document.getElementById("prodotti-totali");
        const totalPrice = document.getElementById("prezzo-totale");

        let productsCount = 0;
        cart.forEach((item) => {
          productsCount += item.quantity;
        });
        totalProducts.textContent = productsCount;

        let total = 0;
        cart.forEach((item) => {
          total += item.quantity * item.prezzo;
        });
        totalPrice.textContent = total + "€";
        
        saveCartToCookie(cart);
      }
      
      // Mostra a schermo la table head
      function showHeadTable(){
    	  const table_head = document.getElementById("table-head");
          const first_row = table_head.insertRow();
          first_row.insertCell(0).outerHTML = "<th>PRODOTTO</th>";
          first_row.insertCell(1).outerHTML = "<th>QUANTITA'</th>";
          first_row.insertCell(2).outerHTML = "<th>PREZZO</th>";
          
          const emptyCell = first_row.insertCell(3);
          emptyCell.classList.add("emptyCell");
          emptyCell.outerHTML="<th></th>";
      }

      // Aggiungi un prodotto al carrello o incrementa la quantità se già presente
      function addToCart(productId, nome, prezzo, quantity = 1, imageId) {
        let cart = getCartFromCookie();

        const existingProductIndex = cart.findIndex(
          (item) => item.productId === productId
        );

        if (existingProductIndex > -1) {
          cart[existingProductIndex].quantity += quantity;
          loadCartProduct(productId);
        } else {
          cart.push({ productId, quantity, nome, prezzo, imageId });
        }

        saveCartToCookie(cart);
        updateCartInfo();
        updateCartQuantity();
        return quantity;
      }

      // Incrementa la quantità di un prodotto nel carrello
      function incrementQuantity(productId, quantity = 1) {
        const updatedQuantity = addToCart(productId, null, null, 1);
        return updatedQuantity;
      }

      // Decrementa la quantità di un prodotto nel carrello
      function decrementQuantity(productId, quantity = 1) {
        const updatedQuantity = removeOneFromCart(productId, 1);
        return updatedQuantity;
      }

      // Aggiorna la quantità in un prodotto
      function updateProducts() {
        const cart = getCartFromCookie();

        cart.forEach((item) => {
          const spanNum = document.getElementById("num-" + item.productId);
          spanNum.textContent = item.quantity.toString();
        });
      }

      // Ottieni il carrello dal cookie
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

      // Rimuove un singolo prodotto dalla quantità
      function removeOneFromCart(productId, quantity = 1) {
        let cart = getCartFromCookie();

        const existingProductIndex = cart.findIndex(
          (item) => item.productId === productId
        );

        if (existingProductIndex > -1) {
          const existingQuantity = cart[existingProductIndex].quantity;

          if (existingQuantity > 1) {
            cart[existingProductIndex].quantity -= quantity;
          }

          saveCartToCookie(cart);
        }

        loadCartProduct(productId);

        updateCartInfo();
        updateCartQuantity();
        return quantity;
      }

      // Rimuovi un prodotto dal carrello o diminuisci la quantità se presente
      function removeFromCart(productId) {
        let cart = getCartFromCookie();

        const existingProductIndex = cart.findIndex(
          (item) => item.productId === productId
        );

        if (existingProductIndex > -1) {
          cart.splice(existingProductIndex, 1);

          saveCartToCookie(cart);
        }

        const table = document.getElementById("products-table");

        const row = document.getElementById("row-" + productId);

        if (table && row) {
          table.deleteRow(row.rowIndex);
        }
        
        if(cart.length === 0){
        	setCartEmpty();
        }

        updateCartInfo();
        updateCartQuantity();
        loadCartProduct(productId);
      }

      // Rimuovi tutti i prodotti dal carrello
      function removeAllFromCart() {
        let cart = getCartFromCookie();
        cart = [];

        const table = document.getElementById("products-table");

        while (table.rows.length > 0) {
          table.deleteRow(0);
        }
        
        setCartEmpty();
        showHeadTable()
        updateCartInfo();
        updateCartQuantity();
      }
      
      function procedeToCheckout(){
    	  window.location.href = "pagamento";
      }
      
      function saveCartToCookie(cart) {
        const cartValue = encodeURIComponent(JSON.stringify(cart));
        const totalProducts = document.getElementById("prodotti-totali");
        const totalPrice = document.getElementById("prezzo-totale");
        

        const expirationDate = new Date();
        expirationDate.setDate(expirationDate.getDate() + 30);

        document.cookie =
          "cart=" + cartValue + "; expires=" + expirationDate.toUTCString();
          
        document.cookie = "totalProducts=" + totalProducts.textContent + "; expires=" + expirationDate.toUTCString();
        document.cookie = "totalPrice=" + totalPrice.textContent + "; expires=" + expirationDate.toUTCString();
      }
    </script>
  </body>
  <%@ include file="footer.html" %>
</html>
