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
 * Servlet implementation class salvaModifiche
 */
@WebServlet("/salvaModifiche")
public class salvaModifiche extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public salvaModifiche() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// prendo i valori dalla jsp
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String via = request.getParameter("via");
		String citta = request.getParameter("citta");
		String datanascita = request.getParameter("datanascita");
		String telefono = request.getParameter("telefono");
		String cap = request.getParameter("cap");
		String cf= request.getParameter("cf");
		
		// connessione al database
		try (Connection conn = DriverManagerConnectionPool.getConnection();
		     PreparedStatement statement = conn.prepareStatement("UPDATE utenti SET nome=?, cognome=?, datanascita=?, email=?, password=?, telefono=?, citta=?, via=?, cap=? WHERE cf=?")) {
			
			statement.setString(1, nome);
			statement.setString(2, cognome);
			statement.setString(3, datanascita);
			statement.setString(4, email);
			statement.setString(5, password);
			statement.setString(6, telefono);
			statement.setString(7, citta);
			statement.setString(8, via);
			statement.setString(9, cap);
			statement.setString(10, cf);

			
			int rowsAffected = statement.executeUpdate();
			
			if (rowsAffected > 0) {
				response.sendRedirect("conferma.jsp");
			} else {
				response.sendRedirect("errore.jsp");
			}
			
			DriverManagerConnectionPool.releaseConnection(conn);
		} catch (Exception e) {
			System.out.println("Errore: " + e.getMessage());
		}
	}
}
