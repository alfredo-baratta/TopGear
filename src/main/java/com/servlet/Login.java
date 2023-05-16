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
import javax.servlet.http.HttpSession;

import java.sql.*;

import org.apache.commons.codec.digest.DigestUtils;

@WebServlet("/loginServlet")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/topgear")
	DataSource dataSource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		response.setContentType("text/html");
        
		HttpSession session = request.getSession(true);
 
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        if (email == null || password == null) {
        	request.setAttribute("error", Boolean.TRUE);
        	request.setAttribute("messgerr", "Parametri non presenti.");
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        	dispatcher.forward(request, response);
        	return;
        }
        
        try {
	        Connection conn = dataSource.getConnection();
	        
	        String query = "SELECT id FROM utente WHERE email = ? AND password = ?";
	        PreparedStatement ps = conn.prepareStatement(query);
	        
	        password = DigestUtils.md5Hex(password);
	        
	        ps.setString(1, email);
	        ps.setString(2, password);
	        
	        ResultSet rs = ps.executeQuery();
	        
	        if(rs.next()) {
	        	String codiceFiscale = rs.getString("codicefiscale");
	        	session.setAttribute("cf", codiceFiscale);
	        	request.getRequestDispatcher("/index.jsp").forward(request, response);
	        } else {
	        	request.setAttribute("error", Boolean.TRUE);
	        	request.setAttribute("messgerr", "Credenziali non valide.");
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
	        	dispatcher.forward(request, response);
	        }
	        
        } catch (Exception e) {
        	System.out.println("Errore: " + e.getMessage());
        	
        	request.setAttribute("error", Boolean.TRUE);
        	request.setAttribute("messgerr", e.getMessage());
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        	dispatcher.forward(request, response);
        }
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
