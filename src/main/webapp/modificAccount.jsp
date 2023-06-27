<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@ include file="utils/sessionCheck.jsp" %>
<%@ include file="header.html" %>

<!DOCTYPE html>
<html>
  <head>
  
  <style>
  	body {
    background-color: white;
    color: black;
    font-family: Arial, sans-serif;
  }
  
  .container {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
  }
  
  h1 {
    color: #4E9BB7;
    margin-bottom: 20px;
    margin-top: 90px;
    text-align: center;
  }
  
  .sezione {
    margin-bottom: 30px;
  }
  
  .titolo-sezione {
    display:flex;
    justify-content:center;
    color: #FA885F;
    font-size: 20px;
    font-weight: bold;
    margin-bottom: 10px;
  }
  
  .contenuto-sezione {
    border: 5px solid #9CEAFF;
    padding: 20px;
   border-radius: 20px;
  }
  
  .input-field {
    margin-bottom: 10px;
  }
  
  .input-label {
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
  }
  
  .input-text {
    width: 100%;
    padding: 8px;
    border: 1px solid #4E9BB7;
    border-radius: 5px;
  }
  
  .button {
    background-color: #FA885F;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  .container {
    display: flex;
  }
  .sezioni-sinistra {
    flex: 1;
    padding-right: 20px;
  }
  .sezioni-destra {
    flex: 1;
    padding-left: 20px;
  }
  .button-container {
    display: flex;
    justify-content: center;
    margin-top: 20px;
    margin-bottom: 75px;
  }
  </style>
  
    <meta charset="UTF-8" />
    <title>Modifica Account</title>
  </head>
  <body>
    <h1>Modifica Account</h1>
    
    
     <input type="button" value="modifica" onclick="abilitaModifica()">
    
        <form action="salvaModifiche" method="POST">
      <div class="container">
        <div class="sezioni-sinistra">
          <div class="sezione">
            <div class="titolo-sezione">Informazioni personali</div>
            <div class="contenuto-sezione">
              <div class="input-field">
                <label class="input-label">Nome:</label>
                <input type="text"class="input-text" name="nome"
                value= "<%= request.getAttribute("nome") %>" readonly />
              </div>
            </div>
          </div>

          <div class="sezione">
            
            <div class="contenuto-sezione">
              <div class="input-field">
                <label class="input-label">Cognome:</label>
                <input type="text" class="input-text" name="cognome"
                value= "<%= request.getAttribute("cognome") %>" readonly />
              </div>
            </div>
          </div>

          <div class="sezione">
            
            <div class="contenuto-sezione">
              <div class="input-field">
                <label class="input-label">Data di nascita:</label>
                <input type="text" class="input-text" name="datanascita" value= "<%= request.getAttribute("datanascita") %>"
                readonly />
              </div>
            </div>
          </div>

          <div class="sezione">
            
            <div class="contenuto-sezione">
              <div class="input-field">
                <label class="input-label">Codice fiscale:</label>
                <input type="text"class="input-text" name="cf" value= "<%= request.getAttribute("cf") %>"
                readonly />
              </div>
            </div>
          </div>

          <div class="sezione">
            <div class="titolo-sezione">Sicurezza</div>
            <div class="contenuto-sezione">
              <div class="input-field">
                <label class="input-label">E-mail:</label>
                <input type="text" class="input-text" name="email" value= "<%= request.getAttribute("email") %>"
                readonly />
              </div>
            </div>
          </div>
        </div>

        <div class="sezioni-destra">
          <div class="sezione">
            <div class="titolo-sezione">Contatti</div>
            <div class="contenuto-sezione">
              <div class="input-field">
                <label class="input-label">Telefono:</label>
                <input type="text" class="input-text" name="telefono"
                value= "<%= request.getAttribute("telefono") %>" readonly />
              </div>
            </div>
          </div>

          <div class="sezione">
           
            <div class="contenuto-sezione">
              <div class="input-field">
                <label class="input-label">Indirizzo:</label>
                <input type="text" class="input-text" name="via" value= "<%= request.getAttribute("via")
                %>" readonly />
              </div>
            </div>
          </div>

          <div class="sezione">
            
            <div class="contenuto-sezione">
              <div class="input-field">
                <label class="input-label">Città:</label>
                <input type="text"class="input-text" name="citta" value= "<%=request.getAttribute("citta") %>"
                readonly />
              </div>
            </div>
          </div>

          <div class="sezione">
            
            <div class="contenuto-sezione">
              <div class="input-field">
                <label class="input-label"
                  >CAP:</label>
                <input type="text" class="input-text" name="cap" value= "<%= request.getAttribute("cap") %>"
                readonly />
              </div>
            </div>
          </div>

          <div class="sezione">
            <div class="titolo-sezione">Sicurezza</div>
            <div class="contenuto-sezione">
              <div class="input-field">
                <label class="input-label">Password:</label>
                <input type="text"class="input-text" name="password" placeholder="*****************************************************" readonly />
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="button-container">
        <input type="submit" value="Salva modifiche" class="button"/>
      </div>
    </form>
    
    <script>
    function abilitaModifica() {
    	  var elements = document.getElementsByClassName('input-text');
    	  for (var i = 0; i < elements.length; i++) {
    	    elements[i].removeAttribute('readonly');
    	  }
    	}

    </script>
  </body>
   <%@ include file="footer.html" %>
</html>
