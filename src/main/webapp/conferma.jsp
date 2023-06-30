<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Confermato</title>
    <style>
      body {
        background-color: #fa885f;
        color: #ffffff;
        font-family: Arial, sans-serif;
        text-align: center;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
      }

      .container {
        max-width: 600px;
        padding: 20px;
      }

      h1 {
        color: #ffffff;
      }

      h3 {
        color: #ffffff;
        text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.3);
      }

      p {
        margin-bottom: 30px;
      }

      .button {
        background-color: #4e9bb7;
        color: #ffffff;
        padding: 10px 20px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <h1>Modifica Account</h1>
      <div>
        <h3>Modifiche avvenute con successo!</h3>
        <p>Le modifiche al tuo account sono state salvate correttamente.</p>
        <form action="modificAccount">
          <input
            type="submit"
            value="Torna alla pagina di modifica"
            class="button"
          />
        </form>
      </div>
    </div>
  </body>
</html>

