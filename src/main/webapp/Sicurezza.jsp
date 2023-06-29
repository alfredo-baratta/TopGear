<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="utils/sessionCheck.jsp" %>
<%@ include file="header.html" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <title>Modifica Account</title>

  <style>
    * {
      box-sizing: border-box;
      margin: 0;
      padding: 0;
    }

    body {
      font-family: Arial, sans-serif;
      background-color: #fa885f;
    }

    #radice {
      margin-top:100px;
      margin-bottom:100px;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .contenitore-esterno {
      width: 500px;
      background-color: #fff;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      border-radius: 4px;
    }

    .contenitore {
      padding: 20px;
    }

    .settings-section {
      margin-bottom: 30px;
    }

    .section-title {
      font-size: 18px;
      margin-bottom: 10px;
    }

    .riga {
      display: flex;
      align-items: center;
      margin-bottom: 10px;
    }

    label {
      flex: 0 0 120px;
      font-weight: bold;
    }

    input[type="text"],
    input[type="password"] {
      flex: 1;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 4px;
      transition: border-color 0.3s ease;
    }

    input[type="text"]:focus,
    input[type="password"]:focus {
      outline: none;
      border-color: #3366cc;
    }

    button {
      padding: 8px 12px;
      font-size: 14px;
      color: #fff;
      background-color: #4e9bb7;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }

    button:hover {
      background-color: #4e9bb7;
    }

    .save-button {
      margin-right: 10px;
    }

    .cancel-button {
      background-color: #ccc;
    }

    .edit-button {
      margin-left: 5px;
      border: none;
      background: none;
      padding: 0;
      font-size: 14px;
      color: #4e9bb7;
      cursor: pointer;
    }

    .tasti {
      display: flex;
      justify-content: center;
      margin-top: 15px;
    }

    .titolo-sezione {
      display: flex;
      justify-content: center;
      color: #fa885f;
      margin-top: 20px;
      margin-bottom: 20px;
      font-weight: bold;
    }

    h1 {
      display: flex;
      justify-content: center;
    }
  </style>
</head>

<body>

  <div id="radice">
    <div class="contenitore-esterno">
      <div class="contenitore">
        <h1>Sicurezza</h1>
        <form action="Sicurezza" method="POST">
          <div class="titolo-sezione">Credenziali</div>
          <div class="riga">
            <label>Email:</label>
            <input type="text" class="input-text" name="email"/>
          </div>
          <div class="riga">
            <label>Password attuale:</label>
            <input type="password" class="input-text" name="passwordAttuale"/>
          </div>
          <div class="riga">
            <label>Nuova password:</label>
            <input type="password" class="input-text" name="nuovaPassword"/>
          </div>
          <div class="tasti">
            <button type="submit" class="save-button">Salva</button>
            <button type="button" class="cancel-button">Annulla</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</body>
<%@ include file="footer.html" %>
</html>
