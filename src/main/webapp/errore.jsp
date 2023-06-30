<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifica Account</title>
    <style>
        body {
            background-color: #FA885F;
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
            background-color: #4E9BB7;
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
            <h3>Errore durante la modifica dell'account!</h3>
            <p>Si è verificato un errore durante il salvataggio delle modifiche al tuo account.</p>
            <form action="modificAccount">
                <input type="submit" value="Torna alla pagina di modifica" class="button">
            </form>
        </div>
    </div>
</body>
</html>

