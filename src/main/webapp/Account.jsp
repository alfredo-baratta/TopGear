<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<%@ include file="utils/sessionCheck.jsp" %>
<%@ include file="header.html" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <style>
    body {
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background-color: #FA885F;
    font-family: Arial, sans-serif;
  }
  
  .container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
  }
  
  h1 {
    font-size: 24px;
    color: #FFFFFF;
    text-align: center;
    margin-bottom: 20px;
  }
  
  .sezione {
    width: 200px;
    height: 200px;
    margin: 10px;
    padding: 20px;
    background-color: #FFFFFF;
    border: 1px solid #4E9BB7;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
    text-align: center;
    transition: all 0.3s ease;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  
  .sezione:hover {
    transform: scale(1.05);
  }
  
  .sezione:hover svg path,
  .sezione:hover h2,
  .sezione:hover p {
    color: #4E9BB7;
  }
  
  .icon {
    margin-bottom: 10px;
  }
  
  h2 {
    font-size: 16px;
    color: #000000;
    margin-bottom: 10px;
  }
  
  p {
    font-size: 14px;
    color: #000000;
    margin: 0;
  }
  .sezione a {
    text-decoration: none;
    color: inherit;
  }
  .contenitore{
   margin-bottom:200px;
    margin-top:200px;}

    </style>
    <title>Gestione Account</title>
  </head>
  <body>
  <div class="contenitore"> 
    <h1>Gestisci account</h1>
    <div class="container">
      <div class="sezione">
        <a href="<%=request.getContextPath()%>/modificAccount" >
          <svg
            class="icon"
            width="25"
            height="25"
            fill="none"
            stroke="currentColor"
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path d="M12 10a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7Z"></path>
            <path
              d="M3 20.4v.6h18v-.6c0-2.24 0-3.36-.436-4.216a4 4 0 0 0-1.748-1.748C17.96 14 16.84 14 14.6 14H9.4c-2.24 0-3.36 0-4.216.436a4 4 0 0 0-1.748 1.748C3 17.04 3 18.16 3 20.4Z"
            ></path>
          </svg>
          <h2>Info utente</h2>
          <p>Accedi alle informazioni sul tuo account utente.</p>
        </a>
      </div>
      <div class="sezione">
        <a href="/Ordini">
          <svg
            class="icon"
            width="25"
            height="25"
            fill="none"
            stroke="currentColor"
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M2 6h20v4l-.7.42a3.5 3.5 0 0 1-3.6 0L17 10l-.7.42a3.5 3.5 0 0 1-3.6 0L12 10l-.7.42a3.5 3.5 0 0 1-3.6 0L7 10l-.7.42a3.5 3.5 0 0 1-3.6 0L2 10V6Z"
            ></path>
            <path d="M4 11.244V22h16V11"></path>
            <path d="M4 5.911V2h16v4"></path>
            <path d="M14.5 16h-5v6h5v-6Z"></path>
          </svg>
          <h2>Ordini</h2>
          <p>Visualizza e gestisci i tuoi ordini effettuati.</p>
        </a>
      </div>
      <div class="sezione">
        <a href="/Pagamenti">
          <svg
            class="icon"
            width="25"
            height="25"
            fill="none"
            stroke="currentColor"
            stroke-linecap="round"
            stroke-linejoin="round"
            stroke-width="2"
            viewBox="0 0 24 24"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              d="M8.991 5.984 15.892 2 18.2 5.994l-9.208-.01Z"
              clip-rule="evenodd"
            ></path>
            <path
              d="M2 7a1 1 0 0 1 1-1h18a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V7Z"
            ></path>
            <path
              d="M17.625 16.5H22v-5h-4.375C16.175 11.5 15 12.62 15 14s1.175 2.5 2.625 2.5Z"
            ></path>
            <path d="M22 8.25v12"></path>
          </svg>
          <h2>Pagamenti</h2>
          <p>Gestisci le opzioni di pagamento associate al tuo account.</p>
        </a>
      </div>
      <div class="sezione">
        <a href="<%=request.getContextPath()%>/Sicurezza">
          <svg width="25" height="25" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
  <path d="M20 11H4a1 1 0 0 0-1 1v9a1 1 0 0 0 1 1h16a1 1 0 0 0 1-1v-9a1 1 0 0 0-1-1Z"></path>
  <path d="M7 11V7a5 5 0 0 1 10 0v4"></path>
  <path d="M12 15v3"></path>
</svg>
            <path
              d="M2 12V6h4.5V5a3 3 0 0 1 6 0v1H17v6h2a3 3 0 1 1 0 6h-2v4H2v-4h2a3 3 0 1 0 0-6H2Z"
            ></path>
          </svg>
          <h2>Sicurezza</h2>
          <p>Modifica le tue impostazioni per l'accesso.</p>
        </a>
      </div>
    </div>
    </div>
       </body>
 <%@ include file="footer.html" %>
</html>