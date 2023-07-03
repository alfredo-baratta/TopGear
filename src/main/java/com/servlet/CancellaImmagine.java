package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.DriverManagerConnectionPool;

/**
 * Servlet implementation class CancellaImmagine
 */
@WebServlet("/cancellaimmagine")
public class CancellaImmagine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellaImmagine() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idImmagine = Integer.parseInt(request.getParameter("immagineDaCancellare"));
		
		try (Connection conn = DriverManagerConnectionPool.getConnection()) {
			String query = "DELETE FROM immagini_accessorio WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, idImmagine);
            preparedStatement.executeUpdate();
            
            DriverManagerConnectionPool.releaseConnection(conn);
        }catch (Exception e) {
        	System.out.println("Errore: " + e.getMessage());
		}
	    // Invia una risposta JSON al client per indicare il successo dell'upload
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write("{\"success\": true}");
	}

}
