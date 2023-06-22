<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@ include file="utils/sessionCheck.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Modifica Account</title>
  </head>
  <body>
    <h1>Modifica Account</h1>
    <form action="salvaModifiche" method="POST">
      <div>
        <label>Email:</label>
        <input type="text" name="email" value= "<%=
        request.getAttribute("email") %>" readonly />
      </div>
      <div>
        <label>Password:</label>
        <input type="password" name="password" value= "<%=
        request.getAttribute("email") %>" readonly />
      </div>
      <div>
        <label>Nome:</label>
        <input type="text" name="nome" value= "<%= request.getAttribute("email")
        %>" readonly />
      </div>
      <div>
        <label>Cognome:</label>
        <input type="text" name="cognome" value= "<%=
        request.getAttribute("email") %>" readonly />
      </div>
      <div>
        <label>Indirizzo:</label>
        <input type="text" name="indirizzo" value= "<%=
        request.getAttribute("email") %>" readonly />
      </div>
      <div>
        <label>Città:</label>
        <input type="text" name="citta" value= "<%=request.getAttribute("email") %>" readonly />
      </div>									
      <div>
        <label>Data di nascita:</label>
        <input type="text" name="datanascita" value= "<%=
        request.getAttribute("email") %>" readonly />
      </div>
      <div>
        <label>Cellulare:</label>
        <input type="text" name="cellulare" value= "<%=
        request.getAttribute("email") %>" readonly />
      </div>
      <div>
        <label>CAP:</label>
        <input type="text" name="cap" value= "<%= request.getAttribute("email")
        %>" readonly />
      </div>
      <div>
        <label>Username:</label>
        <input type="text" name="username" value= "<%=
        request.getAttribute("email") %>" readonly />
      </div>
      <input type="submit" value="Salva modifiche" />
    </form>
    
    <script>
        function abilitaModifica(){
        document.getElementsByName("email")[0].removeAttribute("readonly");
        document.getElementsByName("password")[0].removeAttribute("readonly");
        document.getElementsByName("nome")[0].removeAttribute("readonly");
        document.getElementsByName("cognome")[0].removeAttribute("readonly");
        document.getElementsByName("indirizzo")[0].removeAttribute("readonly");
        document.getElementsByName("citta")[0].removeAttribute("readonly");
        document.getElementsByName("datanascita")[0].removeAttribute("readonly");
        document.getElementsByName("cellulare")[0].removeAttribute("readonly");
        document.getElementsByName("cap")[0].removeAttribute("readonly");
        document.getElementsByName("username")[0].removeAttribute("readonly");
      }
    </script>
  </body>
</html>
