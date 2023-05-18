<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>TopGear - Login</title>
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

      .login-box {
        background-color: white;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        max-width: 600px;
        width: 90%;
        height: 450px;
        padding: 10px;
      }

      .login-box a {
        text-decoration: none;
        color: black;
        font-weight: 600;
      }

      .login-box button {
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

      .login-box p {
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

      .img {
        width: 200px;
      }
      
      .error-message {
        margin-bottom: 10px;
      }

      @media screen and (max-width: .container) {
        body {
          background-color: white;
        }
        .login-box {
          box-shadow: none;
          width: 100%;
          padding: 0;
        }
        .container {
          width: 100%;
        }
      }
    </style>
  </head>
  <body>
    <img src="assets/Top-Gear-Logo.png" class="img" />

    <form method="post" action="login" class="login-box">
      <div class="container">
      <%
      if(request.getAttribute("error") != null) {
      %>
      <p class="error-message"><%= request.getAttribute("messgerr") %></p>
      <%
      }
      %>
        <div class="label-box">
          <label for="email">Email</label>
          <input
            type="email"
            name="email"
            autocapitalize="none"
            autofocus
            maxlength="50"
            placeholder="example@gmail.com"
            value="<%= (String) request.getAttribute("email") != null ? 
            		request.getParameter("email") : ""%>"
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
        <button type="submit">Accedi</button>
        <p>
          Non hai un account?
          <a href="register.jsp">Registrati</a>
        </p>
      </div>
    </form>
  </body>
</html>


