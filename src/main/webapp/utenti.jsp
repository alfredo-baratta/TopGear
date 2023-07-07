<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.html" %>

<!DOCTYPE html>
<html lang="it">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Lista utenti</title>
    <style>
      * {
        margin: 0;
        padding: 0;
      }

      body {
        font-family: Arial, Helvetica, sans-serif;
        height: 100%;
        min-height: 100vh;
        background-color: #fa885f;
      }

      .container {
        width: 100%;
        height: 100%;
        min-height: 100vh;
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 25px;
        flex-wrap: wrap;
        padding: 15px;
        margin-top: 65px;
      }

      .utente {
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        text-align: center;
        width: 400px;
        height: 90px;
        box-shadow: 0px 10px 15px -3px rgba(0, 0, 0, 0.1);
        border-radius: 5px;
        background-color: white;
      }

      .utente-description {
        display: flex;
        flex-direction: column;
        padding-left: 5px;
        padding-right: 5px;
      }

      .utente-title {
        font-size: 17px;
        font-weight: 600;
        margin-top: 5px;
      }

      .utente-cf {
        margin-top: 5px;
        margin-bottom: 5px;
      }
      
      input {
        width: 300px;
        height: 40px;
        padding: 7px;
        border: 2px solid #dbdada;
        border-radius: 5px;
        outline: none;
        font-size: 12px;
        margin-bottom: 8px;
      }
      
      button {
        background-color: black;
        color: white;
        padding: 5px;
        border: none;
        outline: none;
        width: 100%;
        height: 45px;
        font-weight: 400;
        max-width: 250px;
        margin-top: 15px;
      }
      
      button:hover {
      	cursor: pointer;
      }
      
      .search-box {
      	display: flex;
      	flex-direction: column;
      	align-items: center;
      }
      
      .titolo-utenti {
      	color: white;
      	margin-top: 15px;
      }

      @media screen and (max-width: 632px) {
        .container {
          justify-content: center;
        }
      }
      @media screen and (max-width: 318px) {
        .utente {
          width: 100%;
          padding-left: 0;
          padding-right: 0;
          height: 330px;
        }
      }
      @media screen and (max-width: 275px) {
        .utente {
          height: 300px;
        }
      }
    </style>
  </head>
  <body>
    <div class="container">
    <form class="search-box" method="post" action="ListaUtenti">
	    <input type="text" placeholder="Nome" name="nome" id="nome" />
	    <input type="text" placeholder="Cognome" name="cognome" id="cognome" />
	    <button type="submit">Cerca</button>
    </form>
    <h3 class="titolo-utenti">Lista utenti</h3>
    <% if(request.getAttribute("utenti") != null) { %>
    <c:forEach var="utente" items="${utenti}">
      <div class="utente">
        <div class="utente-description">
          <p class="utente-cf">${utente.getCodicefiscale()}</p>
          <p class="utente-title">
            ${utente.getNome()} ${utente.getCognome()}
          </p>
        </div>
      </div>
    </c:forEach>
      <% } %>
    </div>
  </body>
  <%@ include file="footer.html" %>
</html>
