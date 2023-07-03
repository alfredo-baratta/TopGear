package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import Model.DriverManagerConnectionPool;

/**
 * Servlet implementation class Sicurezza
 */
@WebServlet("/Sicurezza")
public class Sicurezza extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sicurezza() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 // Verifica che l'utente sia autenticato
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            // L'utente non è autenticato, reindirizza alla pagina di login o a un'altra pagina di errore
            session.invalidate();
            response.sendRedirect("login.jsp");
            return;
        }

        // Recupera la primary key dell'account dalla sessione
        String cf = (String) session.getAttribute("username");

    	RequestDispatcher dispatcher = request.getRequestDispatcher("/Sicurezza.jsp");
    	try (Connection conn = DriverManagerConnectionPool.getConnection();
			     PreparedStatement statement = conn.prepareStatement("SELECT email FROM utenti WHERE cf = ?");){
    		
    		statement.setString(1, cf);
		    ResultSet resultSet = statement.executeQuery();
		    
		    if (resultSet.next()) {
		    	String Email = resultSet.getString("email");
		    	request.setAttribute("email", Email);

    	}}
    	catch (Exception e) {
			 	    System.out.println("Errore: " + e.getMessage());}
    	dispatcher.forward(request, response);
	}
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		// Verifica che l'utente sia autenticato
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            // L'utente non è autenticato, reindirizza alla pagina di login o a un'altra pagina di errore
            session.invalidate();
            response.sendRedirect("login.jsp");
            return;
        }

        // Recupera la primary key dell'account dalla sessione
        String cf = (String) session.getAttribute("username");
        
		String pwdAttuale= DigestUtils.md5Hex(request.getParameter("passwordAttuale"));
		String nuovaPwd= DigestUtils.md5Hex(request.getParameter("nuovaPassword"));
		
		try (Connection conn = DriverManagerConnectionPool.getConnection();
			     PreparedStatement selectStatement = conn.prepareStatement("SELECT password, email FROM utenti WHERE cf = ?");
			     PreparedStatement updateStatement = conn.prepareStatement("UPDATE utenti SET password=? WHERE cf=?")) {
			
			updateStatement.setString(2, cf);
			
			selectStatement.setString(1, cf);
		    ResultSet resultSet = selectStatement.executeQuery();
		    
		    if (resultSet.next()) {
		    	String pwd = resultSet.getString("password");
		    	String Email = resultSet.getString("email");
		    	request.setAttribute("email", Email);
		    	
		    if(pwdAttuale.equals(pwd)) {
		    	//ha inserito la password corretta
		    	
		    	if(nuovaPwd.equals(pwd)) {
		    		//vuole cambiare la password vecchia con una uguale
			    	request.setAttribute("tipo","errore");
			    	request.setAttribute("messaggio","La nuova password non può essere uguale alla precedente!");

		    		
		    	}else{
		    		//tutto ok
		            updateStatement.setString(1, nuovaPwd);
		    		updateStatement.executeUpdate();
			    	request.setAttribute("tipo","ok");
			    	request.setAttribute("messaggio","La password è stata modificata correttamente!");

			    	
		    	}} 
		    }
		    else {//password errata
		    	request.setAttribute("tipo","errore");
		    	request.setAttribute("messaggio","Password errata!");
		    }
		    
		    
	}catch (Exception e) {
	    System.out.println("Errore: " + e.getMessage());
	}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Sicurezza.jsp");
		dispatcher.forward(request, response);
}}
