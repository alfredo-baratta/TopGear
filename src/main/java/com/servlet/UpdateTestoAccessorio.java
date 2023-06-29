package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.DriverManagerConnectionPool;

@WebServlet("/upModAccessorio")
public class UpdateTestoAccessorio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public UpdateTestoAccessorio() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nome = request.getParameter("nome");
		String descrizione = request.getParameter("descrizione");
		float prezzo = Float.parseFloat(request.getParameter("prezzo"));
		float iva = Float.parseFloat(request.getParameter("iva"));
		int disponibilita = Integer.parseInt(request.getParameter("disponibilita"));
		boolean visibile = Boolean.parseBoolean(request.getParameter("visibile"));
		String id = request.getParameter("id");
		
		String redirectURL = "/TopGear/modifica?id="+id;
		
		if(nome==null || descrizione==null) {
			request.setAttribute("error", Boolean.TRUE);
        	request.setAttribute("messgerr", "Errore! Inserire correttamente i parametri");
			response.sendRedirect(redirectURL);
		}
		
		int rowsAffected = 0;
		
		try (Connection conn = DriverManagerConnectionPool.getConnection();
		     PreparedStatement statement = conn.prepareStatement("UPDATE accessori SET nome=?, descrizione=?, prezzo=?, iva=?, disponibilita=?, visibilita=? WHERE id=?")) {
			
			statement.setString(1, nome);
			statement.setString(2, descrizione);
			statement.setFloat(3, prezzo); 
			statement.setFloat(4, iva);
			statement.setInt(5, disponibilita);
			statement.setBoolean(6, visibile);
			statement.setString(7, id);

			rowsAffected = statement.executeUpdate();
 			
			DriverManagerConnectionPool.releaseConnection(conn);
		} catch (Exception e) {
			System.out.println("Errore: " + e.getMessage());
		}
		
		if (rowsAffected > 0) {
				response.sendRedirect(redirectURL);
			} else {
				request.setAttribute("error", Boolean.TRUE);
	        	request.setAttribute("messgerr", "Errore nell'aggiornamento dei parametri nel database, si prega di riprovare!");
				response.sendRedirect(redirectURL);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);	
	}
}