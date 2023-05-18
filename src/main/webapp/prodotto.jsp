<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%@ include file="header.html" %>
<!DOCTYPE html>
<html lang="en">
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
        width: 100%;
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
      }

      .immagini {
        display: flex;
        flex-direction: row;
        gap: 5px;
        margin-top: 5px;
        width: 600px;
        align-self: center;
      }

      .immagini img {
        height: 90px;
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
          	<img src="data:image/jpg;base64,${immagine}" />
          </c:forEach>
        </div>
      </div>
      <div class="informazioni-prodotto">
        <p class="nome"><%= request.getAttribute("nome") %></p>
        <div class="prezzo"><%= request.getAttribute("prezzo") %>$</div>
        <button class="add-to-cart">Aggiungi al carrello</button>
        <div class="descrizione">Descrizione</div>
        <p class="descrizione-testuale">
          <%= request.getAttribute("descrizione") %>
        </p>
      </div>
    </div>

    <script src="js/jquery-3.6.0.min.js"></script>
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
    </script>
  </body>
  <footer><%@ include file="footer.html" %></footer>
</html>
