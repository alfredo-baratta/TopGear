package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.codec.digest.DigestUtils;

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
        
        try {
        	Connection conn = dataSource.getConnection();
	        
	        String query = "INSERT INTO Utente (CF, nome, cognome, datanascita, email, pass, cellulare, citta, via, cap) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement ps = conn.prepareStatement(query);
	        
	        password = DigestUtils.md5Hex(password);
	        
	        ps.setString(1, codiceFiscale);
	        ps.setString(2, nome);
	        ps.setString(3, cognome);
	        ps.setString(4, dataDiNascita);
	        ps.setString(5, email);
	        ps.setString(6, password);
	        ps.setString(7, cellulare);
	        ps.setString(8, citta);
	        ps.setString(9, indirizzo);
	        ps.setString(10, cap);
	        
	        ps.executeUpdate();
	        
	        HttpSession session = request.getSession(true);
	    	session.setAttribute("cf", codiceFiscale);
	    	
	        request.getRequestDispatcher("/index.jsp").forward(request, response);
	        
        } catch (Exception e) {
        	System.out.println("Errore: " + e.getMessage());
        	
        	String mesgerr = null;
        	
        	if (e.getMessage().toLowerCase().contains("duplicate")) {
        		if(e.getMessage().contains("utente.PRIMARY"))
        			mesgerr = "Errore, il codice fiscale da lei inserito risulta già presente nei nostri sistemi";
        		else if(e.getMessage().contains("utente.email"))
        			mesgerr = "Errore, l'indirizzo email da lei inserito risulta già presente nei nostri sistemi";
        		else if(e.getMessage().contains("utente.cellulare"))
        			mesgerr = "Errore, il numero di telefono da lei inserito risulta già presente nei nostri sistemi";
        		else 
					mesgerr = "Errore, ricontrollare i dati inseriti";
        	}
        	
        	if(mesgerr == null)
				mesgerr = "Errore, ricontrollare i dati inseriti";

        	request.setAttribute("error", Boolean.TRUE);
        	request.setAttribute("messgerr", mesgerr);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
            dispatcher.forward(request, response);        		
            
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
