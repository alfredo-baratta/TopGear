package com.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.DriverManagerConnectionPool;

/**
 * Servlet implementation class caricaAccessorio
 */
@WebServlet("/caricamentoAccessorio")
@MultipartConfig(maxFileSize = 50000000,
					maxRequestSize = 50000000)
public class caricaAccessorio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public caricaAccessorio() {
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
		String nome = request.getParameter("nome");
		String descrizione = request.getParameter("descrizione");
		double prezzo = Double.parseDouble(request.getParameter("prezzo"));
		double iva = Double.parseDouble(request.getParameter("iva"));
		int disponibilita = Integer.parseInt(request.getParameter("disponibilita"));
		boolean visibile = Boolean.parseBoolean(request.getParameter("visibile"));
		
		int fkAccessorio = -1;
		
		System.out.println("Sono qui");
		
		try (Connection conn = DriverManagerConnectionPool.getConnection()){
				PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO accessori (nome, descrizione, prezzo, iva, disponibilita, visibilita) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				insertStatement.setString(1, nome);
				insertStatement.setString(2, descrizione);
				insertStatement.setDouble(3, prezzo);
				insertStatement.setDouble(4, iva);
				insertStatement.setInt(5, disponibilita);
				insertStatement.setBoolean(6, visibile);
				insertStatement.executeUpdate();
				
				ResultSet generatedKeys = insertStatement.getGeneratedKeys();
			    if (generatedKeys.next()) {
			    	fkAccessorio = generatedKeys.getInt(1);
			    }
	 			
				DriverManagerConnectionPool.releaseConnection(conn);
			} catch (Exception e) {
				System.out.println("Errore: " + e.getMessage());
			}
		
		try (Connection conn = DriverManagerConnectionPool.getConnection()){
			for (Part part : request.getParts()) {
				if(part != null && part.getName().equals("fileInput[]")) {
					InputStream inputStream = null;
					inputStream = part.getInputStream();
					
					String query = "INSERT INTO immagini_accessorio (immagine, fk_accessorio) VALUES (?, ?)";
		            PreparedStatement preparedStatement = conn.prepareStatement(query);
		            preparedStatement.setBlob(1, inputStream);
		            preparedStatement.setInt(2, fkAccessorio);
		            preparedStatement.executeUpdate();
				}
			}
			DriverManagerConnectionPool.releaseConnection(conn);
		} catch (Exception e) {
			System.out.println("Errore: " + e.getMessage());
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("{\"success\": true}");

	}

}