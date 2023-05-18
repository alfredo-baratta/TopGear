package com.servlet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import entities.Utente;
import entities.Risposta;

@WebServlet("/registrazioneServlet")
public class Registrazione extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/topgear")
	DataSource dataSource;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		 
        String codiceFiscale = request.getParameter("cf");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String dataDiNascita = (String) request.getParameter("datanascita");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cellulare = request.getParameter("numtel");
        String citta = request.getParameter("citta");
        String indirizzo = request.getParameter("indirizzo");
        String cap = request.getParameter("cap");
        
        if (codiceFiscale == null || nome == null || cognome == null || dataDiNascita == null || email == null || password == null
        		|| cellulare == null || citta == null || indirizzo == null || cap == null) {
        	request.setAttribute("error", Boolean.TRUE);
        	request.setAttribute("messgerr", "Parametri non presenti.");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        	dispatcher.forward(request, response);
        	return;
        }
        
        Utente u = new Utente();
        Risposta r = new Risposta();
        
        if(u.verificaEmail(email)) {
        	request.setAttribute("error", Boolean.TRUE);
        	request.setAttribute("messgerr", "Indirizzo email già presente nel sistema, verrai reindirizzato alla pagina di login!");
        	
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);   
        }
        
        r = u.salvaUtente(codiceFiscale, nome, cognome, dataDiNascita, email, password, cellulare, citta, indirizzo, cap);
        if(!r.getEsito()) {
        	request.setAttribute("error", Boolean.TRUE);
        	request.setAttribute("messgerr", r.getMessage());
        	
        	if(r.getMessage().toLowerCase().contains("non valid")) {
        		RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        		dispatcher.forward(request, response); 
        	}else {
        		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        		dispatcher.forward(request, response); 
        	}
            
        }else {
        	request.setAttribute("email", u.getEmail());
	        request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
