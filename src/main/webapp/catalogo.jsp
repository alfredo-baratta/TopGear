<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Catalogo</title>
    <style>
      body {
        font-family: Arial, Helvetica, sans-serif;
        height: 100%;
      }

      .container {
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: row;
        gap: 25px;
        flex-wrap: wrap;
      }

      .product {
        display: flex;
        flex-direction: column;
        width: 300px;
        height: 350px;
        box-shadow: 0px 10px 15px -3px rgba(0, 0, 0, 0.1);
        border-radius: 5px;
      }

      .image img {
        max-width: 100%;
        border-top-right-radius: 5px;
        border-top-left-radius: 5px;
      }

      .product-description {
        display: flex;
        flex-direction: column;
        padding-left: 5px;
        padding-right: 5px;
        width: 100%;
        height: 100%;
      }

      .product-title {
        font-size: 17px;
        font-weight: 600;
        margin-top: 5px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        max-width: 99%;
      }

      .add-to-cart {
        background-color: black;
        color: white;
        padding: 5px;
        border: none;
        outline: none;
        width: 100%;
        height: 45px;
        font-weight: 400;
        max-width: 250px;
        align-self: center;
      }

      .page-title {
        font-size: 25px;
        font-weight: 600;
        margin-bottom: 30px;
        text-align: center;
      }

      .product-prezzo {
        margin-top: 0;
      }

      .add-to-cart:hover {
        cursor: pointer;
      }

      .image img:hover {
        cursor: pointer;
      }

      .product-title:hover {
        cursor: pointer;
      }

      @media screen and (max-width: 632px) {
        .container {
          justify-content: center;
        }
      }
      @media screen and (max-width: 318px) {
        .product {
          width: 100%;
          padding-left: 0;
          padding-right: 0;
          height: 330px;
        }
        .add-to-cart {
          width: 85%;
        }
      }
      @media screen and (max-width: 275px) {
        .product {
          height: 300px;
        }
      }
    </style>
  </head>
  <body>
    <div class="page-title">Catalogo</div>
    <div class="container">
    <% if(request.getAttribute("accessori") != null) { %>
    <c:forEach var="accessorio" items="${accessori}">
      <div class="product">
        <div onclick="location.href='prodotto/${accessorio.getId()}'" class="image">
          <img src="assets/${accessorio.getImmagine()}" />
        </div>
        <div class="product-description">
          <p onclick="location.href='test.html'" class="product-title">
            ${accessorio.getNome()}
          </p>
          <p class="product-prezzo">${accessorio.getPrezzo()} euro</p>
          <button class="add-to-cart">Aggiungi al carrello</button>
        </div>
      </div>
    </c:forEach>
      <% } %>
    </div>
  </body>
</html>
