package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.DriverManagerConnectionPool;

/**
 * Servlet implementation class presenzaEmail
 */
@WebServlet("/verificaEmail")
public class presenzaEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public presenzaEmail() {
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
		String email = request.getParameter("email");
		
		int res = -1;
		
		try (Connection conn = DriverManagerConnectionPool.getConnection();
			     PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) AS n_email FROM utenti WHERE email = ?;")) {
				
				statement.setString(1, email);
				
				ResultSet rs = statement.executeQuery();
				
				if(rs.next()) {
					res = rs.getInt("n_email");
				}
	 			
				DriverManagerConnectionPool.releaseConnection(conn);
			} catch (Exception e) {
				System.out.println("Errore: " + e.getMessage());
			}
		
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		
		if(res == 0) {
			out.print("false");
		}else {
			out.print("true");
		}
	}

}
