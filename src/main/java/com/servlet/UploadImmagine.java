package com.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.DriverManagerConnectionPool;

/**
 * Servlet implementation class UploadImmagine
 */
@WebServlet("/UploadImmagine")
@MultipartConfig(maxFileSize = 16177215)
public class UploadImmagine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadImmagine() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("imageFile");
		System.out.println(filePart.getName());             
		System.out.println(filePart.getSize());             
		System.out.println(filePart.getContentType());
		int fkAccessorio = Integer.parseInt(request.getParameter("idaccessorio"));
		
		InputStream inputStream = null;
		if (filePart != null) {
			inputStream = filePart.getInputStream();
			try (Connection conn = DriverManagerConnectionPool.getConnection()) {
			String query = "INSERT INTO immagini_accessorio (immagine, fk_accessorio) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setBlob(1, inputStream);
            preparedStatement.setInt(2, fkAccessorio);
            preparedStatement.executeUpdate();
            
            DriverManagerConnectionPool.releaseConnection(conn);
	        }catch (Exception e) {
	        	System.out.println("Errore: " + e.getMessage());
			}
			
		}
		
		
	    // Invia una risposta JSON al client per indicare il successo dell'upload
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write("{\"success\": true}");
	}


}
