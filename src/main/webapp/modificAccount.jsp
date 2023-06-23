<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@ include file="utils/sessionCheck.jsp" %>
<%@ include file="header.html" %>

<!DOCTYPE html>
<html>
  <head>
  
  body {
  display: flex;
  justify-content: center;
}
  <style>
  	
  </style>
  
    <meta charset="UTF-8" />
    <title>Modifica Account</title>
  </head>
  <body>
    <h1>Modifica Account</h1>
    <input type="button" value="modifica" onclick="abilitaModifica()">
    <form action="salvaModifiche" method="POST">
      <div>
        <label>Email:</label>
        <input type="text" name="email" value= "<%=
        request.getAttribute("email") %>" readonly />
      </div>
      <div>
        <label>Password:</label>
        <input type="password" name="password" value= "<%=
        request.getAttribute("password") %>" readonly />
      </div>
      <div>
        <label>Nome:</label>
        <input type="text" name="nome" value= "<%= request.getAttribute("nome")
        %>" readonly />
      </div>
      <div>
        <label>Cognome:</label>
        <input type="text" name="cognome" value= "<%=
        request.getAttribute("cognome") %>" readonly />
      </div>
      <div>
        <label>Via:</label>
        <input type="text" name="via" value= "<%=
        request.getAttribute("via") %>" readonly />
      </div>
      <div>
        <label>Città:</label>
        <input type="text" name="citta" value= "<%=request.getAttribute("citta") %>" readonly />
      </div>									
      <div>
        <label>Data di nascita:</label>
        <input type="text" name="datanascita" value= "<%=
        request.getAttribute("datanascita") %>" readonly />
      </div>
      <div>
        <label>Telefono:</label>
        <input type="text" name="telefono" value= "<%=
        request.getAttribute("telefono") %>" readonly />
      </div>
      <div>
        <label>CAP:</label>
        <input type="text" name="cap" value= "<%= request.getAttribute("cap")
        %>" readonly />
      </div>
      <div>
        <label>Codice fiscale:</label>
        <input type="text" name="cf" value= "<%=
        request.getAttribute("cf") %>" readonly />
      </div>
      <input type="submit" value="Salva modifiche" />
    </form>
    
    <script>
        function abilitaModifica(){
        document.getElementsByName("email")[0].removeAttribute("readonly");
        document.getElementsByName("password")[0].removeAttribute("readonly");
        document.getElementsByName("nome")[0].removeAttribute("readonly");
        document.getElementsByName("cognome")[0].removeAttribute("readonly");
        document.getElementsByName("via")[0].removeAttribute("readonly");
        document.getElementsByName("citta")[0].removeAttribute("readonly");
        document.getElementsByName("datanascita")[0].removeAttribute("readonly");
        document.getElementsByName("telefono")[0].removeAttribute("readonly");
        document.getElementsByName("cap")[0].removeAttribute("readonly");
        document.getElementsByName("cf")[0].removeAttribute("readonly");
      }
    </script>
  </body>
   <%@ include file="footer.html" %>
</html>
