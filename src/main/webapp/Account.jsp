<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ include file="header.html" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
}

.container {
    max-width: 960px;
    margin: 0 auto;
    padding: 20px;
}

.titolo {
    text-align: center;
    margin-top: 50px;
}

.sezioni {
    display: flex;
    justify-content: space-between;
    margin-top: 30px;
}

.sezioni h3 {
    border: 1px solid red;
    padding: 10px;
    text-align: center;
    flex-basis: 45%;
}

</style>
<title>Account</title>
</head>
<body>
    <div class="container">
        <h1 class="titolo">Impostazioni account</h1>
        <div class="sezioni" id="1">
            <h3>Info utente</h3>
            <h3>Ordini</h3>
        </div>

        <div class="sezioni" id="2">
            <h3>Pagamenti</h3>
            <h3>Sezione</h3>
        </div>
    </div>

        <%@ include file="footer.html" %>
</body>
</html>
