package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String cf= request.getParameter("cf");
		String nuovaEmail= request.getParameter("email");
		String pwdAttuale= request.getParameter("passwordAttuale");
		String nuovaPwd= request.getParameter("nuovaPassword");
		
		try (Connection conn = DriverManagerConnectionPool.getConnection();
			     PreparedStatement selectStatement = conn.prepareStatement("SELECT * FROM utenti WHERE cf = ?");
			     PreparedStatement updateStatement = conn.prepareStatement("UPDATE utenti SET email=?, password=? WHERE cf=?")) {
			
			selectStatement.setString(1, cf);
		    ResultSet resultSet = selectStatement.executeQuery();
		    
		    if (resultSet.next()) {
		    	String email = resultSet.getString("email");
		    	String pwd = resultSet.getString("password");
		    	
		    if(pwdAttuale.equals(pwd)) {
		    	//ha inserito la password corretta
		    	
		    	if(nuovaPwd.equals(pwd)) {
		    		//vuole cambiare la password vecchia con una uguale
		    		
		    	}else{
		    		//tutto ok
		    		updateStatement.setString(1, nuovaEmail);
		            updateStatement.setString(2, nuovaPwd);
		    		updateStatement.executeUpdate();
			        response.sendRedirect("conferma.jsp");
		    	}} 
		    }
		    else {//password errata 
		    }
		    
		    
	}catch (Exception e) {
	    System.out.println("Errore: " + e.getMessage());
	}

}}
