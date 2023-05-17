<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <title>TopGear - Registrazione</title>
    <style>
      * {
        margin: 0;
        padding: 0;
      }

      body {
        background-color: #f5f5f5;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        height: 100vh;
        font-family: Arial, Helvetica, sans-serif;
      }

      .label-box {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        margin-bottom: 20px;
      }

      .label-box label {
        font-size: 15px;
        font-weight: 600;
        margin-bottom: 3px;
      }

      .register-box {
        background-color: white;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        width: 650px;
        height: 650px;
        padding: 10px;
      }

      .register-box a {
        text-decoration: none;
        color: black;
        font-weight: 600;
      }

      .register-box button {
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

      .label-box input {
        width: 250px;
        height: 25px;
        padding: 7px;
        border: 2px solid #dbdada;
        border-radius: 5px;
        outline: none;
        font-size: 12px;
      }

      .register-box p {
        color: #797979;
        font-weight: 500;
        margin-top: 15px;
        font-size: 13px;
      }

      .container {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        width: 70%;
        height: 100%;
      }

      .group-box {
        display: flex;
        gap: 15px;
      }

      .img {
        width: 200px;
      }
      
      .error-message {
        margin-bottom: 10px;
      }

      @media screen and (max-width: 690px) {
        .register-box {
          width: 90%;
        }
      }

      @media screen and (max-width: 490px) {
        body {
          background-color: white;
          min-height: 100vh;
          height: 100%;
        }
        .group-box {
          flex-direction: column;
          gap: 0;
        }
        .container {
          width: 100%;
        }
        .register-box {
          box-shadow: none;
          width: 100%;
          height: 100%;
          padding-left: 0;
          padding-right: 0;
          padding-top: 15px;
          padding-bottom: 15px;
        }
      }
    </style>
  </head>
  <body>
    <img src="immagini/sito/Top-Gear-Logo.png" class="img" width="100%"/>
    <form method="post" action="registrazioneServlet" class="register-box">
      <div class="container">
      <%
      if(request.getAttribute("error") != null) {
      %>
      <p class="error-message"><%= request.getAttribute("messgerr") %></p>
      <%
      }
      %>
        <div class="group-box">
          <div class="label-box">
            <label for="email">Codice fiscale</label>
            <input
              type="text"
              name="cf"
              maxlength="16"
              autofocus
              placeholder="CF123456789"
              pattern="[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}"
              required
            />
          </div>
          <div class="label-box">
            <label for="email">Nome</label>
            <input
              type="text"
              name="nome"
              maxlength="30"
              placeholder="Mario"
              required
            />
          </div>
        </div>

        <div class="group-box">
          <div class="label-box">
            <label for="email">Cognome</label>
            <input
              type="text"
              name="cognome"
              maxlength="30"
              placeholder="Rossi"
              required
            />
          </div>
          <div class="label-box">
            <label for="email">Data di nascita</label>
            <input
              type="date"
              name="datanascita"
              required
            />
          </div>
        </div>

        <div class="group-box">
          <div class="label-box">
            <label for="email">Cellulare</label>
            <input type="tel" name="numtel" placeholder="3827465712" required />
          </div>
          <div class="label-box">
            <label for="email">Città</label>
            <input
              type="text"
              name="citta"
              maxlength="30"
              placeholder="Salerno"
              required
            />
          </div>
        </div>

        <div class="group-box">
          <div class="label-box">
            <label for="email">Indirizzo</label>
            <input
              type="text"
              name="indirizzo"
              maxlength="50"
              placeholder="Via Del Calabrese"
              required
            />
          </div>
          <div class="label-box">
            <label for="email">Cap</label>
            <input
              type="text"
              name="cap"
              maxlength="5"
              placeholder="84121"
              pattern="[0-9]{5}"
              required
            />
          </div>
        </div>

        <div class="group-box">
          <div class="label-box">
            <label for="email">Email</label>
            <input
              type="email"
              name="email"
              autocapitalize="none"
              maxlength="50"
              placeholder="example@gmail.com"
              required
            />
          </div>
          <div class="label-box">
            <label for="password">Password</label>
            <input
              type="password"
              name="password"
              maxlength="16"
              placeholder="********"
              required
            />
          </div>
        </div>

        <button type="submit">Registrati</button>
        <p>
          Hai già un account?
          <a href="login.jsp">Accedi</a>
        </p>
      </div>
    </form>
  </body>
</html>
